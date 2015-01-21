package com.moshx.indicators.tab.effect;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.widget.ImageView;

/**
 * Created by M.Ersan on 1/21/15.
 */
public class ArgbIconicTabsEffect extends IconicTabsEffect {

    private int startInt, endInt;

    public ArgbIconicTabsEffect(int unfocusedColor, int focusedColor) {
        startInt = unfocusedColor;
        endInt = focusedColor;
    }

    @Override
    public void applyTabEffect(ImageView imageView, float value, Status status) {


        int startA = (startInt >> 24) & 0xff;
        int startR = (startInt >> 16) & 0xff;
        int startG = (startInt >> 8) & 0xff;
        int startB = startInt & 0xff;

        int endA = (endInt >> 24) & 0xff;
        int endR = (endInt >> 16) & 0xff;
        int endG = (endInt >> 8) & 0xff;
        int endB = endInt & 0xff;

        int resultColor = (int) ((startA + (int) (value * (endA - startA))) << 24) |
                (int) ((startR + (int) (value * (endR - startR))) << 16) |
                (int) ((startG + (int) (value * (endG - startG))) << 8) |
                (int) ((startB + (int) (value * (endB - startB))));


        imageView.setColorFilter(resultColor, PorterDuff.Mode.MULTIPLY);


    }
}
