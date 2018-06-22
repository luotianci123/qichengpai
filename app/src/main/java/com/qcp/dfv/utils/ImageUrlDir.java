package com.qcp.dfv.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luotc on 2018/6/12.
 */

public class ImageUrlDir {
    private static List<String> list = new ArrayList<>();
    public static List<String>  getUrlImageDir()
    {
        list.clear();
        //获取本地文件夹下的图片或者视频资源
        File scanner5Directory = new File(Environment.getExternalStorageDirectory().getPath() + "/DrivingRecord");//记得修改路径文件夹
        if (scanner5Directory.isDirectory()) {
            Log.e("nmnm", "scanner5Directory.isDirectory(");
            for (File file : scanner5Directory.listFiles()) {
                String path = file.getAbsolutePath();
                if (path.endsWith(".jpg") || path.endsWith(".jpeg") || path.endsWith(".png") || path.endsWith(".bmp") || path.endsWith(".JPG") || path.endsWith(".avi") || path.endsWith(".mp4")) {
                    list.add(path);
                }
            }
        }
        return list;
    }
    public static void getUrlVideoDir()
    {
        list.clear();
        //获取本地文件夹下的图片或者视频资源
        File scanner5Directory = new File(Environment.getExternalStorageDirectory().getPath() + "/DrivingRecord");//记得修改路径文件夹
        if (scanner5Directory.isDirectory()) {
            Log.e("nmnm", "scanner5Directory.isDirectory(");
            for (File file : scanner5Directory.listFiles()) {
                String path = file.getAbsolutePath();
                if (path.endsWith(".mp4")) {
                    list.add(path);
                }
            }
        }
    }
}
