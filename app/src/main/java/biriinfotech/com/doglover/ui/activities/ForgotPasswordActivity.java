package biriinfotech.com.doglover.ui.activities;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.RetrofitClient;
import biriinfotech.com.doglover.controller.ApiExecutor;
import biriinfotech.com.doglover.controller.ApiInterface;
import biriinfotech.com.doglover.model.ResponsePojo;
import biriinfotech.com.doglover.ui.customviews.CallProgressWheel;
import biriinfotech.com.doglover.utils.BlurBuilder;
import biriinfotech.com.doglover.utils.Utility;
import biriinfotech.com.doglover.utils.Validation;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by Biri Infotech on 2/26/2018.
 */

public class ForgotPasswordActivity extends BaseActivity {

    private TextInputLayout mTilEmail;
    private AppCompatEditText mEtEmail;
    private AppCompatTextView mTvSubmit;
    private String LOG_TAG = "ForgotPasswordActivity";
    Toolbar toolbar;
    private LinearLayout mLlMain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setUpToolbar();
        setUpLayout();
        setDataInViewObjects();
    }

    @Override
    AppCompatActivity setInstance() {
        return this;
    }

    @Override
    void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Forgot Password");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    void setUpLayout() {
        mLlMain = (LinearLayout) findViewById(R.id.ll_main);
        mTvSubmit = (AppCompatTextView) findViewById(R.id.tv_submit);
        mTilEmail = (TextInputLayout) findViewById(R.id.mTilEmail);
        mEtEmail = (AppCompatEditText) findViewById(R.id.et_email);
        BitmapDrawable background = new BitmapDrawable(BlurBuilder.blur(this, BitmapFactory.decodeResource(getResources(), R.drawable.dancing_dog)));
        mLlMain.setBackgroundDrawable(background);
        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(Validation.isValidForgotPassword(ForgotPasswordActivity.this, mEtEmail, mTilEmail) == null))
                    callApi();
            }
        });
    }

    private void callApi() {
        try {
            ApiInterface mWebApi = RetrofitClient.createService(ApiInterface.class, this);
            CallProgressWheel.showLoadingDialog(this, "Loading...");
            HashMap<String, String> params = new HashMap<>();
            params.put(ApiExecutor.email_address, mEtEmail.getText().toString().trim());

            Log.d("Parameters----", "" + params.toString());

            mWebApi.forgotPassword(params.get(ApiExecutor.email_address).toString().trim(), new Callback<ResponsePojo>() {
                @Override
                public void success(ResponsePojo commonResponse, retrofit.client.Response response) {

                    Log.d(LOG_TAG, "Success " + commonResponse.toString());

                    try {
                        if (commonResponse.isSuccess()) {
                            Utility.showToastMessageShort(ForgotPasswordActivity.this, commonResponse.getMsg().toString());

                        } else {
                            Utility.showToastMessageShort(ForgotPasswordActivity.this, commonResponse.getMsg().toString());
                        }
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                        finish();
                    }
                    CallProgressWheel.dismissLoadingDialog();
                }

                @Override
                public void failure(RetrofitError error) {
                    try {
//                            Utility.showToastMessageShort(ForgotPasswordActivity.this, getResources().getString(R.string.server_error));
                        Utility.showToastMessageShort(ForgotPasswordActivity.this, error.getMessage().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    CallProgressWheel.dismissLoadingDialog();
                }
            });
        } catch (Exception e) {
        }
    }

    @Override
    void setDataInViewObjects() {

    }
}
