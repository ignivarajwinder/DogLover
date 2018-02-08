package biriinfotech.com.doglover.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.util.HashMap;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.RetrofitClient;
import biriinfotech.com.doglover.controller.ApiExecutor;
import biriinfotech.com.doglover.controller.ApiInterface;
import biriinfotech.com.doglover.model.ResponsePojo;
import biriinfotech.com.doglover.ui.customviews.CallProgressWheel;
import biriinfotech.com.doglover.utils.Constants;
import biriinfotech.com.doglover.utils.Utility;
import biriinfotech.com.doglover.utils.Validation;
import retrofit.Callback;
import retrofit.RetrofitError;

//import biriinfotech.com.doglover.controller.RetrofitClient;

public class LoginSignUpActivity extends BaseActivity implements OnClickListener {


    private AppCompatEditText mEtEmail, mEtPassword, mEtName;
    private AppCompatTextView mTvLoginText, mTvCreateAccountPageText, mEmailSignInButton;
    private TextInputLayout mTilName, mTilEmail, mTilPassword;
    String intentActivity;
    Toolbar mToolbar;
    private String LOG_TAG = "LoginSignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_sign_up);
        } catch (Exception e) {
            Utility.showException(LoginSignUpActivity.this, e);
        }
    }

    @Override
    AppCompatActivity setInstance() {
        return LoginSignUpActivity.this;
    }

    @Override
    void setUpToolbar() {
        intentActivity = getIntent().getStringExtra(Constants.ACTIVITY_NAME) == null ? "" : getIntent().getStringExtra(Constants.ACTIVITY_NAME);
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
////        getSupportActionBar().setTitle(intentActivity.equals(Constants.LOGIN) ? getResources().getString(R.string.loginTitle) : getResources().getString(R.string.signUp));
//        getSupportActionBar().setTitle("");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setNavigationOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
    }

    @Override
    void setUpLayout() {
        mTilName = (TextInputLayout) findViewById(R.id.mTilName);
        mTilEmail = (TextInputLayout) findViewById(R.id.mTilEmail);
        mTilPassword = (TextInputLayout) findViewById(R.id.mTilPassword);
        mEtName = (AppCompatEditText) findViewById(R.id.et_name);
        mEtEmail = (AppCompatEditText) findViewById(R.id.email);
        mEtPassword = (AppCompatEditText) findViewById(R.id.password);
        mTvLoginText = (AppCompatTextView) findViewById(R.id.tv_login_text);

        Utility.onChangeClearButtonVisible(LoginSignUpActivity.this, mEtName, mTilName);
        Utility.onChangeClearButtonVisible(LoginSignUpActivity.this, mEtEmail, mTilEmail);
        Utility.onChangeClearButtonVisible(LoginSignUpActivity.this, mEtPassword, mTilPassword);


        mTvCreateAccountPageText = (AppCompatTextView) findViewById(R.id.tv_create_account_page_text);
       Utility.setTypeface(LoginSignUpActivity.this,mTvCreateAccountPageText,null,false);
        mEmailSignInButton = (AppCompatTextView) findViewById(R.id.tv_lets_do_this);
        mEmailSignInButton.setOnClickListener(this);
        mEmailSignInButton.setTag(intentActivity == null ? "" : intentActivity);
        mTvLoginText.setText(intentActivity.equals(Constants.LOGIN) ? getResources().getString(R.string.login) : getResources().getString(R.string.create_an_account));
        mTvCreateAccountPageText.setVisibility(intentActivity.equals(Constants.LOGIN) ? View.GONE : View.VISIBLE);
        mEtName.setVisibility(intentActivity.equals(Constants.LOGIN) ? View.GONE : View.VISIBLE);
        mTilName.setVisibility(intentActivity.equals(Constants.LOGIN) ? View.GONE : View.VISIBLE);
        mEtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    callMethod(mEmailSignInButton.getTag().toString());
                    return true;
                }
                return false;
            }
        });

