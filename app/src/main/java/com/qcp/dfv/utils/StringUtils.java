package com.qcp.dfv.utils;

import android.text.TextUtils;

/**
 * Created by w.gs on 2016/9/23.
 */
public class StringUtils {

    public static String pop(String first, String second) {
        return TextUtils.isEmpty(first) ? second : first;
    }

    public static String skipNull(String str) {
        return str == null ? "" : str;
    }

    /***
     * 除了前三位和后四位 中间位数置为*
     *
     * @param str
     * @return
     */
    public static String getHideMidStr(String str, int preHideLength, int postHideLength) {
        if (str == null) {
            return null;
        }

        StringBuffer result = new StringBuffer();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            result.append((i < preHideLength || i >= len - postHideLength) ? str.charAt(i) : "*");
        }
        return result.toString();
    }
}
