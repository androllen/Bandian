package com.cc.model;

public interface IListItem {

	public boolean isClickable();

	public void setClickable(boolean clickable);

	int getTabSelectedIcon();

	int getTabUnselectedIcon();

	String getTitle();

}