//         <com.promocodeclub.AutoScrollViewPager
//        android:id="@+id/pager"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:background="@color/white"
//
//                />


    }

    @Override
    void setDataInViewObjects() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_lets_do_this:
                callMethod(mEmailSignInButton.getTag().toString());
                break;
        }
    }

    private void callMethod(String tag) {
        try {
            switch (tag) {
                case Constants.LOGIN:
                    if (!(Validation.isValidSignIn(LoginSignUpActivity.this, mEtEmail, mTilEmail, mEtPassword, mTilPassword)==null))
                        login();
                    break;
                case Constants.SIGNUP:
                    if (!(Validation.isValidSignUp(LoginSignUpActivity.this, mEtName, mTilName, mEtEmail, mTilEmail, mEtPassword, mTilPassword)==null))
                        signUp();
                    break;
            }
        } catch (Exception e) {
            Utility.showException(LoginSignUpActivity.this, e);
        }

    }


    private void setloginScreen() {
        try {
            mEtName.setText("");
            mEtName.setVisibility(View.GONE);
            mTilName.setVisibility(View.GONE);
            mTvCreateAccountPageText.setVisibility(View.GONE);
            mEtPassword.setText("");
            mTvLoginText.setText(getResources().getString(R.string.login));
            intentActivity = Constants.LOGIN;
            mEmailSignInButton.setTag(intentActivity);
        } catch (Exception e) {
            Utility.showException(LoginSignUpActivity.this, e);
        }
    }

    private void signUp() {
//        try {
//            HashMap<String, Object> params = new HashMap<>();
//            params.put(ApiExecutor.name, mEtName.getText().toString().trim());
//            params.put(ApiExecutor.email_address, mEtEmail.getText().toString().trim());
//            params.put(ApiExecutor.password, mEtPassword.getText().toString().trim());
//            ApiExecutor.getInstance().setParams(params).signUp(new CallBack<ResponsePojo>() {
//                @Override
//                public void onSuccess(Call<ResponsePojo> call, Response<ResponsePojo> response) {
//                    ResponsePojo responsePojo=response.body();
//                    if (responsePojo.isSuccess()) {
//                        Toast.makeText(LoginSignUpActivity.this, "Login Successful with " + response.body().getEmail_address(), Toast.LENGTH_SHORT).show();
//                        setloginScreen();
//                    }
//                    else
//                    {
//                        Toast.makeText(LoginSignUpActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<ResponsePojo> call, Throwable t) {
//                    Toast.makeText(LoginSignUpActivity.this, "Login UnSuccesful", Toast.LENGTH_SHORT).show();
//                }
//            });
//        } catch (Exception e) {
//            Utility.showException(LoginSignUpActivity.this, e);
//        }


        try {
            ApiInterface mWebApi = RetrofitClient.createService(ApiInterface.class, this);
            CallProgressWheel.showLoadingDialog(this, "Loading...");
            HashMap<String, String> params = new HashMap<>();
            params.put(ApiExecutor.name, mEtName.getText().toString().trim());
            params.put(ApiExecutor.email_address, mEtEmail.getText().toString().trim());
            params.put(ApiExecutor.password, mEtPassword.getText().toString().trim());

            Log.d("Parameters----", "" + params.toString());

            mWebApi.signUp(params.get(ApiExecutor.name).toString().trim(), params.get(ApiExecutor.email_address).toString().trim(), params.get(ApiExecutor.password).toString().trim(), new Callback<ResponsePojo>() {
                @Override
                public void success(ResponsePojo commonResponse, retrofit.client.Response response) {

                    Log.d(LOG_TAG, "Success " + commonResponse.toString());

                    try {
                        if (commonResponse.isSuccess()) {
                            setloginScreen();
                            Utility.showToastMessageShort(LoginSignUpActivity.this, commonResponse.getMsg().toString());
                        } else {
                            Utility.showToastMessageShort(LoginSignUpActivity.this, commonResponse.getMsg().toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    CallProgressWheel.dismissLoadingDialog();
                }

                @Override
                public void failure(RetrofitError error) {
                    try {
//                            Utility.showToastMessageShort(LoginSignUpActivity.this, getResources().getString(R.string.server_error));
                        Utility.showToastMessageShort(LoginSignUpActivity.this, error.getMessage().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    CallProgressWheel.dismissLoadingDialog();
                }
            });
        } catch (Exception e) {
        }


    }

    private void login() {
        try {
//            HashMap<String, Object> params = new HashMap<>();
//            params.put(ApiExecutor.email_address, mEtEmail.getText().toString().trim());
//            params.put(ApiExecutor.password, mEtPassword.getText().toString().trim());
//            ApiExecutor.getInstance().setParams(params).login(new CallBack<ResponsePojo>() {
//                @Override
//                public void onSuccess(Call<ResponsePojo> call, Response<ResponsePojo> response) {
//                    ResponsePojo responsePojo=response.body();
//                    if (responsePojo.isSuccess()) {
//                        Toast.makeText(LoginSignUpActivity.this, "Login Successful with " + response.body().getEmail_address(), Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        Toast.makeText(LoginSignUpActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
//                    }
//                    }
//
//                @Override
//                public void onFailure(Call<ResponsePojo> call, Throwable t) {
//                    Toast.makeText(LoginSignUpActivity.this, "Login UnSuccesful", Toast.LENGTH_SHORT).show();
//                }
//            });

            try {
                ApiInterface mWebApi = RetrofitClient.createService(ApiInterface.class, this);
                CallProgressWheel.showLoadingDialog(this, "Loading...");
                HashMap<String, String> params = new HashMap<>();
                params.put(ApiExecutor.email_address, mEtEmail.getText().toString().trim());
                params.put(ApiExecutor.password, mEtPassword.getText().toString().trim());

                Log.d("Parameters----", "" + params.toString());

                mWebApi.login(params.get(ApiExecutor.email_address).toString().trim(), params.get(ApiExecutor.password).toString().trim(), new Callback<ResponsePojo>() {
                    @Override
                    public void success(ResponsePojo commonResponse, retrofit.client.Response response) {

                        Log.d(LOG_TAG, "Success " + commonResponse.toString());

                        try {
                            if (commonResponse.isSuccess()) {
                                Utility.showToastMessageShort(LoginSignUpActivity.this, commonResponse.getMsg().toString());
                                setIntent(TutorialActivity.class).setFlag(Intent.FLAG_ACTIVITY_CLEAR_TOP).startActivity();
                                finish();
                            } else {
                                Utility.showToastMessageShort(LoginSignUpActivity.this, commonResponse.getMsg().toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        CallProgressWheel.dismissLoadingDialog();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        try {
//                            Utility.showToastMessageShort(LoginSignUpActivity.this, getResources().getString(R.string.server_error));
                            Utility.showToastMessageShort(LoginSignUpActivity.this, error.getMessage().toString());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        CallProgressWheel.dismissLoadingDialog();
                    }
                });
            } catch (Exception e) {
            }

        } catch (Exception e) {
            Utility.showException(LoginSignUpActivity.this, e);
        }


//        ApiInterface apiService =
//                RetrofitClient.getClient().create(ApiInterface.class);
//
//        Call<ResponsePojo> call = apiService.login(mEtEmail.getText().toString(),mEtPassword.getText().toString());
//        call.enqueue(new Callback<ResponsePojo>() {
//            @Override
//            public void onResponse(Call<ResponsePojo> call, Response<ResponsePojo> response) {
//                int statusCode = response.code();
//                Toast.makeText(LoginSignUpActivity.this, "Login Succesful " + response.body(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<ResponsePojo> call, Throwable t) {
//                // Log error here since request failed
//                Log.e("", t.toString());
//                Toast.makeText(LoginSignUpActivity.this, "Login UnSuccesful", Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        setIntent(SplashActivity.class).startActivity();
        super.onBackPressed();
    }
}

