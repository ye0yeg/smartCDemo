package com.example.sbj.basepage;

import com.example.sbj.activity.MainActivity;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class SmartServiceBaseTagPager extends BaseTagPage{

	public SmartServiceBaseTagPager(MainActivity context) {
		super(context);
	}
	@Override
	public void initData() {
		tv_title.setText("Smart");
		TextView tv = new TextView(mainActivity);
		tv.setText("Smart Content");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		fl_content.addView(tv);
		super.initData();
	}
}
