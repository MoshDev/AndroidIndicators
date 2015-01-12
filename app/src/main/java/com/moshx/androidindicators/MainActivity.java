package com.moshx.androidindicators;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.moshx.indicators.observer.ViewPagerObserver;
import com.moshx.indicators.title.TitleIndicator;
import com.moshx.indicators.title.transformer.DefaultTitleTransformer;
import com.moshx.indicators.title.transformer.Title3DTransformer;
import com.moshx.indicators.title.transformer.UpDownTitleTransformer;


public class MainActivity extends ActionBarActivity {

    private TitleIndicator titleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(null);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        final ViewPagerObserver observer = new ViewPagerObserver(mViewPager);

        titleIndicator = (TitleIndicator) findViewById(R.id.titleIndicator);
        observer.addObservableView(titleIndicator);
        titleIndicator.setToolBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_default:
                titleIndicator.setTitleTransformer(new DefaultTitleTransformer(MainActivity.this));
                break;
            case R.id.action_updown:
                titleIndicator.setTitleTransformer(new UpDownTitleTransformer(MainActivity.this));
                break;
            case R.id.action_3d:
                titleIndicator.setTitleTransformer(new Title3DTransformer(MainActivity.this));
                break;
        }

        return super.onOptionsItemSelected(item);
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
