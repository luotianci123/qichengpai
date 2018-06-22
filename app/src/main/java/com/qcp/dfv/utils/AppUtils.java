package com.qcp.dfv.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.qcp.dfv.DfvApplication;

/**
 * Created by w.gs on 2016/1/14.
 */
public class AppUtils {


    public static void showToast(String txt) {
        Toast.makeText(DfvApplication.getInstance().getApplicationContext(), txt, Toast.LENGTH_LONG).show();
    }

    public static void showShortToast(String txt) {
        Toast.makeText(DfvApplication.getInstance().getApplicationContext(), txt, Toast.LENGTH_SHORT).show();
    }

    public static void noTitle(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public static void fullScreen(Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static int getVersionCode() {
        String packageName = DfvApplication.getInstance().getPackageName();
        PackageInfo packageInfo;
        try {
            packageInfo = DfvApplication.getInstance().getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    public static String getVersionName(Context context) {
        String packageName = context.getPackageName();
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new AssertionError(e);
        }
    }

    public static int dp2px(int dp_value) {
        return (int) (dp_value * getDensity() + 0.5f);
    }

    /**
     * 获取屏幕密度
     **/
    public static float getDensity() {
        return DfvApplication.getInstance().getResources().getDisplayMetrics().density;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getSceenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getSceenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static StateListDrawable getSelectListDrawable(int normal, int selected) {
        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[]{android.R.attr.state_selected}, DfvApplication.getInstance().getResources().getDrawable(selected));
        listDrawable.addState(new int[]{}, DfvApplication.getInstance().getResources().getDrawable(normal));
        return listDrawable;
    }

    public static Drawable getRecCorner(int stockColor, int solidColor, int leftRadius, int rightRadius) {
        GradientDrawable drawable = new GradientDrawable();
        float[] radius = new float[]{leftRadius, leftRadius, rightRadius, rightRadius, rightRadius, rightRadius, leftRadius, leftRadius};
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadii(radius);
        drawable.setStroke(1, stockColor);
        drawable.setColor(solidColor);
        return drawable;
    }

    /***
     * 隐藏软键盘
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        ((InputMethodManager) DfvApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /***
     * 显示软键盘
     *
     * @param view
     */
    public static void showKeyboard(View view) {
        ((InputMethodManager) DfvApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE))
                .showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 获取屏幕尺寸
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenSize(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            return new Point(display.getWidth(), display.getHeight());
        }else{
            Point point = new Point();
            display.getSize(point);
            return point;
        }
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

}
