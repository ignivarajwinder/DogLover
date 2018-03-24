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

import java.util.HashMap;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.controller.ApiExecutor;
import biriinfotech.com.doglover.interfaces.CallBack;
import biriinfotech.com.doglover.model.MenuModel;
import biriinfotech.com.doglover.model.ResponsePojo;
import biriinfotech.com.doglover.ui.adapters.SubMenuAdapter;
import biriinfotech.com.doglover.ui.customviews.CallProgressWheel;
import biriinfotech.com.doglover.ui.customviews.EllipsizingTextView;
import biriinfotech.com.doglover.utils.BlurBuilder;
import biriinfotech.com.doglover.utils.Constants;
import biriinfotech.com.doglover.utils.Utility;
import retrofit2.Call;
import retrofit2.Response;

public class SubMenuActivity extends BaseActivity {

    private static final String LOG_TAG = "SubMenuActivity";
    private Toolbar mToolbar;
    private RecyclerView mRvMain;
    MenuModel mMenuModel;
    private Call callGetSubMenuData;
    private LinearLayout mLlMain;

    @Override
    AppCompatActivity setInstance() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sub_menu);
            mMenuModel = (MenuModel) getIntent().getSerializableExtra(Constants.POJO);
            EllipsizingTextView.maxLines = 5;
        } catch (Exception e) {
            Utility.showException(SubMenuActivity.this, e);
        }
    }

    @Override
    void setUpToolbar() {
        try {
            mLlMain = (LinearLayout) findViewById(R.id.ll_main);
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(mMenuModel.getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        } catch (Exception e) {
            Utility.showException(SubMenuActivity.this, e);
        }
    }

    @Override
    void setUpLayout() {
        try {
            mRvMain = (RecyclerView) findViewById(R.id.rv_main);
            mRvMain.setLayoutManager(new LinearLayoutManager(this));
            BitmapDrawable background = new BitmapDrawable(BlurBuilder.blur(this, BitmapFactory.decodeResource(getResources(), R.drawable.dancing_dog)));
            mLlMain.setBackgroundDrawable(background);
        } catch (Exception e) {
            Utility.showException(SubMenuActivity.this, e);
        }

    }

    @Override
    void setDataInViewObjects() {
        try {
            if (Utility.isInternetConnection(this)) {
                getSubMenuDataRetrofit2(mMenuModel.getId());
            } else {
                Toast.makeText(this, getResources().getString(R.string.check_internet), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Utility.showException(SubMenuActivity.this, e);
        }

    }

    private void getSubMenuDataRetrofit2(String category_id) {
        try {
            CallProgressWheel.showLoadingDialog(this, "Loading...");
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("main_menu_id", category_id);
            callGetSubMenuData = ApiExecutor.getInstance().setParams(params).getSubMenuData(new CallBack<ResponsePojo>() {
                @Override
                public void onSuccess(Call<ResponsePojo> call, Response<ResponsePojo> response) {
                    ResponsePojo commonResponse = response.body();
                    Log.d(LOG_TAG, "Success " + commonResponse.toString());
                    try {
                        if (commonResponse.isSuccess()) {
                            mRvMain.setAdapter(new SubMenuAdapter(SubMenuActivity.this, mMenuModel, commonResponse.getData(), mMenuModel.getPosition()));
                        } else {
                            Utility.showToastMessageShort(SubMenuActivity.this, commonResponse.getMsg().toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    CallProgressWheel.dismissLoadingDialog();
                }

                @Override
                public void onFailure(Call<ResponsePojo> call, Throwable t) {
                    CallProgressWheel.dismissLoadingDialog();
                    callGetSubMenuData.clone();
                }

                @Override
                public void onInternetUnavailable() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
