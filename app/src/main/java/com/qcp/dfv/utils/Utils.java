package com.qcp.dfv.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by w.gs on 2016/8/22.
 */
public class Utils {

    public static void startActivity(Context activity, Class<? extends AppCompatActivity> clazz) {
        startActivity(activity, clazz, null);
    }


    public static void startActivity(Context context, String className) {
        try {
            context.startActivity(new Intent(context, Class.forName(className)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public static void startActivity(Context activity, Class<? extends AppCompatActivity> clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    /**
     * 如果为空则给出Toast提示
     *
     * @param text
     * @param msg
     * @return
     */
    public static boolean checkNotEmpty(CharSequence text, String msg) {
        if (!TextUtils.isEmpty(text)) {
            return true;
        }
        AppUtils.showToast(msg);
        return false;
    }

    /**
     * 如果为空则给出Toast提示
     *
     * @param editText
     * @param msg
     * @return
     */
    public static boolean checkNotEmpty(EditText editText, String msg) {
        if (!TextUtils.isEmpty(editText.getText())) {
            return true;
        }
        AppUtils.showToast(msg);
        return false;
    }


    /***
     * 返回指定颜色的文本
     *
     * @param content
     * @param color
     * @return
     */
    public static SpannableString getColorString(CharSequence content, @ColorInt int color) {

        return TextUtils.isEmpty(content) ? new SpannableString("")
                : getColorString(content, 0, content.length(), color);
    }

    /**
     * 返回指定颜色的文本
     *
     * @param content
     * @param start
     * @param end
     * @param color
     * @return
     */
    public static SpannableString getColorString(CharSequence content, int start, int end, @ColorInt int color) {
        if (TextUtils.isEmpty(content)) {
            return new SpannableString("");
        }
        SpannableString sps = new SpannableString(content); // 更换字体颜色
        sps.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sps;
    }

    /**
     * 生成二维码图片
     *
     * @param text
     * @param w
     * @param h
     * @param logo
     * @return
     */
    public static Bitmap createImage(String text, int w, int h, Bitmap logo) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        try {
            Bitmap scaleLogo = getScaleLogo(logo, w, h);

            int offsetX = w / 2;
            int offsetY = h / 2;

            int scaleWidth = 0;
            int scaleHeight = 0;
            if (scaleLogo != null) {
                scaleWidth = scaleLogo.getWidth();
                scaleHeight = scaleLogo.getHeight();
                offsetX = (w - scaleWidth) / 2;
                offsetY = (h - scaleHeight) / 2;
            }
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //容错级别
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置空白边距的宽度
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, w, h, hints);
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (x >= offsetX && x < offsetX + scaleWidth && y >= offsetY && y < offsetY + scaleHeight) {
                        int pixel = scaleLogo.getPixel(x - offsetX, y - offsetY);
                        if (pixel == 0) {
                            if (bitMatrix.get(x, y)) {
                                pixel = 0xff000000;
                            } else {
                                pixel = 0xffffffff;
                            }
                        }
                        pixels[y * w + x] = pixel;
                    } else {
                        if (bitMatrix.get(x, y)) {
                            pixels[y * w + x] = 0xff000000;
                        } else {
                            pixels[y * w + x] = 0xffffffff;
                        }
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(w, h,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Bitmap getScaleLogo(Bitmap logo, int w, int h) {
        if (logo == null) return null;
        Matrix matrix = new Matrix();
        float scaleFactor = Math.min(w * 1.0f / 5 / logo.getWidth(), h * 1.0f / 5 / logo.getHeight());
        matrix.postScale(scaleFactor, scaleFactor);
        Bitmap result = Bitmap.createBitmap(logo, 0, 0, logo.getWidth(), logo.getHeight(), matrix, true);
        return result;
    }

    /**
     * 流量转换
     */
    public static String convertTraffic(long traffic) {
        BigDecimal trafficKB;
        BigDecimal trafficMB;
        BigDecimal trafficGB;

        BigDecimal temp = new BigDecimal(traffic);
        BigDecimal divide = new BigDecimal(1024);
        trafficKB = temp.divide(divide, 2, 1);
        if (trafficKB.compareTo(divide) > 0) {
            trafficMB = trafficKB.divide(divide, 2, 1);
            if (trafficMB.compareTo(divide) > 0) {
                trafficGB = trafficMB.divide(divide, 2, 1);
                return trafficGB.doubleValue() + "GB";
            } else {
                return trafficMB.doubleValue() + "MB";
            }
        } else {
            return trafficKB.doubleValue() + "KB";
        }
    }

    public static String getDownloadPerSize(long finished, long total) {
        return DF.format((float) finished / (1024 * 1024)) + "M/" + DF.format((float) total / (1024 * 1024)) + "M";
    }

    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public static String getApkFilePackage(Context context, File apkFile) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(apkFile.getPath(), PackageManager.GET_ACTIVITIES);
        if (info != null){
            return info.applicationInfo.packageName;
        }
        return null;
    }

    public static boolean isAppInstalled(Context context, String packageName) {
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
//        if (!ListUtils.isEmpty(packages)) {
//            for (PackageInfo packageInfo : packages) {
//                if (packageInfo.packageName.equals(packageName)) {
//                    return true;
//                }
//            }
//        }
        return false;
    }


    public static void installApk(Context context, String apkPath) {
        if (context == null || TextUtils.isEmpty(apkPath)) {
            return;
        }
        File file = new File(apkPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            //provider authorities
            Uri apkUri = FileProvider.getUriForFile(context, "com.ddp.dfv.provider", file);
            //Granting Temporary Permissions to a URI
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    public static void unInstallApp(Context context, String packageName) {
        Uri packageUri = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE, packageUri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    // 两次点击按钮之间的点击间隔不能少于2000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime;

    /**
     * 防止多次点击button多次执行
     */
    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /**
     * 空返回true
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if(str!=null&&(!str.trim().equals(""))){
            return false;
        }else{
            return true;
        }
    }

}
