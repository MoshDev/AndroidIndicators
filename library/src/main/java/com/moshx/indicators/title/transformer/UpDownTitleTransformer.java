package com.moshx.indicators.title.transformer;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

import com.moshx.indicators.observer.ViewPagerObserver;

/**
 * Created by M.Ersan on 1/5/15.
 */
public class UpDownTitleTransformer extends TitleTransformer {

    private float topTextXPosition, titlePosition, bottomPosition;
    private int direction = -1;

    public UpDownTitleTransformer(Context context) {

        float dp48 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, context.getResources().getDisplayMetrics());
        titlePosition = 0;
        topTextXPosition = titlePosition - dp48;
        bottomPosition = titlePosition + dp48;
    }

    @Override
    public void transform(View view1, View view2, float positionOffset, int direction) {
        
        if (direction == ViewPagerObserver.DIRECTION_LEFT) {
            view1.setAlpha(positionOffset);//Hiding
            view1.setY(bottomPosition * (1f - positionOffset));//Moving to right

            view2.setAlpha(1f - positionOffset);//Showing
            view2.setY(topTextXPosition * positionOffset);//Moving to right
        } else {
            view1.setAlpha(1f - positionOffset);//Hiding
            view1.setY(topTextXPosition * positionOffset);

            view2.setAlpha(positionOffset);//Showing
            view2.setY(bottomPosition * (1f - positionOffset));//Moving to left
        }
    }

    @Override
    public void setParentSize(int width, int height) {
        topTextXPosition = -height;
        bottomPosition = height;
    }
}
