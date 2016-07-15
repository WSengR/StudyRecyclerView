package com.che.studyreclclerview.util;

/**
 * 作者：余天然 on 16/7/14 下午4:02
 */
public interface MultiType<T> {

    int setViewType(T t);

    int getLayoutId(int viewType);
}
