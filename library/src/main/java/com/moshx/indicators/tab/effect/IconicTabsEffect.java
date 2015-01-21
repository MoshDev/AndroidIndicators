package com.moshx.indicators.tab.effect;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by M.Ersan on 1/20/15.
 */
public abstract class IconicTabsEffect {

    public enum Status {
        GAINING_FOCUS, LOSING_FOCUS;
    }

    public abstract void applyTabEffect(ImageView imageView, float value, Status status);
}
