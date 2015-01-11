package com.moshx.indicators.observer;

import android.support.v4.view.ViewPager;

/**
 * Created by M.Ersan on 1/2/15.
 */
public interface ObservableView extends ViewPager.OnPageChangeListener {


    public void onChanged();

    public void onInvalidated();

    public void setViewPagerObserver(ViewPagerObserver observer);

}
