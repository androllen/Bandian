package com.cc.component.button.iface;

import com.cc.model.BasicItem;
import com.cc.model.ViewItem;

/**
 * Created by androllen on 15/12/17.
 */
public interface ITableViewItem<T> {

    /**
     * @param title
     */
    public void addBasicItem(String title);

    /**
     * @param title
     * @param summary
     */
    public void addBasicItem(String title, String summary);

    /**
     * @param title
     * @param summary
     * @param color
     */
    public void addBasicItem(String title, String summary, int color);

    /**
     * @param drawable
     * @param title
     * @param summary
     */
    public void addBasicItem(int drawable, String title, String summary);

    /**
     * @param drawable
     * @param title
     * @param summary
     */
    public void addBasicItem(int drawable, String title, String summary,int color);

    /**
     * @param item
     */
    public void addBasicItem(BasicItem item);

    /**
     * @param itemView
     */
    public void addViewItem(ViewItem itemView);
}
