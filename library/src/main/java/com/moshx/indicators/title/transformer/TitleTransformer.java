package com.moshx.indicators.title.transformer;

import android.view.View;

/**
 * Created by M.Ersan on 1/5/15.
 */
public abstract class TitleTransformer {


    public abstract void transform(View view1, View view2, float positionOffset, int direction);

    public abstract void setParentSize(int width, int height);
}
