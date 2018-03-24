package biriinfotech.com.doglover.controller;

import biriinfotech.com.doglover.model.ResponsePojo;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit2.Call;

public interface ApiInterface {

    @Multipart
    @POST(ApiExecutor.LOGIN)
    void login(@Part("email_address") String email_address, @Part("password") String password, Callback<ResponsePojo> callback);

    @Multipart
    @POST(ApiExecutor.SIGNUP)
    void signUp(@Part("name") String name, @Part("email_address") String email_address, @Part("password") String password, Callback<ResponsePojo> callback);

    @Multipart
    @POST("/lostpassword.php")
    void forgotPassword(@Part("email_address") String email_address, Callback<ResponsePojo> callback);


    @Multipart
    @POST("/sub_main_menu.php")
    void getSubMenuData(@Part("main_menu_id") String main_menu_id, Callback<ResponsePojo> callback);


    @Multipart
    @POST("login.php")
    Call<ResponsePojo> login(@Part("email_address") String email_address, @Part("password") String password);

    @Multipart
    @POST("lostpassword.php")
    Call<ResponsePojo> forgotPassword(@Part("email_address") String email_address);

    @Multipart
    @POST("register.php")
    Call<ResponsePojo> signUp(@Part("name") String name, @Part("email_address") String email_address, @Part("password") String password);

    //    @retrofit2.http.Multipart
    @retrofit2.http.GET("sub_main_menu.php")
    Call<ResponsePojo> getSubMenuData(@retrofit2.http.Query("main_menu_id") String main_menu_id);

    @GET("/main_menu.php")
    void getMenuData(Callback<ResponsePojo> callback);

    @retrofit2.http.GET("main_menu.php")
    Call<ResponsePojo> getMenuData();


}