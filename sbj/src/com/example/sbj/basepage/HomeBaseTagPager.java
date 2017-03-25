package com.example.sbj.basepage;

import javax.crypto.spec.IvParameterSpec;

import com.example.sbj.activity.MainActivity;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class HomeBaseTagPager extends BaseTagPage{

	public HomeBaseTagPager(MainActivity context) {
		super(context);
	}
	
	@Override
	public void initData() {
		ib_menu.setVisibility(View.GONE);
		tv_title.setText("Home");
		TextView tv = new TextView(mainActivity);
		tv.setText("Home Content");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		fl_content.addView(tv);
		super.initData();
	}
}
