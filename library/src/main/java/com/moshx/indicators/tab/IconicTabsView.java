package com.moshx.indicators.tab;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.moshx.indicators.R;
import com.moshx.indicators.observer.ObservableView;
import com.moshx.indicators.observer.ViewPagerObserver;
import com.moshx.indicators.tab.effect.GreyscaleIconicTabsEffect;
import com.moshx.indicators.tab.effect.IconicTabsEffect;

/**
 * Created by M.Ersan on 1/20/15.
 */
public class IconicTabsView extends HorizontalScrollView implements ObservableView {

    private static final String TAG = "IconicTabsView";

    public enum TabsWidth {
        AUTO, FIT_WIDTH, ACTUAL;
    }

    private ViewPagerObserver observer;
    private IconicProvider iconicProvider;
    private LinearLayout tabsContainer;
    private ImageView selectedTabView;
    private int lastPosition;
    private IconicTabsEffect iconicTabsEffect = new GreyscaleIconicTabsEffect();
    private TabsWidth tabsWidth = TabsWidth.AUTO;
    private int minTabWidth;


    public IconicTabsView(Context context) {
        this(context, null, 0);
    }

    public IconicTabsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IconicTabsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    public IconicTabsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        setFillViewport(true);
        setWillNotDraw(false);

        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);

        DisplayMetrics dm = getResources().getDisplayMetrics();

        int dividerPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm);
        float dp48 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, dm);

        minTabWidth = (int) dp48;

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) dp48);

        tabsContainer = new LinearLayout(context);
        tabsContainer.setLayoutParams(params);
        tabsContainer.setDividerDrawable(getResources().getDrawable(R.drawable.andindicators___iconic_tabs_divider));
        tabsContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        tabsContainer.setDividerPadding(dividerPadding);
        addView(tabsContainer);

    }


    @Override
    public void onChanged() {

    }

    @Override
    public void onInvalidated() {

    }

    @Override
    public void setViewPagerObserver(ViewPagerObserver observer) {

        this.observer = observer;

        if (observer.getAdapter() instanceof IconicProvider) {
            iconicProvider = (IconicProvider) observer.getAdapter();
        } else {
            throw new IllegalArgumentException("ViewPager Adapter MUST implement IconicProvider interface to use IconicTabsView");
        }

        buildTabsViews();

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (observer.getCount() <= 0) {
            return;
        }

        scrollToChild(position, (int) (positionOffset * tabsContainer.getChildAt(position).getWidth()));

        if (lastPosition != position) {
            ImageView previousImageView = (ImageView) tabsContainer.getChildAt(position);
            if (previousImageView != null)
                iconicTabsEffect.applyTabEffect(previousImageView, 1f - positionOffset, IconicTabsEffect.Status.GAINING_FOCUS);
            iconicTabsEffect.applyTabEffect(selectedTabView, positionOffset, IconicTabsEffect.Status.LOSING_FOCUS);
        } else {
            ImageView nextImageView = (ImageView) tabsContainer.getChildAt(position + 1);
            if (nextImageView != null)
                iconicTabsEffect.applyTabEffect(nextImageView, positionOffset, IconicTabsEffect.Status.GAINING_FOCUS);
            iconicTabsEffect.applyTabEffect(selectedTabView, 1f - positionOffset, IconicTabsEffect.Status.LOSING_FOCUS);

        }

    }

    @Override
    public void onPageSelected(int position) {

        if (lastPosition != position) {
            lastPosition = observer.getCurrentItem();
        }

        if (selectedTabView != null) {
            iconicTabsEffect.applyTabEffect(selectedTabView, 0f, IconicTabsEffect.Status.LOSING_FOCUS);
        }

        selectedTabView = (ImageView) tabsContainer.getChildAt(position);
        iconicTabsEffect.applyTabEffect(selectedTabView, 1f, IconicTabsEffect.Status.GAINING_FOCUS);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

        if (state == ViewPager.SCROLL_STATE_IDLE) {
            for (int i = 0; i < observer.getCount(); i++) {
                ImageView tab = (ImageView) tabsContainer.getChildAt(i);
                if (tab == selectedTabView) {
                    iconicTabsEffect.applyTabEffect(tab, 1f, IconicTabsEffect.Status.GAINING_FOCUS);
                } else {
                    iconicTabsEffect.applyTabEffect(tab, 0f, IconicTabsEffect.Status.LOSING_FOCUS);
                }
            }
        }

    }

    private void buildTabsViews() {

        int selectedTab = observer.getCurrentItem();
        int tabsCount = observer.getCount();

        tabsContainer.removeAllViews();

        if (tabsCount <= 0) {
            return;
        }
        LinearLayout.LayoutParams params = null;

        switch (tabsWidth) {
            case ACTUAL:
                tabsContainer.setWeightSum(0);
                params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                break;
            case FIT_WIDTH:
                tabsContainer.setWeightSum(tabsCount);
                params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                break;
            case AUTO:
                tabsContainer.setWeightSum(0);
                params = new LinearLayout.LayoutParams(minTabWidth, LinearLayout.LayoutParams.MATCH_PARENT);
                calculateAutoTabsSize();
                break;
            default:
                params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                break;
        }


        for (int i = 0; i < tabsCount; i++) {

            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(iconicProvider.getIconicDrawable(i));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            if (i == selectedTab) {
                selectedTabView = imageView;
                iconicTabsEffect.applyTabEffect(imageView, 1f, IconicTabsEffect.Status.GAINING_FOCUS);
            } else {
                iconicTabsEffect.applyTabEffect(imageView, 0f, IconicTabsEffect.Status.LOSING_FOCUS);
            }

            imageView.setOnClickListener(new TabsClickListener(i));

            tabsContainer.addView(imageView, params);
        }

        scrollToChild(selectedTab, 0);
    }


    private void calculateAutoTabsSize() {

        tabsContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tabsContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                int tabsCount = observer.getCount();

                int parentWidth = getWidth();

                int tabsTotalWidth = tabsCount * (minTabWidth + (tabsContainer.getDividerPadding() * 2));

                Log.d(TAG, "Parent Width:" + parentWidth);

                Log.d(TAG, "TabsWidth:" + tabsTotalWidth);

                if (tabsTotalWidth < parentWidth) {

                    tabsContainer.setWeightSum(tabsCount);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

                    for (int i = 0; i < tabsCount; i++) {
                        View tab = tabsContainer.getChildAt(i);
                        tab.setLayoutParams(params);
                    }

                    tabsContainer.requestLayout();


                } else {
                    //No need to modify views width
                }


            }
        });

    }

    private void scrollToChild(int position, int offset) {


        int newScrollX = tabsContainer.getChildAt(position).getLeft() + offset;

        if (position > 0 || offset > 0) {
            newScrollX -= 52;
        }

        scrollTo(newScrollX, 0);

    }

    private class TabsClickListener implements OnClickListener {
        private int position;

        private TabsClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            observer.setCurrentItem(position, true);
        }
    }


    public void setIconicTabsEffect(IconicTabsEffect iconicTabsEffect) {
        this.iconicTabsEffect = iconicTabsEffect;
    }

}
