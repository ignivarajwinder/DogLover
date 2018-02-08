package biriinfotech.com.doglover.ui.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.utils.Utility;

public class TutorialActivity extends BaseActivity {
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView[] dots;
    private int[] layouts;
    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {

            if (position == layouts.length - 1) {
//                mTvLetsGo.setText(getString(R.string.letsGo));
//                mTvLetsGo.setVisibility(View.VISIBLE);
            } else {
//                mTvLetsGo.setText(getString(R.string.skip));
//                mTvLetsGo.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };
    private ViewPager viewPager;
    private AppCompatTextView mTvPawThisButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_tutorial);
        } catch (Exception e) {
        }
    }

    @Override
    protected void setUpLayout() {
        try {
            viewPager = (ViewPager) findViewById(R.id.view_pager);
            mTvPawThisButton=(AppCompatTextView)findViewById(R.id.tv_paw_this_button) ;
            layouts = new int[]{
                    R.layout.tutorial_screen_1,
                    R.layout.tutorial_screen_2,
                    R.layout.tutorial_screen_3,
            };
            changeStatusBarColor();
            myViewPagerAdapter = new MyViewPagerAdapter();
            viewPager.setAdapter(myViewPagerAdapter);
            viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setDataInViewObjects() {
        try {
            mTvPawThisButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    launchHomeScreen();
                }
            });
        } catch (Exception e) {
        }
    }
    @Override
    AppCompatActivity setInstance() {
        return TutorialActivity.this;
    }
    @Override
    protected void setUpToolbar() {
    }


    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        try {
            setIntent(MenuActivity.class).startActivity();
            finish();
        } catch (Exception e) {
            Utility.showException(TutorialActivity.this,e);
        }
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
