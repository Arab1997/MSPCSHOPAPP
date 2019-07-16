package com.ukuya.mspc.api;

import com.ukuya.mspc.model.User;
import com.ukuya.mspc.utils.PreferenceUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {
    private PreferenceUtil preferenceUtil;

    public ApiInterceptor(PreferenceUtil preferenceUtil) {
        this.preferenceUtil = preferenceUtil;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = getRequest(original);
        return chain.proceed(request);

    }

    private Request getRequest(Request original) {
        //TODO for auth
        User user = preferenceUtil.getUser();

        Request.Builder builder = original.newBuilder();
        builder.header("content-type", "application/json");
        builder.method(original.method(), original.body());
        return builder.build();
    }


}
