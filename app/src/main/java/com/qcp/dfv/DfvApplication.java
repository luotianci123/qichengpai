package com.qcp.dfv;

import android.app.Activity;
import android.app.Application;

import com.lzy.okgo.OkGo;
import com.qcp.dfv.pub.CrashHandler;
import com.socks.library.KLog;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.LinkedList;
import java.util.List;

 /*
                           _ooOoo_
                          o8888888o
                          88" . "88
                          (| -_- |)
                          O\  =  /O
                       ____/`---'\____
                     .'  \\|     |//  `.
                    /  \\|||  :  |||//  \
                   /  _||||| -:- |||||-  \
                   |   | \\\  -  /// |   |
                   | \_|  ''\---/''  |   |
                   \  .-\__  `-`  ___/-. /
                 ___`. .'  /--.--\  `. . __
              ."" '<  `.___\_<|>_/___.'  >'"".
             | | :  `- \`.;`\ _ /`;.`/ - ` : | |
             \  \ `-.   \_ __\ /__ _/   .-` /  /
        ======`-.____`-.___\_____/___.-`____.-'======
                           `=---='
        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                 佛祖保佑       永无BUG
        */
/**
 * Created by panjunquan
 */

public class DfvApplication extends Application {


    private static DfvApplication instance;
    public static int delete_img = 0;
    /**
     * Activity列表
     */
    private List<Activity> activityList = new LinkedList<Activity>();

    public static DfvApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        OkGo.init(this);
        KLog.init(BuildConfig.LOG_DEBUG);
        if (BuildConfig.LOG_DEBUG) {
            CrashHandler.getInstance().init(this);
        }

        ZXingLibrary.initDisplayOpinion(this);
    }

    /**
     * 保存Activity到现有列表中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }


    /**
     * 关闭保存的Activity
     */
    public void exit() {
        if (activityList != null) {
            Activity activity;

            for (int i = 0; i < activityList.size(); i++) {
                activity = activityList.get(i);

                if (activity != null) {
                    if (!activity.isFinishing()) {
                        activity.finish();
                    }

                    activity = null;
                }

                activityList.remove(i);
                i--;
            }
        }
    }


}
