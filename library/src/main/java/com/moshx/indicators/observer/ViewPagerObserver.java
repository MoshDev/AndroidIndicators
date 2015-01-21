package com.moshx.indicators.observer;

import android.database.DataSetObserver;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

/**
 * Created by M.Ersan on 1/2/15.
 */
public class ViewPagerObserver implements ViewPager.OnPageChangeListener {

    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = 2;

    private ArrayList<ObservableView> observableViews = new ArrayList<>();
    private PagerAdapter internalAdapter;
    private ViewPager mViewPager;

    private ViewPager.OnPageChangeListener externalOnPageChangeListener;

    public ViewPagerObserver(ViewPager mViewPager) {
        if (mViewPager == null) {
            throw new IllegalArgumentException("ViewPager is null.");
        }
        this.mViewPager = mViewPager;
        mViewPager.setOnPageChangeListener(this);
        internalAdapter = mViewPager.getAdapter();
        if (internalAdapter == null) {
            throw new IllegalArgumentException("ViewPager adapter is null.");
        }
        internalAdapter.registerDataSetObserver(internalDataSetObserver);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (externalOnPageChangeListener != null) {
            externalOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        synchronized (observableViews) {
            for (int i = observableViews.size() - 1; i >= 0; i--) {
                observableViews.get(i).onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }


    }

    @Override
    public void onPageSelected(int position) {

        if (externalOnPageChangeListener != null) {
            externalOnPageChangeListener.onPageSelected(position);
        }

        synchronized (observableViews) {
            for (int i = observableViews.size() - 1; i >= 0; i--) {
                observableViews.get(i).onPageSelected(position);
            }
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

        if (externalOnPageChangeListener != null) {
            externalOnPageChangeListener.onPageScrollStateChanged(state);
        }

        synchronized (observableViews) {
            for (int i = observableViews.size() - 1; i >= 0; i--) {
                observableViews.get(i).onPageScrollStateChanged(state);
            }
        }

    }

    public void addObservableView(ObservableView l) {

        if (l == null) {
            throw new IllegalArgumentException("The OnPageChangeListener is null.");
        }
        synchronized (observableViews) {
            if (observableViews.contains(l)) {
                throw new IllegalStateException("OnPageChangeListener " + l + " is already added.");
            }
            l.setViewPagerObserver(this);
            observableViews.add(l);
        }

    }

    public void removeObservableView(ObservableView l) {

        if (l == null) {
            throw new IllegalArgumentException("The ObservableView is null.");
        }
        synchronized (observableViews) {
            int index = observableViews.indexOf(l);
            if (index == -1) {
                throw new IllegalStateException("ObservableView " + l + " was not added.");
            }
            observableViews.remove(index);
        }

    }

    public void removeAll() {
        synchronized (observableViews) {
            observableViews.clear();
        }
    }

    private DataSetObserver internalDataSetObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            synchronized (observableViews) {
                for (int i = observableViews.size() - 1; i >= 0; i--) {
                    observableViews.get(i).onChanged();
                }
            }
        }

        @Override
        public void onInvalidated() {
            synchronized (observableViews) {
                for (int i = observableViews.size() - 1; i >= 0; i--) {
                    observableViews.get(i).onInvalidated();
                }
            }
        }
    };


    public int getCount() {
        return internalAdapter.getCount();
    }

    public CharSequence getPageTitle(int position) {
        return internalAdapter.getPageTitle(position);
    }

    public int getCurrentItem() {
        return mViewPager.getCurrentItem();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener l) {
        this.externalOnPageChangeListener = l;
    }

    public PagerAdapter getAdapter() {
        return internalAdapter;
    }

    public void setCurrentItem(int position) {
        mViewPager.setCurrentItem(position);
    }

    public void setCurrentItem(int position, boolean smoothScroll) {
        mViewPager.setCurrentItem(position, smoothScroll);
    }
}
