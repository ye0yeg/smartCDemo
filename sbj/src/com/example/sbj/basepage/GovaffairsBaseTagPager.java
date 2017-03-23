package com.example.sbj.basepage;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class GovaffairsBaseTagPager extends BaseTagPage{

	public GovaffairsBaseTagPager(Context context) {
		super(context);
	}
	
	public void initData(){
		tv_title.setText("Fair");
		TextView tv = new TextView(context);
		tv.setText("GovaFairs Content");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
	}
}
