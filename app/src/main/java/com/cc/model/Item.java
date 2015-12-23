package com.cc.model;

import android.graphics.drawable.Drawable;


import com.cc.model.res.Colors;

import java.util.Date;
import java.util.Locale;


public class Item implements java.io.Serializable {
	private long id;
	private long datetime;
	private Colors colors;
	private String mtitle;
	private String mContent;
	private String mFileName;
	private double latitude;
	private double longtitude;
	private long lastModify;
	private boolean selected;
	private Drawable reid;

	public Item() {
		this.mtitle = "";
		this.mContent = "";
		this.colors = Colors.LIGHTGREY;
	}

	public Item(long id, long datetime, Colors color, String title,
			String content, String fileName, double latitude, double longitude,
			long lastModify) {
		this.id = id;
		this.datetime = datetime;
		this.colors = color;
		this.mtitle = title;
		this.mContent = content;
		this.mFileName = fileName;
		this.latitude = latitude;
		this.longtitude = longitude;
		this.lastModify = lastModify;
	}

	public Item(long id, long datetime, Drawable color, String title,
			String content, String fileName, double latitude, double longitude,
			long lastModify) {
		this.id = id;
		this.datetime = datetime;
		this.reid = color;
		this.mtitle = title;
		this.mContent = content;
		this.mFileName = fileName;
		this.latitude = latitude;
		this.longtitude = longitude;
		this.lastModify = lastModify;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public long getDatetime() {
		return this.datetime;
	}

	public String getLocalDatetime() {
		return String.format(Locale.getDefault(), "%tF %<tR",
				new Date(datetime));
	}

	public String getLocalDate() {
		return String.format(Locale.getDefault(), "%tF ", new Date(datetime));
	}

	public String getLocaltime() {
		return String.format(Locale.getDefault(), "%tR", new Date(datetime));
	}

	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}

	public Colors getColor() {
		return colors;
	}

	public Drawable getReId() {
		return reid;
	}

	public void setReid(Drawable id) {
		this.reid = id;
	}

	public void setColor(Colors color) {
		this.colors = color;
	}

	public String getTitle() {
		return mtitle;
	}

	public void setTitle(String title) {
		this.mtitle = title;
	}

	public String getContent() {
		return mContent;
	}

	public void setContent(String content) {
		this.mContent = content;
	}

	public String getFileName() {
		return mFileName;
	}

	public void setFileName(String fileName) {
		this.mFileName = fileName;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longtitude;
	}

	public void setLongitude(double longitude) {
		this.longtitude = longitude;
	}

	public long getLastModify() {
		return lastModify;
	}

	public void setLastModify(long lastModify) {
		this.lastModify = lastModify;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
