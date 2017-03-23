package com.example.sbj.basepage;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class SmartServiceBaseTagPager extends BaseTagPage{

	public SmartServiceBaseTagPager(Context context) {
		super(context);
	}
	@Override
	public void initData() {
		tv_title.setText("Smart");
		TextView tv = new TextView(context);
		tv.setText("Smart Content");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		super.initData();
	}
}
