package biriinfotech.com.doglover.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.controller.Config;
import biriinfotech.com.doglover.model.MenuModel;
import biriinfotech.com.doglover.ui.customviews.CallProgressWheel;
import biriinfotech.com.doglover.utils.Constants;

public class WebViewActivity extends BaseActivity {
    WebView webView;
    private Toolbar mToolbar;
    private MenuModel mMenuModel;
    private boolean isFirstTime;

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
            getSupportActionBar().setTitle(mMenuModel.getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void setUpLayout() {

        try {
            webView = (WebView) findViewById(R.id.webView);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    void setDataInViewObjects() {
        try {
            startWebView(Config.getBaseUrlImage() + mMenuModel.getWeb_view_url());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_web_view);
            mMenuModel = (MenuModel) getIntent().getSerializableExtra(Constants.POJO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void startWebView(String url) {
        try {
            //Create new webview Client to show progress dialog
            //When opening a url or click on link

            webView.setWebViewClient(new WebViewClient() {
                //If you will not use this method url links are opeen in new brower not in webview
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                //Show loader on url load
                public void onLoadResource(WebView view, String url) {
                    if (!isFirstTime) {
                        CallProgressWheel.showLoadingDialog(WebViewActivity.this, "Loading...");
                        isFirstTime = true;
                    }
                }

                public void onPageFinished(WebView view, String url) {
                    try {
                        CallProgressWheel.dismissLoadingDialog();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                        CallProgressWheel.dismissLoadingDialog();
                    }
                }

            });

            // Javascript inabled on webview
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // Open previous opened link from history on webview when back button pressed

    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        try {
            CallProgressWheel.dismissLoadingDialog();
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                // Let the system handle the back button
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
