package com.cc.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.bandian.R;

public class Fragment1  extends Fragment{
	//这个是我们必须重写的方法，返回的VIew就是将来显示到界面上的内容
	@Override
	public View onCreateView(LayoutInflater inflater, 
			ViewGroup container,Bundle savedInstanceState) {
		View messageLayout = 
				inflater.inflate(R.layout.fragment1,
				container, false);
		return messageLayout;
	}
}
