package biriinfotech.com.doglover.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.RetrofitClient;
import biriinfotech.com.doglover.controller.ApiInterface;
import biriinfotech.com.doglover.model.ResponsePojo;
import biriinfotech.com.doglover.ui.adapters.MenuAdapter;
import biriinfotech.com.doglover.ui.customviews.CallProgressWheel;
import biriinfotech.com.doglover.utils.Utility;
import retrofit.Callback;
import retrofit.RetrofitError;

public class MenuActivity extends BaseActivity {

    private Toolbar mToolbar;
    private RecyclerView mRvMain;
    private String LOG_TAG="MenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        } catch (Exception e) {
            Utility.showException(MenuActivity.this, e);
        }
    }

    @Override
    AppCompatActivity setInstance() {
        return this;
    }

    @Override
    void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Main Menu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    void setUpLayout() {
        mRvMain = (RecyclerView) findViewById(R.id.rv_main);
        mRvMain.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    void setDataInViewObjects() {
        getMenuData();
    }

    private void getMenuData() {
        try {
            ApiInterface mWebApi = RetrofitClient.createService(ApiInterface.class, this);
            CallProgressWheel.showLoadingDialog(this, "Loading...");
            mWebApi.getMenuData(new Callback<ResponsePojo>() {
                @Override
                public void success(ResponsePojo commonResponse, retrofit.client.Response response) {

                    Log.d(LOG_TAG, "Success " + commonResponse.toString());

                    try {
                        if (commonResponse.isSuccess()) {
//                            Utility.showToastMessageShort(MenuActivity.this, commonResponse.getMsg().toString());
                        mRvMain.setAdapter(new MenuAdapter(MenuActivity.this,commonResponse.getData()));
                        } else {
                            Utility.showToastMessageShort(MenuActivity.this, commonResponse.getMsg().toString());
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
                        Utility.showToastMessageShort(MenuActivity.this, error.getMessage().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    CallProgressWheel.dismissLoadingDialog();
                }
            });
        } catch (Exception e) {
            Utility.showException(this,e);
        }


    }


    @Override
    public void onBackPressed() {
        setIntent(TutorialActivity.class).startActivity();
        super.onBackPressed();
    }
}
