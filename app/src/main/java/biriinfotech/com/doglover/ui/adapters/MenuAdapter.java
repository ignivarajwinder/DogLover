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

public class MenuAdapter extends BaseAdapter {
    private int position=-1;
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
        final LinearLayout mLlMain = (LinearLayout) itemView.findViewById(R.id.ll_main);
        ImageView mIvMenuImage = (ImageView) itemView.findViewById(R.id.iv_menu_image);
        TextView mTvMenuItemTitle = (TextView) itemView.findViewById(R.id.menu_item_title);
        final TextView mTvMenuItemDescription = (TextView) itemView.findViewById(R.id.tv_menu_item_description);
        final ProgressBar mHomeProgress = (ProgressBar) itemView.findViewById(R.id.homeprogress);
if (this.position==-1)
        mTvMenuItemTitle.setText((position + 1) + ". " + arrayListData.get(position).getTitle().toString().trim());
else
    mTvMenuItemTitle.setText(this.position+"."+(position + 1) + ". " + arrayListData.get(position).getTitle().toString().trim());
mTvMenuItemDescription.setText(arrayListData.get(position).getDescription().toString().trim());

        Glide.with(context)
                .load(Config.getBaseURL() + "/" + arrayListData.get(position).getImage())
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
                    MenuModel menuModel = arrayListData.get(position);
                    menuModel.setTitle((position + 1) + ". " + arrayListData.get(position).getTitle().toString().trim());
                    menuModel.setPosition(position+1);
                    ((BaseActivity) mLlMain.getContext())
                            .setIntent(SubMenuActivity.class)
                            .putExtra(Constants.POJO, menuModel)
//                        .putExtra(Constants.MENU_TITLE, (position + 1) + ". " + arrayListData.get(position).getTitle().toString().trim())
                            .startActivity();
                } else {
                    Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
