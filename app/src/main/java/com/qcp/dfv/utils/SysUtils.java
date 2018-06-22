package com.qcp.dfv.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.telephony.TelephonyManager;
import android.text.TextUtils;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by w.gs on 2016/10/12.
 */

public class SysUtils {


//    public static void installApk(Context context, String apkPath) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setDataAndType(Uri.fromFile(new File(apkPath)), "application/vnd.android.package-archive");
//        context.startActivity(intent);
//    }

    public static void installApk(Context context, String apkPath) {
        if (context == null || TextUtils.isEmpty(apkPath)) {
            return;
        }
        File file = new File(apkPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);

        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            //provider authorities
            Uri apkUri = FileProvider.getUriForFile(context, "com.recorder.dfv.fileprovider", file);
            //Granting Temporary Permissions to a URI
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class<?> clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                if (rest == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Exception e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }

    public static String getDeviceID(Context context) {
        try {
            //org.json.JSONObject json = new org.json.JSONObject();
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = null;
            if (checkPermission(context, android.Manifest.permission.READ_PHONE_STATE)) {
                device_id = tm.getDeviceId();
            }
//            String mac = null;
//            FileReader fstream = null;
//            try {
//                fstream = new FileReader("/sys/class/net/wlan0/address");
//            } catch (FileNotFoundException e) {
//                fstream = new FileReader("/sys/class/net/eth0/address");
//            }
//            BufferedReader in = null;
//            if (fstream != null) {
//                try {
//                    in = new BufferedReader(fstream, 1024);
//                    mac = in.readLine();
//                } catch (IOException e) {
//                } finally {
//                    if (fstream != null) {
//                        try {
//                            fstream.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (in != null) {
//                        try {
//                            in.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//            //json.put("mac", mac);
//            if (TextUtils.isEmpty(device_id)) {
//                device_id = mac;
//            }
//            if (TextUtils.isEmpty(device_id)) {
//                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
//                        android.provider.Settings.Secure.ANDROID_ID);
//            }
//            json.put("device_id", device_id);
//            return json.toString();
            return device_id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getICCID(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        String imei = tm.getDeviceId();//取出IMEI
//        String tel = tm.getLine1Number();//取出MSISDN，很可能为空 手机号
//        String imei = tm.getSubscriberId();//取出IMSI
        String iccid = null;//取出ICCID
        try {
            iccid = tm.getSimSerialNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (TextUtils.isEmpty(iccid)) {
//            if (L.debug) {
//                iccid = "8986031641201628793";
//                iccid = "8986031641201902324";//测试号
//                iccid = "8986031641201903114";//T3车型
//                iccid = "8986031642202854272";

//            }
        } else {
            if (iccid.length() > 19) {
                iccid = iccid.substring(0, 19);
            }
        }

        return iccid;
    }

    public static String getMSISDN(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        String imei = tm.getDeviceId();//取出IMEI
//        String tel = tm.getLine1Number();//取出MSISDN，很可能为空 手机号
//        String imei = tm.getSubscriberId();//取出IMSI
        //String iccid = tm.getSimSerialNumber();//取出ICCID
        return tm.getLine1Number();
    }


    public static String printSystemInfo(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String str = "deviceId=" + tm.getDeviceId() + " DeviceSoftwareVersion=" + tm.getDeviceSoftwareVersion()
                + " Line1Number=" + tm.getLine1Number() + " SimSerialNumber=" + tm.getSimSerialNumber() + " SimOperatorName=" + tm.getSimOperatorName()
                + " SimState=" + tm.getSimState() + " SubscriberId=" + tm.getSubscriberId() + " hasIccCard=" + tm.hasIccCard()
                + " SimCountryIso=" + tm.getSimCountryIso() + " SimOperator=" + tm.getSimOperator();

        return str;
//        /*
//   * 电话状态：
//   * 1.tm.CALL_STATE_IDLE=0          无活动
//   * 2.tm.CALL_STATE_RINGING=1  响铃
//   * 3.tm.CALL_STATE_OFFHOOK=2  摘机
//   */
//        tm.getCallState();//int
//  /*
//   * 电话方位：
//   *
//   */
//        tm.getCellLocation();//CellLocation
//  /*
//   * 唯一的设备ID：
//   * GSM手机的 IMEI 和 CDMA手机的 MEID.
//   * Return null if device ID is not available.
//   */
//        tm.getDeviceId();//String
//  /*
//   * 设备的软件版本号：
//   * 例如：the IMEI/SV(software version) for GSM phones.
//   * Return null if the software version is not available.
//   */
//        tm.getDeviceSoftwareVersion();//String
//  /*
//   * 手机号：
//   * GSM手机的 MSISDN.
//   * Return null if it is unavailable.
//   */
//        tm.getLine1Number();//String
//  /*
//   * 附近的电话的信息:
//   * 类型：List
//   * 需要权限：android.Manifest.permission#ACCESS_COARSE_UPDATES
//   */
//        tm.getNeighboringCellInfo();//List
//  /*
//   * 获取ISO标准的国家码，即国际长途区号。
//   * 注意：仅当用户已在网络注册后有效。
//   *       在CDMA网络中结果也许不可靠。
//   */
//        tm.getNetworkCountryIso();//String
//  /*
//   * MCC+MNC(mobile country code + mobile network code)
//   * 注意：仅当用户已在网络注册时有效。
//   *    在CDMA网络中结果也许不可靠。
//   */
//        tm.getNetworkOperator();//String
//  /*
//   * 按照字母次序的current registered operator(当前已注册的用户)的名字
//   * 注意：仅当用户已在网络注册时有效。
//   *    在CDMA网络中结果也许不可靠。
//   */
//        tm.getNetworkOperatorName();//String
//  /*
//   * 当前使用的网络类型：
//   * 例如： NETWORK_TYPE_UNKNOWN  网络类型未知  0
//     NETWORK_TYPE_GPRS     GPRS网络  1
//     NETWORK_TYPE_EDGE     EDGE网络  2
//     NETWORK_TYPE_UMTS     UMTS网络  3
//     NETWORK_TYPE_HSDPA    HSDPA网络  8
//     NETWORK_TYPE_HSUPA    HSUPA网络  9
//     NETWORK_TYPE_HSPA     HSPA网络  10
//     NETWORK_TYPE_CDMA     CDMA网络,IS95A 或 IS95B.  4
//     NETWORK_TYPE_EVDO_0   EVDO网络, revision 0.  5
//     NETWORK_TYPE_EVDO_A   EVDO网络, revision A.  6
//     NETWORK_TYPE_1xRTT    1xRTT网络  7
//   */
//        tm.getNetworkType();//int
//  /*
//   * 手机类型：
//   * 例如： PHONE_TYPE_NONE  无信号
//     PHONE_TYPE_GSM   GSM信号
//     PHONE_TYPE_CDMA  CDMA信号
//   */
//        tm.getPhoneType();//int
//  /*
//   * Returns the ISO country code equivalent for the SIM provider's country code.
//   * 获取ISO国家码，相当于提供SIM卡的国家码。
//   *
//   */
//        tm.getSimCountryIso();//String
//  /*
//   * Returns the MCC+MNC (mobile country code + mobile network code) of the provider of the SIM. 5 or 6 decimal digits.
//   * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字.
//   * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
//   */
//        tm.getSimOperator();//String
//  /*
//   * 服务商名称：
//   * 例如：中国移动、联通
//   * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
//   */
//        tm.getSimOperatorName();//String
//  /*
//   * SIM卡的序列号：
//   * 需要权限：READ_PHONE_STATE
//   */
//        tm.getSimSerialNumber();//String
//  /*
//   * SIM的状态信息：
//   *  SIM_STATE_UNKNOWN          未知状态 0
//   SIM_STATE_ABSENT           没插卡 1
//   SIM_STATE_PIN_REQUIRED     锁定状态，需要用户的PIN码解锁 2
//   SIM_STATE_PUK_REQUIRED     锁定状态，需要用户的PUK码解锁 3
//   SIM_STATE_NETWORK_LOCKED   锁定状态，需要网络的PIN码解锁 4
//   SIM_STATE_READY            就绪状态 5
//   */
//        tm.getSimState();//int
//  /*
//   * 唯一的用户ID：
//   * 例如：IMEI(国际移动用户识别码) for a GSM phone.
//   * 需要权限：READ_PHONE_STATE
//   */
//        tm.getSubscriberId();//String
//  /*
//   * 取得和语音邮件相关的标签，即为识别符
//   * 需要权限：READ_PHONE_STATE
//   */
//        tm.getVoiceMailAlphaTag();//String
//  /*
//   * 获取语音邮件号码：
//   * 需要权限：READ_PHONE_STATE
//   */
//        tm.getVoiceMailNumber();//String
//  /*
//   * ICC卡是否存在
//   */
//        tm.hasIccCard();//boolean
//  /*
//   * 是否漫游:
//   * (在GSM用途下)
//   */
//        tm.isNetworkRoaming();//
    }


    public static String getOsInfo() {

        String str = "品牌：" + Build.BRAND + " 主板：" + Build.BOARD + " 设备：" + Build.DEVICE
                + " DISPLAY：" + Build.DISPLAY + " HARDWARE：" + Build.HARDWARE + " 型号：" + Build.MODEL
                + " 版本名：" + Build.VERSION.RELEASE + " 版本号：" + Build.VERSION.SDK_INT + " 厂商：" + Build.MANUFACTURER
                + " PRODUCT：" + Build.PRODUCT + " 系列号：" + Build.SERIAL;

        return str;
    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     *
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 判断是wifi还是3g网络
     *
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
        if (networkINfo != null
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * 读取设备ID
     *
     * @return
     */
    public static String getCID() {

        DataInputStream dis = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/sys/block/mmcblk0/device/cid");

//            fis = new FileInputStream("/sys/devices/platform/emmc/mmc_host/mmc0/mmc0:0001/cid");//诺威达
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);

            String str = new String(bytes, "utf-8");
            if (str != null) {
                str = str.replace("\n", "").trim();
            }
            return str;

        } catch (IOException e) {
            e.printStackTrace();
//            ReportError.reportError(e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

//        if (L.debug) {
//            return "150100414a4e423452070540fecca3a1";
//            return "11010030313647373000c19fac65630d";
//            return "11010030313647373000272ad4438361";//政企用户cid
//        }


        return "";
    }


}
