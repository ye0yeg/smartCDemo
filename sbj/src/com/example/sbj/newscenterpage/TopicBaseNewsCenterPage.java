package com.example.sbj.newscenterpage;

import com.example.sbj.activity.MainActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class TopicBaseNewsCenterPage extends BaseNewsCenterPage {

	public TopicBaseNewsCenterPage(MainActivity mainActivity) {
		super(mainActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		TextView tv = new TextView(mainActivity);
		tv.setText("TopicBaseNewsCenterPage");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

}
