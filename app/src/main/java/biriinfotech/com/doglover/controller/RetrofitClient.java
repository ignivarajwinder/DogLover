package biriinfotech.com.doglover.controller;

import android.content.Context;
import android.util.Log;

import biriinfotech.com.doglover.interfaces.CallBack;
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
    private String TAG="response";

    public static RetrofitClient getInstance(Context context) {
        retrofitClient.context=context;
        return retrofitClient;
    }

    private RetrofitClient() {
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://cloudwebsolutions.in/anotherapi/project/webservices/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public void execute(Call<T> call, final CallBack<T> callBack) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                Log.d(TAG, "onResponse: "+response.body());
                callBack.onSuccess(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                callBack.onFailure(call, throwable);
            }
        });
    }


}