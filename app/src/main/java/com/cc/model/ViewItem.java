package com.cc.model;

import android.view.View;

public class ViewItem implements IListItem {

	private View mView;

	private boolean mClickable = true;
	private int mDrawable = -1;
	private String mTitle;
	private String mSubtitle;
	private int mColor = -1;
	public int selectedIcon;
	public int unSelectedIcon;

	public ViewItem(View view) {
		this.mView = view;
	}

	public View getView() {
		return this.mView;
	}

	public ViewItem(String _title) {
		this.mTitle = _title;
	}

	public ViewItem(String _title, String _subtitle) {
		this.mTitle = _title;
		this.mSubtitle = _subtitle;
	}

	public ViewItem(String _title, String _subtitle, int _color) {
		this.mTitle = _title;
		this.mSubtitle = _subtitle;
		this.mColor = _color;
	}

	public ViewItem(String _title, String _subtitle, boolean _clickable) {
		this.mTitle = _title;
		this.mSubtitle = _subtitle;
		this.mClickable = _clickable;
	}


	public ViewItem(String title, int selectedIcon, int unSelectedIcon) {
		this.mTitle = title;
		this.selectedIcon = selectedIcon;
		this.unSelectedIcon = unSelectedIcon;
	}
	public ViewItem(int _drawable, String _title, String _subtitle) {
		this.mDrawable = _drawable;
		this.mTitle = _title;
		this.mSubtitle = _subtitle;
	}

	public ViewItem(int _drawable, String _title, String _subtitle, int _color) {
		this.mDrawable = _drawable;
		this.mTitle = _title;
		this.mSubtitle = _subtitle;
		this.mColor = _color;
	}

	public int getDrawable() {
		return mDrawable;
	}

	public void setDrawable(int drawable) {
		this.mDrawable = drawable;
	}

	@Override
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}
	public String getSubtitle() {
		return mSubtitle;
	}

	public void setSubtitle(String summary) {
		this.mSubtitle = summary;
	}

	public int getColor() {
		return mColor;
	}

	public void setColor(int mColor) {
		this.mColor = mColor;
	}

	@Override
	public boolean isClickable() {
		return mClickable;
	}

	@Override
	public void setClickable(boolean clickable) {
		mClickable = clickable;
	}
	@Override
	public int getTabSelectedIcon() {
		return selectedIcon;
	}

	@Override
	public int getTabUnselectedIcon() {
		return unSelectedIcon;
	}
}
