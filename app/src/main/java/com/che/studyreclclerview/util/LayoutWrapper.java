package com.che.studyreclclerview.util;

/**
 * 作者：余天然 on 16/7/14 下午6:06
 */
public class LayoutWrapper<T> {

    private T source;
    private int layoutId;
    private int spanSize;
    private int viewType;
    private boolean isPinned;

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setIsPinned(boolean isPinned) {
        this.isPinned = isPinned;
    }
}
