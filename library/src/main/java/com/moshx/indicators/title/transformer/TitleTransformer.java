package com.moshx.indicators.title.transformer;

import android.view.View;

/**
 * Created by M.Ersan on 1/5/15.
 */
public abstract class TitleTransformer {

    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_RIGHT = 2;

    public abstract void transform(View view1, View view2, float positionOffset, int direction);

    public abstract void setParentSize(int width, int height);
}
