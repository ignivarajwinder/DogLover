package biriinfotech.com.doglover.model;

import java.util.ArrayList;

/**
 * Created by Biri Infotech on 2/4/2018.
 */

public class ResponsePojo {

    boolean success;
    private String email_address;
    private  String password;
    private  String approval;
    private  String msg;
    private ArrayList<MenuModel> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<MenuModel> getData() {
        return data;
    }

    public void setData(ArrayList<MenuModel> data) {
        this.data = data;
    }
}
