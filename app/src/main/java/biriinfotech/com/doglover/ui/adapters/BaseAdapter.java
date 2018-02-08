package biriinfotech.com.doglover.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Biri Infotech on 2/7/2018.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    public View itemView;

    public abstract int setItemCount();
    public abstract int setLayoutResourceFile();
    public abstract void onItemClickListener(View itemView, int position);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView= LayoutInflater.from(parent.getContext()).inflate(setLayoutResourceFile(),parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        onItemClickListener(itemView,position);
    }

    @Override
    public int getItemCount() {
        return setItemCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemV) {
            super(itemV);
        }
    }
}
