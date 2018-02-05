package biriinfotech.com.doglover.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.utils.Utility;

/**
 * Created by Biri Infotech on 2/3/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {
    Intent intent;

    abstract AppCompatActivity setInstance();

    abstract void setUpToolbar();

    abstract void setUpLayout();

    abstract void setDataInViewObjects();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            setUpToolbar();
            setUpLayout();
            setDataInViewObjects();
        } catch (Exception e) {
            Utility.showException(BaseActivity.this, e);
        }
    }

    public BaseActivity startActivity() {
        try {
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
        } catch (Exception e) {
            Utility.showException(BaseActivity.this, e);
        }
        return this;
    }

    public BaseActivity setIntent(Class appCompatActivity) {
        try {
            intent = new Intent(setInstance(), appCompatActivity);
        } catch (Exception e) {
            Utility.showException(BaseActivity.this, e);
        }
        return this;
    }

    public BaseActivity setFlag(int flag) {
        try {
            if (intent!=null)
            intent.setFlags(flag);
        } catch (Exception e) {
            Utility.showException(BaseActivity.this, e);
        }
        return this;
    }



    public BaseActivity putExtra(String data) {
        try {
            if (intent != null) {
                intent.putExtra(data, data);
            }
        } catch (Exception e) {
            Utility.showException(BaseActivity.this, e);
        }
        return this;
    }

    public BaseActivity putExtra(String key, String value) {
        try {
            if (intent != null) {
                intent.putExtra(key, value);
            }
        } catch (Exception e) {
            Utility.showException(BaseActivity.this, e);
        }
        return this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_translate, R.anim.exit_translate);
    }

}
