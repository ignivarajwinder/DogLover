package biriinfotech.com.doglover;

import android.content.Context;

import com.jakewharton.retrofit.Ok3Client;

import java.util.concurrent.TimeUnit;

import biriinfotech.com.doglover.controller.Config;
import okhttp3.OkHttpClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;


/**
 * Created by cl-macmini-38 on 18/05/16.
 */
public class RetrofitClient {

    private static final int CONNECT_TIMEOUT_MILLIS = 30 * 1000; // 60s
    private static final int READ_TIMEOUT_MILLIS = 30 * 1000; // 60s
    private static RestAdapter adapter;
    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    
    private static RequestInterceptor newInterceptor(final Context context) {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
//                String token = PreferenceHandler.readString(context,PreferenceHandler.PREF_KEY_AUTH_TOKEN, "");
//                String token = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjU5MjI5MDEwYzAwNjk4MzYzYzViNTFjMSIsImVtYWlsIjoiYmh1bGxhcl9lMUBtYWlsaW5hdG9yLmNvbSIsImZpcnN0X25hbWUiOiJjYmR2diIsImxhc3RfbmFtZSI6InNodnNodmQiLCJyb2xlIjoxLCJsb2dpbl90eXBlIjoxLCJpc19jb25maXJtZWQiOnRydWUsImlhdCI6MTQ5NjAzODEzNSwiZXhwIjoxNDk2MjEwOTM1fQ.LKgeT_I0adAYMxoDatOdhGuhJG1tvBYPtqN7bRXkovUh-Z2pUvU_Vn6tx1z3BiQ4R_3vjKOmOjCtb4-a-5QDmA";
//                if (token != null) {
//                    request.addHeader("x-logintoken", "" + token);
//                }
            }
        };
        return requestInterceptor;

    }

    static RestAdapter.Builder builder;
    static RestAdapter.Builder getBuilder(Context context) {
        if (builder == null) {
            builder = new RestAdapter.Builder()
                    .setEndpoint(Config.getBaseURL())
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor(newInterceptor(context));
        }
        return builder;
    }

    public static <S> S createService(Class<S> serviceClass, Context context) {
        getBuilder(context);
        okHttpClient.readTimeout(READ_TIMEOUT_MILLIS, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.SECONDS);
        builder.setClient(new Ok3Client(okHttpClient.build()));
        if (adapter == null)
            adapter = builder.build();

        return adapter.create(serviceClass);
    }

}

