package biriinfotech.com.doglover.ui.adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import biriinfotech.com.doglover.R;

public class ViewPagerAdapter extends PagerAdapter {
	// Declare Variables
	Context context;

	ArrayList<String> ViewPager_Image_link=new ArrayList<>();
	ArrayList<String> ViewPager_Image_click_link=new ArrayList<>();

	LayoutInflater inflater;

	public ViewPagerAdapter(Context context,
							ArrayList<String> ViewPager_Image_link, ArrayList<String> ViewPager_Image_click_link) {
		this.context = context;
		this.ViewPager_Image_link = ViewPager_Image_link;
		this.ViewPager_Image_click_link = ViewPager_Image_click_link;
	}

	@Override
	public int getCount() {
		return ViewPager_Image_link.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((RelativeLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {

		ImageView imgflag;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.viewpager_item, container,
				false);


		imgflag = (ImageView) itemView.findViewById(R.id.flag);

//		Glide.with(context).load(ViewPager_Image_link.get(position).toString())
////				.thumbnail(1.0f)
//				.centerCrop()
//				.crossFade()
//				.diskCacheStrategy(DiskCacheStrategy.ALL)
//				.into(imgflag);



		// Add viewpager_item.xml to ViewPager
		((ViewPager) container).addView(itemView);

		imgflag.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(ViewPager_Image_click_link.get(position)));
				String title = " ";
				Intent chooser = Intent.createChooser(intent, title);
				context.startActivity(chooser);
			}
		});

		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Remove viewpager_item.xml from ViewPager
		((ViewPager) container).removeView((RelativeLayout) object);

	}
}
