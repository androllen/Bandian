package com.cc.model;

public class BasicItem implements IListItem {

	private boolean mClickable = true;
	private String mTitle;

	@Override
	public boolean isClickable() {
		return mClickable;
	}

	@Override
	public void setClickable(boolean clickable) {
		mClickable = clickable;
	}
	@Override
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}
	@Override
	public int getTabSelectedIcon() {
		return 0;
	}

	@Override
	public int getTabUnselectedIcon() {
		return 0;
	}
}
