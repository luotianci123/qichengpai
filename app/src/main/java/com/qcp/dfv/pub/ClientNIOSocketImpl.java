package com.qcp.dfv.pub;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.qcp.dfv.net.JsonCallback;
import com.qcp.dfv.utils.BitmapUtil;
import com.qcp.dfv.utils.ImageUrlDir;
import com.qcp.dfv.utils.SaveBitmapFiles;
import com.qcp.dfv.utils.StringUtils;
import com.qcp.dfv.utils.SysUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by luotc on 2018/6/2.
 */

public class ClientNIOSocketImpl extends Thread {
    /*发送数据缓冲区*/
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    /*接受数据缓冲区*/
    private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    /*服务器端地址*/
    private InetSocketAddress SERVER;
    private Selector selector;
    private SocketChannel client;
    private String receiveText;
    private String sendText;
    private int count = 0;
    private Charset charset = Charset.forName("UTF-8");
    static SocketChannel socketChannel;
    Context context;
    String mPreview;
    String clientMsg;
    public ClientNIOSocketImpl(Context context,String mPreview) {
        this.context = context;
        this.mPreview = mPreview;
    }

    @Override
    public void run() {
        L.e("LLL", "sn222222:");
        SERVER = new InetSocketAddress("192.168.100.201", 8095);
        L.e("LLL", "sn333333:");
        try {
            /**
             * 客户端向服务端发起建立连接请求
             */
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            selector = Selector.open();
            L.e("LLL", "sn444444:");
            //将通道注册到选择器上，并且分配连接权限
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            if(SERVER != null) {
                socketChannel.connect(SERVER);
            }
            /**
             * 轮询监听客户端上注册事件的发生
             */
            while (true) {
                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();
                for (final SelectionKey key : keySet) {
                    handle(key,mPreview);
                }
                keySet.clear();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void handle(SelectionKey selectionKey,String preview) throws IOException {
        L.e("LLL", "sn5555555:");
//        String clientMsg = "\"loginAccount:\"\"1118888\"\"operationFlag:\"\"preview\"";
//        String clientMsg = "deviceConnect";//设备端连服务器
        if(preview.equals("preview")) {
             clientMsg = "\"loginAccount:\"\"13590435468\"\"operationFlag:\"\"preview\"";//手机端连服务器
        }else if(preview.equals("watch")){
             clientMsg = "\"loginAccount:\"\"13590435468\"\"operationFlag:\"\"watch\"";//手机端连服务器
        }
        L.e("LLL", "selectionKey.isConnectable():" + selectionKey.isConnectable() + "----" + selectionKey.isAcceptable());
        if (selectionKey.isConnectable()) {//此键的通道完成socket连接操作
            /**
             * 连接建立事件，已成功连接至服务器
             */
            client = (SocketChannel) selectionKey.channel();
            L.e("LLL", "sn6666666:");
            if (client.isConnectionPending()) {//此通道正在进行连接操作
                L.e("LLL", "sn777777777:");
                client.finishConnect();
                System.out.println("connect success !");
                L.e("LLL", "sn888888888:");

                sBuffer.clear();
                sBuffer.put(clientMsg.getBytes());
                sBuffer.flip();
                //通过连接通道发送信息至服务器
                client.write(sBuffer);


            }
            //注册读事件
            client.register(selector, SelectionKey.OP_READ);

        } else if (selectionKey.isReadable()) {
            L.e("LLL", "sn7777777777777777777:");
            String imei = "";
            String OperationFlag = "";
            /*
	         * 读事件触发
	         * 有从服务器端发送过来的信息，读取输出到屏幕上后，继续注册读事件
	         * 监听服务器端发送信息
	         */
            client = (SocketChannel) selectionKey.channel();
            rBuffer.clear();
            count = client.read(rBuffer);
            if (count > 0) {
                L.e("LLL", "sn8888888888:" + count);
                receiveText = new String(rBuffer.array(), 0, count);
                System.out.println("从服务器收到消息:" + receiveText);
                // 从服务器收到消息:"IMEI:""00000""OperationFlag:""device connect success"#
                Log.e("LLL", "手机从服务器收到消息:" + receiveText);
                client = (SocketChannel) selectionKey.channel();
                client.register(selector, SelectionKey.OP_READ);
                Log.e("LLL","ServerMsg77777777:------");
//                String msg = "\"IMEI:\"\"00000\"\"OperationFlag:\"\"device connect success\"#";
                Log.e("LLL","receiveText:" + receiveText);
                if (receiveText.contains("ServerMsg:")) {
                    String ServerMsg = receiveText.substring(receiveText.indexOf("\"ServerMsg:\""), receiveText.indexOf("\"\"L")).replace("\"ServerMsg:\"\"", "");
                    Log.e("LLL","ServerMsg:" + ServerMsg);
                    String LoginAccount_ = receiveText.substring(receiveText.indexOf("\"LoginAccount:\""), receiveText.indexOf("\"\"R")).replace("\"LoginAccount:\"\"", "");
                    Log.e("LLL","LoginAccount_:" + LoginAccount_ );
                    String ResourceId = receiveText.substring(receiveText.indexOf("\"ResourceId:\""), receiveText.indexOf("\"\"#")).replace("\"ResourceId:\"\"", "");
                    Log.e("LLL","ResourceId:" + ResourceId);
                    if(ServerMsg.equals("getServerPhotoData")) {//图片
                        try {
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (ResourceId != null) {
                            postData(ResourceId);
                        }
                    }
                    if(ServerMsg.equals("getServerVideoData")) {//视频
                        try {
                            client.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (ResourceId != null) {
                            postData2(ResourceId);
                        }
                    }

                }

            }
        }
    }

    private void postData(String LoginAccount_) {
        Log.e("LLL", "postData:");
        ArrayList<File> file1 = new ArrayList<>();
        if(file1 != null && file1.size() > 0){
            file1.clear();
        }
        List<String> urlImageDir = ImageUrlDir.getUrlImageDir();
        for (int i = 0; i < urlImageDir.size(); i++) {
            File file = new File(BitmapUtil.compressImage(SaveBitmapFiles.saveBitmapFile(SaveBitmapFiles.getVideoThumbnail(context, context.getContentResolver(), urlImageDir.get(i)),urlImageDir.get(i))));
            file1.add(file);
        }
        OkGo.post("http://192.168.100.201:8085/qcpai-console/photoManager/getThumbnailFiles")
                .params("resourceId", LoginAccount_)
                .execute(new JsonCallback<JsonObject>() {
                    @Override
                    public void onSuccess(JsonObject jsonObject, Call call, Response response) {
                        Log.e("LLL", "json:" + jsonObject);
                        if(jsonObject.get("requestCode").equals("1")){

                        }
                    }

                    @Override
                    public void onFailure(String code, String message) {
                        super.onFailure(code, message);
                        Log.e("LLL", "onFailure:" + code + "----" + message);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.e("LLL", "onError:" + response + "----" + e);
                    }
                });

    }

    private void postData2(String LoginAccount_) {//获取视频
        Log.e("NN2","postData:");
        OkGo.get("http://192.168.100.162:8082/qcpai-console/onTheRoad/downloadVideo2/downloadVideo")
//                .params("fileId",LoginAccount_)//037946339
                .execute(new FileCallback() {
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        Log.e("NN2","file:" + file);
                        Uri.parse(String.valueOf(new File(file.toString())));

//                        videoView.setVideoURI(Uri.parse(String.valueOf(new File(file.toString()))));
//                        //设置视频路径
//                        //开始播放视频
//                        videoView.start();
                    }
                });
    }
    public static void close() {
        if (socketChannel.isConnected()) {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
