package com.qcp.dfv.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;


import java.io.File;
import java.io.IOException;

/**
 * Created by ${LingYun} on 2017/11/14 0014.
 * <p>
 * android7.0及以上访问系统照相和图库会造成权限崩溃问题
 * 1、是在调用系统相机的时候；
 * 2、是在写入文件的时候， 在我这里是强制升级后下载完成安装Apk时；
 * 关于FileUriExposedException 异常的描述是这样子的：
 * 针对于权限部分，Android7.0是google推出的对权限做了一个更新即不允许出现以file://的形式调用隐式APP系统，也就是说以前呢，Uri的获取方式是以file://xxx的样式来，那么我们也就是通过Uri.fromFile()来获取如今放在7.0及以上系统呢，这样子就不行啦；
 * 如今的解决关键在哪里呢，需要在应用间共享文件，也就是需要发送一项content://URI，并授予 URI 临时访问权限。进行此授权的最简单方式是使用FileProvider类。
 * 嗯的，FileProvider
 * <p>
 * 1、首先我们需要在AndroidManifest中的application下添加provider：
 * authorities：是该项目的包名+provider
 * grantUriPermissions：必须是true，表示授予 URI 临时访问权限
 * exported：必须是false
 * resource：中的@xml/file_paths是我们接下来要在资源文件目录下添加的文件
 * 2、在res目录下新建一个xml文件夹，并且新建一个file_paths的xml文件（如下图）
 * 需要注意的是:
 * path：需要临时授权访问的路径（.代表在相机调用时候访问的是所有路径，而文件写入时访问的路径是Android/data/com.ijuyin.prints.news/）
 * name： 是你为设置的这个访问路径起的名字
 * <p>
 * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
 * String authority = getActivity().getPackageName() + ".provider";
 * imageUri = FileProvider.getUriForFile(getActivity(), authority, mTmpFile);
 * } else {
 * imageUri = Uri.fromFile(mTmpFile);
 * }
 * <p>
 * 需要注意的地方是：
 * 首先我们对Android系统的型号做出判断
 * 添加flags，表明我们要被授予什么样的临时权限
 * 以前我们直接 Uri.fromFile(apkFile)构建出一个Uri,现在我们使用FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".provider", mTmpFile);
 * 其中getActivity().getPackageName()指的是该项目的应用包名（此处调用的是在fragment，所以使用的是getActivity()）
 * 通过以上4步的设置操作，就可以完全解决Android 7.0及以上权限问题导致的崩溃问题。
 */

public class PhotoAfterFilePathTool {

    static Bitmap bit;
    /**
     * 存储图片或者视频的目录
     */
    public static String getSavaFolderPath(String folderName) {
        File file = new File(PhotoFileUtils.SDPATH+folderName);
        try {
            if(!file.exists()){
                file = PhotoFileUtils.createSDDir(folderName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        if (file != null && file.exists()) {
//            L.e("AXAX","img4:" + file.getAbsolutePath());
            return file.getAbsolutePath();
//        } else {
//            return "";
//        }
    }

    /**
     * 写入照相和图库的url
     */
    public static Uri getFileProviderUri(Activity activity, File file) {
        String authority = activity.getPackageName() + ".provider";
        return FileProvider.getUriForFile(activity, authority, file);
    }
    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }
}
