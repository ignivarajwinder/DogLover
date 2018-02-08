package biriinfotech.com.doglover.ui.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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

public class SubMenuAdapter extends BaseAdapter {
    private int position = -1;
    ArrayList<MenuModel> arrayListData = new ArrayList<>();
    Context context;
    MenuModel mMenuModel;

    public SubMenuAdapter(Context context, MenuModel mMenuModel, ArrayList<MenuModel> arrayListData, int position) {
        this.arrayListData = arrayListData;
        this.context = context;
        this.mMenuModel = mMenuModel;
        this.position = position;
    }

    public SubMenuAdapter(Context context, ArrayList<MenuModel> arrayListData, int position) {
        this.arrayListData = arrayListData;
        this.context = context;
        this.position = position;
    }

    @Override
    public int setItemCount() {
        return arrayListData.size()+1;
    }

    @LayoutRes
    @Override
    public int setLayoutResourceFile() {
        return R.layout.item_menu;
    }

    @LayoutRes
    @Override
    public int setLayoutResourceFileHeader() {
        return R.layout.item_menu_header;
    }

    @Override
    public void onItemClickListener(View itemView, final int position) {

        final LinearLayout mLlMain = (LinearLayout) itemView.findViewById(R.id.ll_main);
        ImageView mIvMenuImage = (ImageView) itemView.findViewById(R.id.iv_menu_image);
        TextView mTvMenuItemTitle = (TextView) itemView.findViewById(R.id.menu_item_title);
        final TextView mTvMenuItemDescription = (TextView) itemView.findViewById(R.id.tv_menu_item_description);
        final ProgressBar mHomeProgress = (ProgressBar) itemView.findViewById(R.id.homeprogress);
        if (this.position == -1)
            mTvMenuItemTitle.setText((position-1+1) + ". " + arrayListData.get(position-1).getTitle().toString().trim());
        else
            mTvMenuItemTitle.setText(this.position-1 + "." + (position-1 + 1) + ". " + arrayListData.get(position-1).getTitle().toString().trim());
        mTvMenuItemDescription.setText(arrayListData.get(position-1).getDescription().toString().trim());

        Glide.with(context)
                .load(Config.getBaseURL() + "/" + arrayListData.get(position-1).getImage())
//                .placeholder(R.drawable.loading_spinner)
//                .into(mIvMenuImage);
                .listener(new RequestListener<Drawable>() {


                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mHomeProgress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(mIvMenuImage);
        mLlMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((view.getContext() instanceof MenuActivity)) {
                    MenuModel menuModel = arrayListData.get(position-1);
                    menuModel.setTitle((position-1 + 1) + ". " + arrayListData.get(position-1).getTitle().toString().trim());
                    menuModel.setPosition(position-1 + 1);
                    ((BaseActivity) mLlMain.getContext())
                            .setIntent(SubMenuActivity.class)
                            .putExtra(Constants.POJO, menuModel)
//                        .putExtra(Constants.MENU_TITLE, (position-1 + 1) + ". " + arrayListData.get(position-1).getTitle().toString().trim())
                            .startActivity();
                } else {
                    Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemClickListenerHeader(View itemViewHeader, int position) {

        ImageView mIvMenuImage = (ImageView) itemViewHeader.findViewById(R.id.iv_menu_image);
        TextView mTvMenuItemTitle = (TextView) itemViewHeader.findViewById(R.id.menu_item_title);
        TextView mTvMenuItemDescription = (TextView) itemViewHeader.findViewById(R.id.tv_menu_item_description);
        final ProgressBar mHomeProgress = (ProgressBar) itemViewHeader.findViewById(R.id.homeprogress);


        mTvMenuItemTitle.setText(mMenuModel.getTitle());
        mTvMenuItemDescription.setText(mMenuModel.getDescription().toString().trim());

        Glide.with(mIvMenuImage.getContext())
                .load(Config.getBaseURL()+"/"+mMenuModel.getImage())
//                .placeholder(R.drawable.loading_spinner)
//                .into(mIvMenuImage);
                .listener(new RequestListener<Drawable>() {


                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        mHomeProgress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(mIvMenuImage);
    }
}
