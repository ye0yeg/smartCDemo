package com.example.sbj.basepage;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sbj.R;
import com.example.sbj.activity.MainActivity;

/**
 * @author Administrator
 * 
 */
public class BaseTagPage {
	protected MainActivity mainActivity;
	protected View root;
	protected ImageButton ib_menu; // 按钮IB
	protected TextView tv_title;
	protected FrameLayout fl_content;

	public BaseTagPage(MainActivity context) {
		this.mainActivity =  context;
		initView();// 初始化布局
		//initData(); 此处不适合初始化数据， 不然在所有界面中都会加载数据
		initEvent();
	}

	public void initEvent() {
		ib_menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//打开或者关闭
				mainActivity.getSlidingMenu().toggle();//左侧菜单的开关
			}
		});
	}

	protected void initView() {
		// 界面的根布局
		root = View.inflate(mainActivity, R.layout.fragment_content_base_content,
				null);
		ib_menu = (ImageButton) root.findViewById(R.id.ib_base_content_menu);
		tv_title = (TextView) root.findViewById(R.id.tv_base_content_title);
		fl_content = (FrameLayout) root.findViewById(R.id.fl_base_content_tag);
	}

	public void initData() {
	}
	
	public void switchPage(int position){
		
	}

	// 获取根布局
	public View getRoot() {
		return root;
	}

}
