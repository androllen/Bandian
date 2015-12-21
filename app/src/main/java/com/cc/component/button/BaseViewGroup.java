package com.cc.component.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.cc.bandian.R;
import com.cc.component.button.iface.ClickItemListener;
import com.cc.model.BasicItem;
import com.cc.model.IListItem;
import com.cc.model.ViewItem;

import java.util.List;

/**
 * Created by androllen on 15/12/17.
 */
public abstract class BaseViewGroup extends FrameLayout {

    private static final String TAG = "BaseViewGroup";


    public BaseViewGroup(Context context) {
        super(context);
    }

    public BaseViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



}
