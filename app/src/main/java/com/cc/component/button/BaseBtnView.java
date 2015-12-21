package com.cc.component.button;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by androllen on 15/12/17.
 */
public abstract class BaseBtnView extends BaseViewGroup{

    private static final String TAG = "BaseBtnView";
    public abstract void clearDate();


    public BaseBtnView(Context context) {
        super(context);
    }

    public BaseBtnView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
