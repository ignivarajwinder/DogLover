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
    void login(@Part("email_address")String email_address, @Part("password")String password, Callback<ResponsePojo> callback);

    @Multipart
    @POST(ApiExecutor.SIGNUP)
    void signUp(@Part("name")String name, @Part("email_address")String email_address, @Part("password")String password, Callback<ResponsePojo> callback);

    @Multipart
    @POST("/sub_main_menu.php")
    void getSubMenuData(@Part("main_menu_id") String main_menu_id, Callback<ResponsePojo> callback);


    @Multipart
    @POST(ApiExecutor.LOGIN)
    Call<ResponsePojo> login(@Part("email_address") String email_address, @Part("password") String password);

    @Multipart
    @POST(ApiExecutor.SIGNUP)
    Call<ResponsePojo> signUp(@Part("name") String name, @Part("email_address") String email_address, @Part("password") String password);

    @Multipart
    @POST("/sub_main_menu.php")
    Call<ResponsePojo> getSubMenuData(@Part("main_menu_id") String main_menu_id);

    @GET("/main_menu.php")
    void getMenuData( Callback<ResponsePojo> callback);

}