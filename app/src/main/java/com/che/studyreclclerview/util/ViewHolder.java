package com.che.studyreclclerview.util;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * 作者：余天然 on 16/7/14 下午4:12
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    public ViewHolder(View view) {
        super(view);
        this.views = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}