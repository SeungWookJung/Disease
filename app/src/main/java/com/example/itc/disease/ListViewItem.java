package com.example.itc.disease;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2017-11-24.
 */

public class ListViewItem {
    private Drawable iconDrawable;
    private String title;
    private String idx;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        this.title = title ;
    }
    public void setIdx(String idx) {
        this.idx = idx;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.title ;
    }
    public String getIdx() { return this.idx; }
}