package com.che.studyreclclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：余天然 on 16/7/14 下午1:27
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.SimpleViewHolder> {

    private Context context;
    private List<MyBean> items = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_text, viewGroup, false);
        return new SimpleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder viewHolder, int position) {
        viewHolder.tvName.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<MyBean> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    protected final static class SimpleViewHolder extends RecyclerView.ViewHolder {
        protected TextView tvName;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }

    }
}
