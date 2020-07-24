package com.github.lihang941.tool.common.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * OK Http
 *
 * @author : lihang1329@gmail.com
 */
public class HttpTool {

    private OkHttpClient okHttpClient;

    public HttpTool(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public <T> T execute(Request request, ResponseHandle<T> handle, ResponseFilter... filter) throws IOException {
        return handle.handle(okHttpClient.newCall(request), filter);
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public HttpTool setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }

    public interface ResponseFilter {
        void filter(Response response) throws IOException;
    }


    /**
     * 转换器
     *
     * @author : lihang1329@gmail.com
     */
    public interface ResponseHandle<T> {

        default T handle(Call call, ResponseFilter[] filters) throws IOException {
            Response response = null;
            try {
                response = call.execute();
                if (filters.length > 0) {
                    for (ResponseFilter filter : filters) {
                        filter.filter(response);
                    }
                }
                return convert(response);
            } finally {
                if (response != null)
                    response.close();
            }
        }

        T convert(Response response) throws IOException;

    }


    public static ResponseHandle<JSONObject> jsonObjectResponseHandle() {
        return (response) -> JSON.parseObject(response.body().string());
    }

    public static <T> ResponseHandle<T> parseObjectResponseHandle(Class<T> tClass) {
        return (response) -> JSONObject.parseObject(response.body().string(), tClass);
    }


    public static ResponseHandle<JSONArray> jsonArrayResponseHandle() {
        return (response) -> JSON.parseArray(response.body().string());
    }


    public static ResponseHandle<Object> defaultResponseHandle() {
        return (response) -> null;
    }

    public static ResponseHandle<String> stringResponseHandle() {
        return (response) -> response.body().string();
    }

}
