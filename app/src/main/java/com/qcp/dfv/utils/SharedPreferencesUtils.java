package com.qcp.dfv.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.qcp.dfv.DfvApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Android on 2017/6/3.
 */

public class SharedPreferencesUtils {

    public static final String SHAREDPREFERENCESKEY = "qcpdfv.setting";

    public static void setSharedPreferencesy(Context context, String key, String values) {
        SharedPreferences sp = context.getSharedPreferences(SharedPreferencesUtils.SHAREDPREFERENCESKEY, context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, values);
        edit.commit();
    }

    public static void setSharedPreferencesy(Context context, String key, long values) {
        SharedPreferences sp = context.getSharedPreferences(SharedPreferencesUtils.SHAREDPREFERENCESKEY, context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putLong(key, values);
        edit.commit();
    }

    public static void setSharedPreferencesy(Context context, String key, Boolean values) {
        SharedPreferences sp = context.getSharedPreferences(SharedPreferencesUtils.SHAREDPREFERENCESKEY, context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, values);
        edit.commit();
    }


    public static String getPhone(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SharedPreferencesUtils.SHAREDPREFERENCESKEY, context.MODE_PRIVATE);
        String time = sp.getString("phone", "");
        return time;
    }

    public static String getPwd(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SharedPreferencesUtils.SHAREDPREFERENCESKEY, context.MODE_PRIVATE);
        String time = sp.getString("pwd", "");
        return time;
    }

    public static boolean getIsSave(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SharedPreferencesUtils.SHAREDPREFERENCESKEY, context.MODE_PRIVATE);
        boolean time = sp.getBoolean("is_save", false);
        return time;
    }

    public static boolean getIsLogin(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SharedPreferencesUtils.SHAREDPREFERENCESKEY, context.MODE_PRIVATE);
        boolean time = sp.getBoolean("is_login", false);
        return time;
    }


    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private static String Object2String(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private static Object String2Object(String objectString) {
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 使用SharedPreference保存对象
     *
     * @param fileKey    储存文件的key
     * @param key        储存对象的key
     * @param saveObject 储存的对象
     */
    public static void save(String fileKey, String key, Object saveObject) {
        SharedPreferences sharedPreferences = DfvApplication.getInstance().getSharedPreferences(fileKey, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String string = Object2String(saveObject);
        editor.putString(key, string);
        editor.commit();
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param fileKey 储存文件的key
     * @param key     储存对象的key
     * @return object 返回根据key得到的对象
     */
    public static Object get(String fileKey, String key) {
        SharedPreferences sharedPreferences = DfvApplication.getInstance().getSharedPreferences(fileKey, Activity.MODE_PRIVATE);
        String string = sharedPreferences.getString(key, null);
        if (string != null) {
            Object object = String2Object(string);
            return object;
        } else {
            return null;
        }
    }

}
