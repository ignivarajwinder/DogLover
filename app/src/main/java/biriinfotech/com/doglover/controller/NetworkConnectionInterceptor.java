package biriinfotech.com.doglover.controller;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class NetworkConnectionInterceptor implements Interceptor {
    Context context;

    public NetworkConnectionInterceptor(Context context) {
        this.context = context;
    }

    public abstract boolean isInternetAvailable();

    public abstract void onInternetUnavailable();

    //    public abstract void onCacheUnavailable();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!isInternetAvailable()) {
            onInternetUnavailable();
        }
        return chain.proceed(request);
    }
}