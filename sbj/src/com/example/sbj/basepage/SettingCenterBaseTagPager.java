package com.example.sbj.basepage;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class SettingCenterBaseTagPager extends BaseTagPage{

	public SettingCenterBaseTagPager(Context context) {
		super(context);
	}
	
	@Override
	public void initData() {
		tv_title.setText("Setting");
		TextView tv = new TextView(context);
		tv.setText("Setting Content");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		super.initData();
	}
}
