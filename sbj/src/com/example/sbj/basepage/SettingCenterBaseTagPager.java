package com.example.sbj.basepage;

import com.example.sbj.activity.MainActivity;

import android.content.Context;
import android.net.VpnService;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class SettingCenterBaseTagPager extends BaseTagPage{

	public SettingCenterBaseTagPager(MainActivity context) {
		super(context);
	}
	
	@Override
	public void initData() {
		tv_title.setText("Setting");
		ib_menu.setVisibility(View.GONE);
		TextView tv = new TextView(mainActivity);
		tv.setText("Setting Content");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		fl_content.addView(tv);
		super.initData();
	}
}
