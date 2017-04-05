package com.example.sbj.view;

import com.example.sbj.R;

import android.R.array;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * @author shaoyyyy
 * @创建时间 2017-4-5 下午2:34:45	
 * @描述 自定义刷新头和尾的ListView , 下拉刷新和尾部加载
 * 
 * 
 */
public class RefreshListView extends ListView {
	
	private View	root;
	private LinearLayout	head;
	private LinearLayout	ll_refresh_head_root;

	public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		this(context,attrs,0);
		
	}

	public RefreshListView(Context context) {
		this(context, null);
	}
	
	private void initView() {
		initRoot();
		initHead();
		
	}

	/**
	 * 底部
	 */
	private void initRoot(){
		root = View.inflate(getContext(), R.layout.listview_refresh_root, null);
		//加载到LV中
		addFooterView(root);
	}
	
	/**
	 * @param view
	 * 		LunBo View
	 */
	public void addLunBoView(View view){
		head.addView(view);
	}
	
	/**
	 * 头部
	 */
	private void initHead(){
		head = (LinearLayout) View.inflate(getContext(), R.layout.listview_head_content, null);
		ll_refresh_head_root = (LinearLayout) head.findViewById(R.id.ll_listview_head_root);
		
		addHeaderView(head);
	}

}
