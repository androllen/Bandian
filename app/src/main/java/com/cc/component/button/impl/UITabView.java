package com.cc.component.button.impl;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cc.bandian.R;
import com.cc.component.button.BaseTabLayout;
import com.cc.model.IListItem;
import com.cc.model.ViewItem;
import com.cc.tool.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by androllen on 15/12/17.
 */
public class UITabView extends BaseTabLayout {

    private static final String TAG = "UITabView";

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};


    public UITabView(Context context) {
        super(context);
    }

    public UITabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.UITabView);

        mIndicatorStyle = ta.getInt(R.styleable.UITabView_tl_divider_color, 0);
        mIndicatorColor = ta.getColor(R.styleable.UITabView_tl_indicator_color, Color.parseColor(mIndicatorStyle == STYLE_BLOCK ? "#4B6A87" : "#ffffff"));
        mIndicatorHeight = ta.getDimension(R.styleable.UITabView_tl_indicator_height,
                DisplayUtils.dp2px(mIndicatorStyle == STYLE_TRIANGLE ? 4 : (mIndicatorStyle == STYLE_BLOCK ? -1 : 2)));
        mIndicatorWidth = ta.getDimension(R.styleable.UITabView_tl_indicator_width, DisplayUtils.dp2px(mIndicatorStyle == STYLE_TRIANGLE ? 10 : -1));
        mIndicatorCornerRadius = ta.getDimension(R.styleable.UITabView_tl_indicator_corner_radius, DisplayUtils.dp2px(mIndicatorStyle == STYLE_BLOCK ? -1 : 0));
        mIndicatorMarginLeft = ta.getDimension(R.styleable.UITabView_tl_indicator_margin_left, DisplayUtils.dp2px(0));
        mIndicatorMarginTop = ta.getDimension(R.styleable.UITabView_tl_indicator_margin_top, DisplayUtils.dp2px(mIndicatorStyle == STYLE_BLOCK ? 7 : 0));
        mIndicatorMarginRight = ta.getDimension(R.styleable.UITabView_tl_indicator_margin_right, DisplayUtils.dp2px(0));
        mIndicatorMarginBottom = ta.getDimension(R.styleable.UITabView_tl_indicator_margin_bottom, DisplayUtils.dp2px(mIndicatorStyle == STYLE_BLOCK ? 7 : 0));
        mIndicatorAnimEnable = ta.getBoolean(R.styleable.UITabView_tl_indicator_anim_enable, false);
        mIndicatorBounceEnable = ta.getBoolean(R.styleable.UITabView_tl_indicator_bounce_enable, true);
        mIndicatorAnimDuration = ta.getInt(R.styleable.UITabView_tl_indicator_anim_duration, -1);
        mIndicatorGravity = ta.getInt(R.styleable.UITabView_tl_indicator_gravity, Gravity.BOTTOM);

        mUnderlineColor = ta.getColor(R.styleable.UITabView_tl_underline_color, Color.parseColor("#ffffff"));
        mUnderlineHeight = ta.getDimension(R.styleable.UITabView_tl_underline_height, DisplayUtils.dp2px(0));
        mUnderlineGravity = ta.getInt(R.styleable.UITabView_tl_underline_gravity, Gravity.BOTTOM);

        mDividerColor = ta.getColor(R.styleable.UITabView_tl_divider_color, Color.parseColor("#ffffff"));
        mDividerWidth = ta.getDimension(R.styleable.UITabView_tl_divider_width, DisplayUtils.dp2px(0));
        mDividerPadding = ta.getDimension(R.styleable.UITabView_tl_divider_padding, DisplayUtils.dp2px(12));

        mTextsize = ta.getDimension(R.styleable.UITabView_tl_textsize, DisplayUtils.sp2px(13f));
        mTextSelectColor = ta.getColor(R.styleable.UITabView_tl_textSelectColor, Color.parseColor("#ffffff"));
        mTextUnselectColor = ta.getColor(R.styleable.UITabView_tl_textUnselectColor, Color.parseColor("#AAffffff"));
        mTextBold = ta.getBoolean(R.styleable.UITabView_tl_textBold, false);
        //mTextAllCaps = ta.getBoolean(R.styleable.UITabView_tl_textAllCaps, false);

        mIconVisible = ta.getBoolean(R.styleable.UITabView_tl_iconVisible, true);
        mIconGravity = ta.getInt(R.styleable.UITabView_tl_iconGravity, Gravity.TOP);
        mIconWidth = ta.getDimension(R.styleable.UITabView_tl_iconWidth, DisplayUtils.dp2px(0));
        mIconHeight = ta.getDimension(R.styleable.UITabView_tl_iconHeight, DisplayUtils.dp2px(0));
        mIconMargin = ta.getDimension(R.styleable.UITabView_tl_iconMargin, DisplayUtils.dp2px(2.5f));

        mTabSpaceEqual = ta.getBoolean(R.styleable.UITabView_tl_tab_space_equal, true);
        mTabWidth = ta.getDimension(R.styleable.UITabView_tl_tab_width, DisplayUtils.dp2px(-1));
        mTabPadding = ta.getDimension(R.styleable.UITabView_tl_tab_padding, mTabSpaceEqual || mTabWidth > 0 ? DisplayUtils.dp2px(0) : DisplayUtils.dp2px(10));

        ta.recycle();
    }

    @Override
    public LinearLayout createView(LayoutInflater paramLayoutInflater) {
        LinearLayout mMainContainer = (LinearLayout) paramLayoutInflater.inflate(
                R.layout.ctl_cpt_list_container, null);

        return mMainContainer;
    }

    @Override
    public List<IListItem> getBasicItems() {
        List<IListItem> mItemList = new ArrayList<IListItem>();
        for (int i = 0; i < mTitles.length; i++) {
            mItemList.add(new ViewItem(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        return mItemList;
    }

}
