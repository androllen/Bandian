package com.cc.viewmodel.Listener;

import com.cc.component.group.UIRadio;

/**
 * Created by androllen on 2015/8/24.
 */
public interface OnCheckedChangeListener {
    /**
     * <p>
     * Called when the checked radio button has changed. When the selection
     * is cleared, checkedId is -1.
     * </p>
     *
     * @param group
     *            the group in which the checked radio button has changed
     * @param checkedId
     *            the unique identifier of the newly checked radio button
     */
    public void onCheckedChanged(UIRadio group, int checkedId);
}

