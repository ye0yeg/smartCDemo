package com.example.sbj.basepage;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class HomeBaseTagPager extends BaseTagPage{

	public HomeBaseTagPager(Context context) {
		super(context);
	}
	
	@Override
	public void initData() {
		tv_title.setText("Home");
		TextView tv = new TextView(context);
		tv.setText("Home Content");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		super.initData();
	}
}
