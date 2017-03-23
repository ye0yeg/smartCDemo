package com.example.sbj.basepage;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class NewsCenterBaseTagPager extends BaseTagPage{

	public NewsCenterBaseTagPager(Context context) {
		super(context);
	}
	
	@Override
	public void initData() {
		tv_title.setText("News");
		TextView tv = new TextView(context);
		tv.setText("News Content");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		super.initData();
	}
}
