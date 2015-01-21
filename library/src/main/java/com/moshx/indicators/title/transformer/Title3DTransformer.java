package com.moshx.indicators.title.transformer;

import android.content.Context;
import android.view.View;

import com.moshx.indicators.observer.ViewPagerObserver;

/**
 * Created by M.Ersan on 1/5/15.
 */
public class Title3DTransformer extends TitleTransformer {


    public Title3DTransformer(Context context) {

    }

    @Override
    public void transform(View view1, View view2, float positionOffset, int direction) {
        view1.setPivotX(0);
        view1.setPivotY(view1.getHeight() / 2);

        view2.setPivotX(0);
        view2.setPivotY(view2.getHeight() / 2);

        if (direction == ViewPagerObserver.DIRECTION_LEFT) {
            view1.setAlpha(1f);//Hiding
            view1.setRotationY(90 * ((1f - positionOffset)));

            view2.setAlpha(1f);//Showing
            view2.setRotationY(-90 + 90 * (1f - positionOffset));
        } else {
            view1.setAlpha(1f );//Hiding
            view1.setRotationY(-90 * positionOffset);

            view2.setAlpha(1f);//Showing
            view2.setRotationY(90 * (1f - positionOffset));
        }
    }

    @Override
    public void setParentSize(int width, int height) {
        //for rent
    }

}
