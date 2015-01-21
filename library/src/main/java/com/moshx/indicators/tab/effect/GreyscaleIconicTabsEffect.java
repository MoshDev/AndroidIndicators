package com.moshx.indicators.tab.effect;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by M.Ersan on 1/20/15.
 */
public class GreyscaleIconicTabsEffect extends IconicTabsEffect {


    @Override
    public void applyTabEffect(ImageView imageView, float value, Status status) {

        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(value);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageView.setColorFilter(filter);

    }
}
