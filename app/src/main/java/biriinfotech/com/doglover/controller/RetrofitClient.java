package biriinfotech.com.doglover.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import biriinfotech.com.doglover.interfaces.CallBack;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient<T> {

    public static final String BASE_URL = Config.getBaseURL();
    private static final RetrofitClient retrofitClient = new RetrofitClient();
    private static Retrofit retrofit = null;
    Context context;
    private String TAG = "response";
    private CallBack<T> callBack;

    public static RetrofitClient getInstance(Context context) {
        retrofitClient.context = context;
        return retrofitClient;
    }

    private RetrofitClient() {
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.getBaseURL())
                    .client(retrofitClient.provideOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
//        okhttpClientBuilder.cache(getCache());
        okhttpClientBuilder.addInterceptor(logging);
        okhttpClientBuilder.addInterceptor(new NetworkConnectionInterceptor(context) {
            @Override
            public boolean isInternetAvailable() {
                return retrofitClient.isInternetAvailable();
            }

            @Override
            public void onInternetUnavailable() {
                if (callBack != null) {
                    retrofitClient.callBack.onInternetUnavailable();
                }
            }

        });

        return okhttpClientBuilder.build();
    }

    public Cache getCache() {
        File httpCacheDirectory = new File(getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        return cache;
    }

    private String getCacheDir() {
        final String convertedFile = Environment.getExternalStorageDirectory() + "/cache";
        return convertedFile;
    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void execute(Call<T> call, final CallBack<T> callBack) {

        retrofitClient.callBack = callBack;

        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                Log.d(TAG, "onResponse: " + response.body());
                callBack.onSuccess(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                callBack.onFailure(call, throwable);
            }
        });
    }


}