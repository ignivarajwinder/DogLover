package biriinfotech.com.doglover.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.interfaces.CallBack;
import biriinfotech.com.doglover.model.MenuModel;
import biriinfotech.com.doglover.ui.customviews.EllipsizingTextView;
import biriinfotech.com.doglover.utils.Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Biri Infotech on 2/3/2018.
 */

public abstract class BaseActivity<T> extends AppCompatActivity implements Callback<T> {
    Intent intent;
    boolean isOnStartExecuted;
    private CallBack<T> callBack;

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
            if (isOnStartExecuted) {
            } else {
                isOnStartExecuted = true;
                setUpToolbar();
                setUpLayout();
                setDataInViewObjects();
            }
        } catch (Exception e) {
            Utility.showException(BaseActivity.this, e);
        }
    }

    public BaseActivity startActivity() {
        try {
            startActivity(intent);
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
            if (intent != null)
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

    public BaseActivity putExtra(String key, MenuModel value) {
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
    public void onResponse(Call call, Response response) {
        callBack.onSuccess(call, response);
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        callBack.onFailure(call, t);
    }


    public void hideNoInternetView(Context context) {
        try {
            LinearLayout mLinearLayout = ((Activity) context).findViewById(R.id.no_internet_connection);
            mLinearLayout.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showNoInternetView(Context context, final Call call, CallBack callBack) {
        try {
            this.callBack = callBack;
            LinearLayout mLinearLayout = ((Activity) context).findViewById(R.id.no_internet_connection);
            mLinearLayout.setVisibility(View.VISIBLE);
            TextView mTvRetry = ((Activity) context).findViewById(R.id.retry);
            mTvRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    call.cancel();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                call.clone().enqueue(BaseActivity.this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        EllipsizingTextView.maxLines = 2;
        super.onBackPressed();
    }

}
