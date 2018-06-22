package com.qcp.dfv.mainview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qcp.dfv.R;
import com.qcp.dfv.ui.activity.PictureShareActivity;
import com.qcp.dfv.ui.activity.VideoShareActivity;
import com.qcp.dfv.ui.activity.ViolationReportActivity;

@SuppressLint("ResourceAsColor")
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "JPush";

    private FragmentOne oneFragment;
    private FragmentTwo twoFragment;
    //    private FragmentThree threeFragment;
    private FragmentFour fourFragment;
    private FragmentFive fiveFragment;

    private View oneLayout;
    private View twoLayout;
    private View threeLayout;
    private View fourLayout;
    private View fiveLayout;

    private ImageView oneImage;
    private ImageView twoImage;
    private ImageView fourImage;
    private ImageView fiveImage;


    private TextView oneText;
    private TextView twoText;
    private TextView fourText;
    private TextView fiveText;
    private ImageButton addIbt;


    private int current = 0;
    private int precurrent = 0;

    private FragmentManager fragmentManager;
    public static MainActivity instance;

    int msnumbers = 0;
    public static boolean isForeground = false;
    public static boolean out = false;
    private LocationManager manager;
    final String permission = Manifest.permission.CAMERA;  //相机权限
    private static final int CAMERA_REQUEST_CODE = 920;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        out = false;
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {

            oneFragment = (FragmentOne) fragmentManager.findFragmentByTag("oneFragment");
            twoFragment = (FragmentTwo) fragmentManager.findFragmentByTag("twoFragment");
            fourFragment = (FragmentFour) fragmentManager.findFragmentByTag("fourFragment");
            fiveFragment = (FragmentFive) fragmentManager.findFragmentByTag("fiveFragment");

        }


        WindowManager windowManager = getWindowManager();
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int sw = dm.widthPixels;
        int h = dm.heightPixels;

        initViews();
        setTabSelection(current);

        /**Android 7.0以上的方式  申请权限**/
        checkPremission();
    }

    private void initViews() {

        oneLayout = findViewById(R.id.one_layout);
        twoLayout = findViewById(R.id.two_layout);
        threeLayout = findViewById(R.id.three_layout);
        fourLayout = findViewById(R.id.four_layout);
        fiveLayout = findViewById(R.id.five_layout);


        oneImage = (ImageView) findViewById(R.id.one_image);
        twoImage = (ImageView) findViewById(R.id.two_image);
        fourImage = (ImageView) findViewById(R.id.four_image);
        fiveImage = (ImageView) findViewById(R.id.five_image);


        oneText = (TextView) findViewById(R.id.one_text);
        twoText = (TextView) findViewById(R.id.two_text);
        fourText = (TextView) findViewById(R.id.four_text);
        fiveText = (TextView) findViewById(R.id.five_text);
        addIbt = (ImageButton) findViewById(R.id.add_ibt);


        oneLayout.setOnClickListener(this);
        twoLayout.setOnClickListener(this);
        fourLayout.setOnClickListener(this);
        fiveLayout.setOnClickListener(this);
        addIbt.setOnClickListener(this);

    }

    private void checkPremission() {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {  //先判断是否被赋予权限，没有则申请权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {  //给出权限申请说明
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_REQUEST_CODE);
            } else { //直接申请权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, CAMERA_REQUEST_CODE); //申请权限，可同时申请多个权限，并根据用户是否赋予权限进行判断
            }
        }
    }


    @Override
    public void onClick(View v) {
        precurrent = current;
        int id = v.getId();
        if (id == R.id.one_layout) {
//            if (new AppParam().isLogin(this)) {
            current = 0;
            setTabSelection(0);
//            }else{
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
//            }
        } else if (id == R.id.two_layout) {
//            if (new AppParam().isLogin(this)) {
            current = 1;
            setTabSelection(1);
//            }else{
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
//            }
        } else if (id == R.id.add_ibt) {
//            if (new AppParam().isLogin(this)) {
//            current = 2;
//            setTabSelection(2);
            showDialog();
//            } else {
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
//            }

        } else if (id == R.id.four_layout) {
            current = 3;
            setTabSelection(3);
        } else if (id == R.id.five_layout) {
            current = 4;
            setTabSelection(4);
        }
    }


    private void setTabSelection(int index) {
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//设置fragment切换动画效果

        hideFragments(transaction);
        switch (index) {
            case 0:
                oneImage.setImageResource(R.drawable.car_2);
                oneText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                if (oneFragment == null) {
                    oneFragment = new FragmentOne();
                    transaction.add(R.id.content, oneFragment, "oneFragment");
                } else {
                    transaction.show(oneFragment);
                }
                break;
            case 1:
                twoImage.setImageResource(R.drawable.device_2);
                twoText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                if (twoFragment == null) {
                    twoFragment = new FragmentTwo();
                    transaction.add(R.id.content, twoFragment, "twoFragment");
                } else {
                    transaction.show(twoFragment);
                }
                break;

            case 2:
//                threeImage.setImageResource(R.drawable.product_2);
//                threeText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
//                if (threeFragment == null) {
//                    threeFragment = new FragmentThree();
//                    transaction.add(R.id.content, threeFragment, "threeFragment");
//                } else {
//                    transaction.show(threeFragment);
//                }
                break;
            case 3:
                fourImage.setImageResource(R.drawable.photo_2);
                fourText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                if (fourFragment == null) {
                    fourFragment = new FragmentFour();
                    transaction.add(R.id.content, fourFragment, "fourFragment");
                } else {
                    transaction.show(fourFragment);
                }
                break;
            case 4:
                fiveImage.setImageResource(R.drawable.me_2);
                fiveText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                if (fiveFragment == null) {
                    fiveFragment = new FragmentFive();
                    transaction.add(R.id.content, fiveFragment, "fiveFragment");
                } else {
                    transaction.show(fiveFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {

        oneImage.setImageResource(R.drawable.car_1);
        oneText.setTextColor(Color.parseColor("#82858b"));

        twoImage.setImageResource(R.drawable.device_1);
        twoText.setTextColor(Color.parseColor("#82858b"));

        fourImage.setImageResource(R.drawable.photo_1);
        fourText.setTextColor(Color.parseColor("#82858b"));

        fiveImage.setImageResource(R.drawable.me_1);
        fiveText.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {

        if (oneFragment != null) {
            transaction.hide(oneFragment);
        }
        if (twoFragment != null) {
//            if (new AppParam().isLogin(this)) {
            transaction.hide(twoFragment);
//            } else {
//                transaction.remove(twoFragment);
//            }
        }
//        if (threeFragment != null) {
//            transaction.hide(threeFragment);
//        }
        if (fourFragment != null) {
//            if (new AppParam().isLogin(this)) {
            transaction.hide(fourFragment);
//            } else {
//                transaction.remove(fourFragment);
//            }
        }
        if (fiveFragment != null) {
//            if (new AppParam().isLogin(this)) {
            transaction.hide(fiveFragment);
//            } else {
//                transaction.remove(fourFragment);
//            }
        }

    }

    boolean canload = true;


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    /**
     * 这里用来实现,再点一次返回键退出应用
     */
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
//            exitApp();
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    private void exitApp() {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.setTitle("退出提示");
        dlg.setMessage("是否确定退出？");
        dlg.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dlg.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dlg.cancel();
            }
        });
        dlg.show();
    }

    private void showDialog() {
        final Dialog dlg = new Dialog(this, R.style.custom_dialog_style);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.dialog_share, null);
        TextView photoTv = (TextView) layout.findViewById(R.id.photo_shate_tv);
        TextView videoTv = (TextView) layout.findViewById(R.id.video_shate_tv);
        TextView disciplineTv = (TextView) layout.findViewById(R.id.discipline_shate_tv);
        View exitV = layout.findViewById(R.id.exit_v);

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.exit_v:

                        break;
                    case R.id.photo_shate_tv:
                        Intent photo_intent = new Intent(MainActivity.this, PictureShareActivity.class);
                        startActivity(photo_intent);
                        break;
                    case R.id.video_shate_tv:
                        Intent video_intent = new Intent(MainActivity.this, VideoShareActivity.class);
                        startActivity(video_intent);
                        break;
                    case R.id.discipline_shate_tv:
                        Intent discipline_intent = new Intent(MainActivity.this, ViolationReportActivity.class);
                        startActivity(discipline_intent);
                        break;
                }
                dlg.dismiss();
            }
        };
        exitV.setOnClickListener(listener);
        photoTv.setOnClickListener(listener);
        videoTv.setOnClickListener(listener);
        disciplineTv.setOnClickListener(listener);

        dlg.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        Window w = dlg.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        w.getDecorView().setPadding(0, 0, 0, 0); //消除边距
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;//设置dialog在屏幕中显示的位置
        ColorDrawable dw = new ColorDrawable(0x99000000);
        w.setBackgroundDrawable(dw);
        dlg.setContentView(layout);
        dlg.show();
    }


}