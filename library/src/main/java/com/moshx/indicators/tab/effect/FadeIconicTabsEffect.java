package com.moshx.indicators.tab.effect;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by M.Ersan on 1/21/15.
 */
public class FadeIconicTabsEffect extends IconicTabsEffect {

    @Override
    public void applyTabEffect(ImageView imageView, float value, Status status) {
        
        float alpha = Math.max(value, 0.3f);
        imageView.setAlpha(alpha);
        
    }
}
