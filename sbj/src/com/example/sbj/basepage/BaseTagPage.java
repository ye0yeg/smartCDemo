package com.example.sbj.basepage;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sbj.R;

/**
 * @author Administrator
 * 
 */
public class BaseTagPage {
	protected Context context;
	protected View root;
	protected ImageButton ib_menu; // 按钮IB
	protected TextView tv_title;
	protected FrameLayout fl_content;

	public BaseTagPage(Context context) {
		this.context = context;
		initView();// 初始化布局
		initData();
		initEvent();

	}

	private void initEvent() {

	}

	protected void initView() {
		// 界面的根布局
		root = View.inflate(context, R.layout.fragment_content_base_content,
				null);
		ib_menu = (ImageButton) root.findViewById(R.id.ib_base_content_menu);
		tv_title = (TextView) root.findViewById(R.id.tv_base_content_title);
		fl_content = (FrameLayout) root.findViewById(R.id.fl_base_content_tag);

	}

	public void initData() {

	}

	// 获取根布局
	public View getRoot() {
		return root;
	}

}
