package com.example.sbj.basepage;

import com.example.sbj.activity.MainActivity;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class GovaffairsBaseTagPager extends BaseTagPage{

	public GovaffairsBaseTagPager(MainActivity context) {
		super(context);
	}
	
	public void initData(){
		tv_title.setText("Fair");
		TextView tv = new TextView(mainActivity);
		tv.setText("GovaFairs Content");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		fl_content.addView(tv);
	}
}
