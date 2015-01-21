package com.moshx.indicators.title;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.moshx.indicators.R;
import com.moshx.indicators.observer.ObservableView;
import com.moshx.indicators.observer.ViewPagerObserver;
import com.moshx.indicators.title.transformer.DefaultTitleTransformer;
import com.moshx.indicators.title.transformer.TitleTransformer;

/**
 * Created by M.Ersan on 1/2/15.
 */
public class TitleIndicator extends FrameLayout implements ObservableView {


    private TextView textViewTitle, textViewSwitcher;

    private int lastPosition = 0;

    private ViewPagerObserver observer;
    private TitleTransformer titleTransformer;

    public TitleIndicator(Context context) {
        this(context, null, 0);
    }

    public TitleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    public TitleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setWillNotDraw(false);
        setClipChildren(false);
        setClipToPadding(false);

        titleTransformer = new DefaultTitleTransformer(context);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.LEFT | Gravity.CENTER_VERTICAL);

        textViewTitle = internalCreateTextView(context, attrs);
        textViewTitle.setId(R.id.indicators_title);
        addView(textViewTitle, params);

        textViewSwitcher = internalCreateTextView(context, attrs);
        textViewSwitcher.setId(R.id.indicators_switcher);
        addView(textViewSwitcher, params);

        textViewSwitcher.setAlpha(0);

    }

    private TextView internalCreateTextView(Context context, AttributeSet attrs) {
        TextView textView = new TextView(context, attrs);
        textView.setBackgroundColor(Color.TRANSPARENT);
        return textView;
    }


    @Override
    public void onChanged() {

        lastPosition = observer.getCurrentItem();
        textViewTitle.setText(observer.getPageTitle(lastPosition));

    }

    @Override
    public void onInvalidated() {

        lastPosition = observer.getCurrentItem();
        textViewTitle.setText(observer.getPageTitle(lastPosition));

    }

    @Override
    public void setViewPagerObserver(ViewPagerObserver observer) {

        this.observer = observer;
        lastPosition = observer.getCurrentItem();
        textViewTitle.setText(observer.getPageTitle(lastPosition));

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        int direction = -1;
        if (lastPosition != position) {
            direction = ViewPagerObserver.DIRECTION_LEFT;
            if (positionOffset != 0f) {
                textViewSwitcher.setText(observer.getPageTitle(position));
            }
        } else {
            direction = ViewPagerObserver.DIRECTION_RIGHT;
            if (positionOffset != 0f) {
                textViewSwitcher.setText(observer.getPageTitle(position + 1));
            }
        }

        titleTransformer.transform(textViewTitle, textViewSwitcher, positionOffset, direction);

    }

    @Override
    public void onPageSelected(int position) {

        textViewTitle.setText(observer.getPageTitle(position));

        if (lastPosition != position) {
            TextView tempView = textViewTitle;
            textViewTitle = textViewSwitcher;
            textViewSwitcher = tempView;
            lastPosition = observer.getCurrentItem();
            textViewTitle.setText(observer.getPageTitle(lastPosition));
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //for rent
    }

    public void setTitleTransformer(TitleTransformer transformer) {

        if (transformer == null) {
            throw new IllegalArgumentException("TitleTransformer is null.");
        }

        textViewTitle.setX(0);
        textViewTitle.setY(0);
        textViewTitle.setAlpha(1);
        textViewTitle.setRotationX(0);
        textViewTitle.setRotationY(0);

        textViewSwitcher.setX(0);
        textViewSwitcher.setY(0);
        textViewSwitcher.setAlpha(0);
        textViewSwitcher.setRotationX(0);
        textViewSwitcher.setRotationY(0);

        titleTransformer = transformer;
        titleTransformer.setParentSize(getWidth(), getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        titleTransformer.setParentSize(w, h);
    }

    public void setToolBar(ViewGroup toolBar) {

        ViewGroup.LayoutParams params = getLayoutParams();
        int w = ViewGroup.LayoutParams.WRAP_CONTENT;
        if (params != null) {
            w = params.width;
        }
        setToolBar(toolBar, w);

    }

    public void setToolBar(ViewGroup toolBar, int width) {

        if (getParent() != null && getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeView(this);
        }

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        toolBar.addView(this, params);

    }
}
