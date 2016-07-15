package com.che.studyreclclerview.item;

/**
 * 作者：余天然 on 16/7/14 下午6:28
 */
public class LayoutWrapper<T> {
    int layoutId;
    int spanSize;
    T data;

    public LayoutWrapper(int layoutId, int spanSize, T data) {
        this.layoutId = layoutId;
        this.spanSize = spanSize;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
