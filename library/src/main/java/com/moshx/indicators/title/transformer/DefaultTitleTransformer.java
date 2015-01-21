package com.moshx.indicators.title.transformer;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;

import com.moshx.indicators.observer.ViewPagerObserver;

/**
 * Created by M.Ersan on 1/5/15.
 */
public class DefaultTitleTransformer extends TitleTransformer {

    private float leftTextXPosition, titlePosition, rightTextPosition;
    private int direction = -1;


    public DefaultTitleTransformer(Context context) {

        float dp48 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, context.getResources().getDisplayMetrics());
        titlePosition = 0;
        leftTextXPosition = titlePosition - dp48;
        rightTextPosition = titlePosition + dp48;

    }

    @Override
    public void transform(View view1, View view2, float positionOffset, int direction) {
        
        if (direction == ViewPagerObserver.DIRECTION_LEFT) {
            view1.setAlpha(positionOffset);//Hiding
            view1.setX(rightTextPosition * (1f - positionOffset));//Moving to right

            view2.setAlpha(1f - positionOffset);//Showing
            view2.setX(leftTextXPosition * positionOffset);//Moving to right
        } else {
            view1.setAlpha(1f - positionOffset);//Hiding
            view1.setX(leftTextXPosition * positionOffset);

            view2.setAlpha(positionOffset);//Showing
            view2.setX(rightTextPosition * (1f - positionOffset));//Moving to left
        }
    }

    @Override
    public void setParentSize(int width, int height) {
        //for rent
    }

}
