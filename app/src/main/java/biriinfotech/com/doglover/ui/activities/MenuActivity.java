package biriinfotech.com.doglover.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.utils.Utility;

public class MenuActivity extends BaseActivity {

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

    }

    @Override
    void setUpLayout() {

    }

    @Override
    void setDataInViewObjects() {

    }

    @Override
    public void onBackPressed() {
        setIntent(TutorialActivity.class).startActivity();
        super.onBackPressed();
    }
}
