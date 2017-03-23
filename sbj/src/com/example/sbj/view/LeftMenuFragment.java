package com.example.sbj.view;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * @author shaoyyyy
 * @创建时间 2017-3-23 上午10:22:39	
 * @描述 Left菜单的fragment
 * 
 */
public class LeftMenuFragment extends BaseFragment {

	@Override
	public View initView() {
		TextView tv = new TextView(mainActivity);
		tv.setText("LEFT");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}
}
