package com.qcp.dfv.base;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by w.gs on 2016/10/27.
 */

public class BaseBean implements Serializable {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
