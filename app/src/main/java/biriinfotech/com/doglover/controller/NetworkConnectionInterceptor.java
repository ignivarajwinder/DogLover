package biriinfotech.com.doglover.controller;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class NetworkConnectionInterceptor implements Interceptor {
    Context context;
    public NetworkConnectionInterceptor(Context context) {
    this.context=context;
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


//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Request request = chain.request();
//        Response originalResponse = chain.proceed(request);
//        if (!isInternetAvailable()) {
//            onInternetUnavailable();
//            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
//            originalResponse=originalResponse.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                    .build();
//            if (originalResponse.cacheResponse() == null) {
//                onCacheUnavailable();
//            }
//            // Sending side
//            byte[] data = "From cache 4-weeks".getBytes("UTF-8");
//            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
//
//
//            try {
////                new AsyncTask(EncryptDecrypt.getInstance().encrypt("From cache 4-weeks"),asynctaskMethodPlayer).execute();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (originalResponse!=null)
//                Log.d("Response",originalResponse.toString());
//
//            return originalResponse;
//        }
//        else
//        {
//            // Sending side
//            byte[] data = "From cache 10 sec".getBytes("UTF-8");
//            String base64 = Base64.encodeToString(data, Base64.DEFAULT);
//
//            try {
////                new AsyncTask(EncryptDecrypt.getInstance().encrypt("From cache 10 sec"),asynctaskMethodPlayer).execute();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            int maxAge = 10; // read from cache for 1 minute
//
//            originalResponse=originalResponse.newBuilder()
//                    .header("Cache-Control", "public, max-age=" + maxAge)
//                    .build();
//
//            if (originalResponse!=null)
//            Log.d("Response",originalResponse.toString());
//
//            return originalResponse;
//        }
////        return chain.proceed(request);
//    }






}