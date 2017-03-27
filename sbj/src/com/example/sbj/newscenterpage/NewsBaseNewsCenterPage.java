package com.example.sbj.newscenterpage;

import com.example.sbj.activity.MainActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class NewsBaseNewsCenterPage extends BaseNewsCenterPage {

	public NewsBaseNewsCenterPage(MainActivity mainActivity) {
		super(mainActivity);
	}

	@Override
	public View initView() {
		TextView tv = new TextView(mainActivity);
		tv.setText("NewsBaseNewsCenterPage");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}
	
}
