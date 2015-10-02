package com.ykyahwa.databindingtest.data;

import android.databinding.BaseObservable;

/**
 * Created by Bill on 2015-10-01.
 */
public class Count extends BaseObservable {
    public int count = 0;

    public Count(int count) {
        this.count = count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
