package com.dmss.nytnews.Model.API;

import android.content.Context;
import android.util.Log;

import com.dmss.nytnews.Controller.Controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiServiceCalls {

    OkHttpClient client;

    public ApiServiceCalls() {
        client = new OkHttpClient();
        client.newBuilder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();
    }

    public void getDataNewMyPlace(String url,final Controller.ApiCallBack apiCallBack) {
        final Request request = new Request.Builder().url(url).build();
        Log.d("ServerUrl",url);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                apiCallBack.onErrorApiCall(e.fillInStackTrace().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200 || response.code() == 201) {
                    if (response != null) {
                        String result = response.body().string();
                        apiCallBack.onSuccessApiCall(result);
                    } else {
                        apiCallBack.onErrorApiCall("WebAPI Not Responding ");
                    }
                } else {
                    apiCallBack.onErrorApiCall("Internal Server Error...");
                }
            }
        });
    }
}
