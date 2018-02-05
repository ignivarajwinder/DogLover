package biriinfotech.com.doglover.ui.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.utils.Constants;
import biriinfotech.com.doglover.utils.Utility;

public class SplashActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvLetsDoThis;
    private String TAG = this.getClass().getName();
    private LinearLayout mLlLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
        } catch (Exception e) {
            Utility.showException(SplashActivity.this, e);
        }
    }


    @Override
    AppCompatActivity setInstance() {
        return SplashActivity.this;
    }

    @Override
    void setUpToolbar() {
    }

    @Override
    void setUpLayout() {
        mTvLetsDoThis = (TextView) findViewById(R.id.tv_lets_do_this);
        mLlLogin = (LinearLayout) findViewById(R.id.ll_login);
        mTvLetsDoThis.setOnClickListener(this);
        mLlLogin.setOnClickListener(this);
    }

    @Override
    void setDataInViewObjects() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_lets_do_this:
                setIntent(LoginSignUpActivity.class).putExtra(Constants.ACTIVITY_NAME,Constants.SIGNUP).startActivity();
                finish();
                break;
            case R.id.ll_login:
                setIntent(LoginSignUpActivity.class).putExtra(Constants.ACTIVITY_NAME,Constants.LOGIN).startActivity();
                finish();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        setIntent(TutorialActivity.class).startActivity();
        super.onBackPressed();
    }
}
