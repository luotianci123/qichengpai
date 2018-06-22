package com.qcp.dfv.net;

/**
 * Created by w.gs on 2017/1/6.
 */

public class UrlUtils {

    public static String addPathParam(String url, String paramName, String paramValue) {
        return url.replace("{" + paramName + "}", paramValue);
    }

}
