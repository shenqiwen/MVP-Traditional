package com.sqw.mvp_traditional.utils;


import android.os.Handler;
import android.os.Looper;

import com.sqw.mvp_traditional.GlobalConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp3Util {

    private static OkHttp3Util mOkHttp3Util;
    private final OkHttpClient mOkHttpClient;
    private final Handler mHandler;

    private OkHttp3Util() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.newBuilder()
                .connectTimeout(GlobalConstants.OKHTTP3_CONNECTTIMEOUT, TimeUnit.SECONDS)
                .readTimeout(GlobalConstants.OKHTTP3_READTIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(GlobalConstants.OKHTTP3_WRITETIMEOUT, TimeUnit.SECONDS)
                .build();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttp3Util getInstance() {
        if (mOkHttp3Util == null) {
            synchronized (OkHttp3Util.class) {
                if (mOkHttp3Util == null) {
                    mOkHttp3Util = new OkHttp3Util();
                }
            }
        }
        return mOkHttp3Util;
    }

    public OkHttpClient getOkHttp3Client(){
        return  mOkHttpClient ;
    }

    class StringCallBack implements Callback {
        private HttpCallBack httpCallBack;
        private Request request;

        public StringCallBack(Request request, HttpCallBack httpCallBack) {
            this.request = request;
            this.httpCallBack = httpCallBack;
        }

        @Override
        public void onFailure(Call call, final IOException e) {
            if (httpCallBack != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallBack.onError(request, e);
                    }
                });
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String result = response.body().string();
            if (httpCallBack != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallBack.onSuccess(request, result);
                    }
                });
            }
        }
    }


    public void asyncGet(Object tag,String url, HttpCallBack httpCallBack) {
        Request request = new Request.Builder().tag(tag).url(url).build();
        mOkHttpClient.newCall(request).enqueue(new StringCallBack(request, httpCallBack));
    }


    public void asyncPost(Object tag,String url, FormBody formBody, HttpCallBack httpCallBack) {
        Request request = new Request.Builder().tag(tag).url(url).post(formBody).build();
        mOkHttpClient.newCall(request).enqueue(new StringCallBack(request, httpCallBack));
    }

    /** 根据Tag取消请求 */
    public void cancelTag(Object tag) {
        if (tag == null) return;
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    /** 根据Tag取消请求 */
    public static void cancelTag(OkHttpClient client, Object tag) {
        if (client == null || tag == null) return;
        for (Call call : client.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : client.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    /** 取消所有请求请求 */
    public void cancelAll() {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            call.cancel();
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            call.cancel();
        }
    }

    /** 取消所有请求请求 */
    public static void cancelAll(OkHttpClient client) {
        if (client == null) return;
        for (Call call : client.dispatcher().queuedCalls()) {
            call.cancel();
        }
        for (Call call : client.dispatcher().runningCalls()) {
            call.cancel();
        }
    }


    public interface HttpCallBack {
        void onError(Request request, IOException e);

        void onSuccess(Request request, String result);
    }

}
