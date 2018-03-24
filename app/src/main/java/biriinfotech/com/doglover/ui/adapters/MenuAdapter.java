package biriinfotech.com.doglover.ui.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import biriinfotech.com.doglover.R;
import biriinfotech.com.doglover.controller.Config;
import biriinfotech.com.doglover.model.MenuModel;
import biriinfotech.com.doglover.ui.activities.BaseActivity;
import biriinfotech.com.doglover.ui.activities.MenuActivity;
import biriinfotech.com.doglover.ui.activities.SubMenuActivity;
import biriinfotech.com.doglover.utils.Constants;

/**
 * Created by Biri Infotech on 2/7/2018.
 */

public class MenuAdapter extends BaseAdapter {
    private int position = -1;
    ArrayList<MenuModel> arrayListData = new ArrayList<>();
    Context context;

    public MenuAdapter(Context context, ArrayList<MenuModel> arrayListData) {
        this.arrayListData = arrayListData;
        this.context = context;
    }

    public MenuAdapter(Context context, ArrayList<MenuModel> arrayListData, int position) {
        this.arrayListData = arrayListData;
        this.context = context;
        this.position = position;
    }

    @Override
    public int setItemCount() {
        return arrayListData.size();
    }

    @LayoutRes
    @Override
    public int setLayoutResourceFile() {
        return R.layout.item_menu;
    }

    @Override
    public void onItemClickListener(View itemView, final int position) {
        try {
            final LinearLayout mLlTitleDesc = (LinearLayout) itemView.findViewById(R.id.ll_ttile_desc);
            final LinearLayout mLlMain = (LinearLayout) itemView.findViewById(R.id.ll_main);
            ImageView mIvMenuImage = (ImageView) itemView.findViewById(R.id.iv_menu_image);
            TextView mTvMenuItemTitle = (TextView) itemView.findViewById(R.id.menu_item_title);
            final TextView mTvMenuItemDescription = (TextView) itemView.findViewById(R.id.tv_menu_item_description);
            final ProgressBar mHomeProgress = (ProgressBar) itemView.findViewById(R.id.homeprogress);
            mTvMenuItemTitle.setText(arrayListData.get(position).getTitle().toString().trim());
            mTvMenuItemDescription.setText(arrayListData.get(position).getDescription().toString().trim());
            mTvMenuItemDescription.setVisibility(arrayListData.get(position).getDescription().toString().equals("") ? View.GONE : View.VISIBLE);
            RequestOptions ro = new RequestOptions()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(100, 100)
                    .fitCenter();

            Glide.with(context)
                    .load(Config.getBaseUrlImage() + arrayListData.get(position).getImage())
                    .apply(ro)
                    .into(mIvMenuImage);
            mLlMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((view.getContext() instanceof MenuActivity)) {
                        MenuModel menuModel = arrayListData.get(position);
                        menuModel.setTitle(arrayListData.get(position).getTitle().toString().trim());
                        menuModel.setPosition(position + 1);
                        ((BaseActivity) mLlMain.getContext())
                                .setIntent(SubMenuActivity.class)
                                .putExtra(Constants.POJO, menuModel)
                                .startActivity();
                    } else {
                        Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
