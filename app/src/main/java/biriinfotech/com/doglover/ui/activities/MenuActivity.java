package biriinfotech.com.doglover.ui.activities;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.RetrofitClient;
import biriinfotech.com.doglover.controller.ApiExecutor;
import biriinfotech.com.doglover.controller.ApiInterface;
import biriinfotech.com.doglover.interfaces.CallBack;
import biriinfotech.com.doglover.model.ResponsePojo;
import biriinfotech.com.doglover.ui.adapters.MenuAdapter;
import biriinfotech.com.doglover.ui.customviews.CallProgressWheel;
import biriinfotech.com.doglover.utils.BlurBuilder;
import biriinfotech.com.doglover.utils.Utility;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit2.Call;
import retrofit2.Response;

public class MenuActivity extends BaseActivity {

    private Toolbar mToolbar;
    private RecyclerView mRvMain;
    private String LOG_TAG = "MenuActivity";
    private Call callGetMenuDataRetrofit2;
    private LinearLayout mLlMain;

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
        try {
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
        } catch (Exception e) {
            Utility.showException(MenuActivity.this, e);
        }
    }

    @Override
    void setUpLayout() {
        try {
            mLlMain = (LinearLayout) findViewById(R.id.ll_main);
            mRvMain = (RecyclerView) findViewById(R.id.rv_main);
            mRvMain.setLayoutManager(new LinearLayoutManager(this));

            BitmapDrawable background = new BitmapDrawable(BlurBuilder.blur(this, BitmapFactory.decodeResource(getResources(), R.drawable.dancing_dog)));
            mLlMain.setBackgroundDrawable(background);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void setDataInViewObjects() {
        try {
            if (Utility.isInternetConnection(this)) {
                getMenuDataRetrofit2();
            } else {
                Toast.makeText(this, getResources().getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Utility.showException(MenuActivity.this, e);
        }

    }

    CallBack CallBack = new CallBack<ResponsePojo>() {
        @Override
        public void onSuccess(Call<ResponsePojo> call, Response<ResponsePojo> response) {
            ResponsePojo commonResponse = response.body();
            Log.d(LOG_TAG, "Success " + commonResponse.toString());

            try {
                mRvMain.setVisibility(View.VISIBLE);
                hideNoInternetView(MenuActivity.this);
                if (commonResponse.isSuccess()) {
                    mRvMain.setAdapter(new MenuAdapter(MenuActivity.this, commonResponse.getData()));
                } else {
                    Utility.showToastMessageShort(MenuActivity.this, commonResponse.getMsg().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            CallProgressWheel.dismissLoadingDialog();
        }

        @Override
        public void onFailure(Call<ResponsePojo> call, Throwable t) {
            CallProgressWheel.dismissLoadingDialog();
        }

        @Override
        public void onInternetUnavailable() {
            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRvMain.setVisibility(View.GONE);
                        showNoInternetView(MenuActivity.this, callGetMenuDataRetrofit2, CallBack);
                    }
                });
            } catch (Exception e) {
                Utility.showException(MenuActivity.this, e);
            }
        }
    };


    private void getMenuDataRetrofit2() {
        try {
            CallProgressWheel.showLoadingDialog(this, "Loading...");
            callGetMenuDataRetrofit2 = ApiExecutor.getInstance(this).getMenuData(CallBack);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                            mRvMain.setAdapter(new MenuAdapter(MenuActivity.this, commonResponse.getData()));
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
                        Utility.showToastMessageShort(MenuActivity.this, error.getMessage().toString());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    CallProgressWheel.dismissLoadingDialog();
                }
            });
        } catch (Exception e) {
            Utility.showException(this, e);
        }


    }


    @Override
    public void onBackPressed() {
        try {
            super.onBackPressed();
        } catch (Exception e) {
            Utility.showException(MenuActivity.this, e);
        }
    }

}
