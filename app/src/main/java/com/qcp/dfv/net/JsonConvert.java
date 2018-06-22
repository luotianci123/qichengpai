package com.qcp.dfv.net;

import com.qcp.dfv.pub.L;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.convert.Converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;

import okhttp3.Response;


public class JsonConvert<T> implements Converter<T> {

    private static Gson gson = new Gson();

    private Type type;

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public T convertSuccess(Response response) throws Exception {

        if (type == null) {
            //以下代码是通过泛型解析实际参数,泛型必须传
            Type genType = getClass().getGenericSuperclass();
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            type = params[0];
        }

        TypeAdapter<T> adapter = (TypeAdapter<T>) gson.getAdapter(TypeToken.get(type));


        if (response == null || response.body() == null) {
            return adapter.fromJson("{}");
        }

        String jsonStr = response.body().string();
        L.v("http", "response--->>" + response.code() + " " + response.request().url().toString() + "\n" + jsonStr);

        if (jsonStr == null || jsonStr.length() == 0) {
            jsonStr = "{}";
        }else {
            if (!jsonStr.startsWith("{") && !jsonStr.startsWith("[")) {
                L.e("Response body " + URLEncoder.encode(jsonStr, "utf-8"));
                if (jsonStr.contains("\n")) {
                    jsonStr = jsonStr.replace("\n", "");
                }
                jsonStr = String.format("{\"result\":%s}", jsonStr);
                L.w("容错处理后：" + jsonStr);
            }
        }

        if (response.code() != 200) {
            throw new RuntimeException(jsonStr);
        }

        return adapter.fromJson(jsonStr);

    }
}