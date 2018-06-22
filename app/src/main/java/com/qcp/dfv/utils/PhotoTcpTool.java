package com.qcp.dfv.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import com.qcp.dfv.constant.PhotoConstants;
import com.qcp.dfv.pub.L;
import com.soundcloud.android.crop.Crop;

import java.io.File;


/**
 * Created by ${LingYun} on 2018/1/9 0009.
 * <p>
 * Taking pictures
 * choose pictures
 * <p>
 * 选择图片，拍照
 */

public class PhotoTcpTool {

    private static PhotoTcpTool tcpTool;
    OnChoosePictureListener onChoosePictureListener;
    Activity activity;
    public Uri uri;
    File mTmpFile;
    int photo_btn;
    Uri selectedImage;
    File file_3;
     String path;
    public static final int MODE_UPLOAD_IMAGE_CAMERA = 920;

    public static final int MODE_UPLOAD_IMAGE_ABLUME = 921;
    private String imagePath;

    private PhotoTcpTool() {

    }

    public static PhotoTcpTool getInstance() {
        if (tcpTool == null) {
            tcpTool = new PhotoTcpTool();
        }

        return tcpTool;
    }

    public void startChoose(Activity activity, Fragment fragment, int flag) {

        if (fragment != null) {
            this.activity = fragment.getActivity();
        } else {
        this.activity = activity;
        }
        getCameraFile();
        getUri();
        switch (flag) {
            case PhotoConstants.CommonKey.TAKING_PICTURES:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                activity.startActivityForResult(intent, MODE_UPLOAD_IMAGE_CAMERA);
                break;

            case PhotoConstants.CommonKey.CHOOSE_PICTURES:
                Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activity.startActivityForResult(intent1, MODE_UPLOAD_IMAGE_ABLUME);
                break;
        }
    }

    public void upLoadImage(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case MODE_UPLOAD_IMAGE_CAMERA:
                photo_btn = 1;
                if (resultCode == Activity.RESULT_OK) {
                    if (uri != null) {
                        L.e("111f", "进行拍照");
                        uri = PhotoAfterFilePathTool.getFileProviderUri(activity, mTmpFile);
                        Crop.of(uri, uri).asSquare().start(activity);
                    } else {
                        Toast.makeText(activity, "拍照失败", Toast.LENGTH_LONG).show();
                    }

                }
                break;
            case MODE_UPLOAD_IMAGE_ABLUME:
                photo_btn = 2;
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {
                        selectedImage = data.getData();

                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        ContentResolver resolver = activity.getContentResolver();
                        Cursor cursor = resolver.query(selectedImage, filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        path = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
                        cursor.close();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            selectedImage = PhotoAfterFilePathTool.getFileProviderUri(activity, new File(path));
                        } else {
                            selectedImage = PhotoAfterFilePathTool.getFileProviderUri(activity, new File(path));
//                            selectedImage = Uri.fromFile(new File(path));
                        }

                        Crop.of(selectedImage,selectedImage).asSquare().start(activity);
                    } else {
                        Toast.makeText(activity, "选择图片失败", Toast.LENGTH_LONG).show();
                    }

                }
                break;
            case Crop.REQUEST_CROP:
                if (uri != null) {
                    if (!TextUtils.isEmpty(uri.getPath())) {

                        if (photo_btn == 1) {
                           // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                           //     onChoosePictureListener.OnChoose("content://" + activity.getPackageName() + ".provider" + uri.getPath());
                          //  } else {
                          //      onChoosePictureListener.OnChoose(uri.getPath());
                         //   }
						 if(path!=null){
							  onChoosePictureListener.OnChoose(path);
						 }else{
							  onChoosePictureListener.OnChoose(mTmpFile.getPath());
						 }
						
                        }
                    }
                    if (photo_btn == 2) {
                        L.e("DDDG",Build.VERSION.SDK_INT + "====" + Build.VERSION_CODES.M + PhotoAfterFilePathTool.hasSdcard());
                       // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                      //      onChoosePictureListener.OnChoose("content://" + activity.getPackageName() + ".provider" + PhotoAfterFilePathTool.getFileProviderUri(activity, new File(path)).getPath());
                      //  } else {
                      //      onChoosePictureListener.OnChoose(PhotoAfterFilePathTool.getFileProviderUri(activity, new File(path)).getPath());
                    //    }
					 if(path!=null){
							  onChoosePictureListener.OnChoose(path);
						 }else{
							  onChoosePictureListener.OnChoose(mTmpFile.getPath());
						 }
                    }
                }
                releaseCarema();
                break;
        }

    }

    public void releaseCarema() {
        L.e("JJJJ","000000000000");
        if (tcpTool != null) {
            tcpTool = null;
        }
        if(mTmpFile != null){
            mTmpFile = null;
        }

}

    //获取存储拍照的文件
    private void getCameraFile() {
        try {
            imagePath = PhotoAfterFilePathTool.getSavaFolderPath("IMG");
            if (!TextUtils.isEmpty(imagePath)) {
                mTmpFile = new File(imagePath, "temp_headImg.jpg");
            } else {
                Toast.makeText(activity, "创建文件失败", Toast.LENGTH_LONG).show();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //7.0以上路径问题，需要获取不一样的uri
    private void getUri() {
        if (mTmpFile != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                uri = PhotoAfterFilePathTool.getFileProviderUri(activity, mTmpFile);
            } else {
//                uri = Uri.fromFile(mTmpFile);
                uri = PhotoAfterFilePathTool.getFileProviderUri(activity, mTmpFile);
            }
        } else {
            Toast.makeText(activity, "创建文件失败+++", Toast.LENGTH_LONG).show();
            return;
        }
    }

    //清除缓存

    public void removeData() {
        tcpTool = null;
        uri = null;
    }

    public PhotoTcpTool setOnChoosePictureListener(OnChoosePictureListener onChoosePictureListener) {
        this.onChoosePictureListener = onChoosePictureListener;

        return tcpTool;
    }

    public interface OnChoosePictureListener {
        void OnChoose(String filePath);

        void OnCancel();
    }
}
