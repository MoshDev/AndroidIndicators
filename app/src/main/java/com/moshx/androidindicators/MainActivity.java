package com.moshx.androidindicators;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.moshx.indicators.observer.ViewPagerObserver;
import com.moshx.indicators.title.TitleIndicator;
import com.moshx.indicators.title.transformer.Title3DTransformer;
import com.moshx.indicators.title.transformer.UpDownTitleTransformer;


public class MainActivity extends ActionBarActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        final ViewPagerObserver observer = new ViewPagerObserver(mViewPager);

        TitleIndicator titleIndicator = (TitleIndicator) findViewById(R.id.titleIndicator);
        observer.addObservableView(titleIndicator);
        titleIndicator.setTitleTransformer(new Title3DTransformer(this));
        titleIndicator.setToolBar(toolbar);

    }


    private class MyAdapter extends FragmentPagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TestFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Position:" + position;
        }
    }


}
