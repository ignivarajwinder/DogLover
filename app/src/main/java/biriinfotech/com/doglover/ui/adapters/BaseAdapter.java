package biriinfotech.com.doglover.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Biri Infotech on 2/7/2018.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    public View itemViewItem, itemViewHeader;

    public abstract int setItemCount();

    public abstract int setLayoutResourceFile();

    public abstract void onItemClickListener(View itemView, int position);

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {

            itemViewItem = LayoutInflater.from(parent.getContext()).inflate(setLayoutResourceFile(), parent, false);
            return new ViewHolder(itemViewItem);

        } else if (viewType == TYPE_HEADER) {
            itemViewHeader = LayoutInflater.from(parent.getContext()).inflate(setLayoutResourceFileHeader(), parent, false);
            return new ViewHolder(itemViewHeader);
        }
        return null;
    }

    public int setLayoutResourceFileHeader() {
        return  setLayoutResourceFile();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == TYPE_HEADER)
            onItemClickListenerHeader(holder.itemView, position);
        else
            onItemClickListener(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return setItemCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemV) {
            super(itemV);
        }
    }

    public void onItemClickListenerHeader(View itemViewHeader, final int position) {
        onItemClickListener(itemViewHeader, position);
    }

    @Override
    public int getItemViewType(int position) {

        if (isPositionHeader(position))
            return TYPE_HEADER;

        else
            return TYPE_ITEM;

    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}
