package com.che.studyreclclerview.item;

/**
 * 作者：余天然 on 16/7/14 下午6:26
 */
public class BigBt {

    int icon;
    String name;
    String desc;

    public BigBt(int icon, String name, String desc) {
        this.icon = icon;
        this.name = name;
        this.desc = desc;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
