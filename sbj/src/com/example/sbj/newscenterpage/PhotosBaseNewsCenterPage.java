package com.example.sbj.newscenterpage;

import com.example.sbj.activity.MainActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class PhotosBaseNewsCenterPage extends BaseNewsCenterPage {

	public PhotosBaseNewsCenterPage(MainActivity mainActivity) {
		super(mainActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		TextView tv = new TextView(mainActivity);
		tv.setText("PhotosBaseNewsCenterPage");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

}
