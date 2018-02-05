package biriinfotech.com.doglover.controller;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

import biriinfotech.com.doglover.interfaces.CallBack;
import biriinfotech.com.doglover.model.ResponsePojo;

/**
 * Created by Biri Infotech on 2/4/2018.
 */

public class ApiExecutor {
    private static final ApiExecutor apiExecutor = new ApiExecutor();
    public static final String LOGIN = "/login.php";
    public static final String SIGNUP = "/register.php";
    public static String name = "name";
    public static String email_address = "email_address";
    public static String password = "password";
    private String TAG = "params";
    Context context;
    HashMap<String, Object> params;

    public static ApiExecutor getInstance() {
        return apiExecutor;
    }

    public static ApiExecutor getInstance(Context context) {
        apiExecutor.context = context;
        return apiExecutor;
    }

    private ApiExecutor() {
    }

    public ApiExecutor setParams(HashMap<String, Object> params) {
        apiExecutor.params = params;
        Log.d(TAG, params.toString());
        return apiExecutor;
    }

    public void login(final CallBack<ResponsePojo> callBack) {
        RetrofitClient.getInstance(context)
                .execute(RetrofitClient.getClient()
                        .create(ApiInterface.class)
                        .login(params.get(ApiExecutor.email_address).toString(), params.get(ApiExecutor.password).toString()), callBack);
    }

    public void signUp(CallBack<ResponsePojo> callBack) {
        RetrofitClient.getInstance(context)
                .execute(RetrofitClient.getClient()
                        .create(ApiInterface.class)
                        .signUp(params.get(ApiExecutor.name).toString().trim(), params.get(ApiExecutor.email_address).toString().trim(), params.get(ApiExecutor.password).toString().trim()), callBack);

    }
}
