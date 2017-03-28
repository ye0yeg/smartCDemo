package com.example.sbj.newscenterpage;

import android.view.View;

import com.example.sbj.activity.MainActivity;

/**
 * @author Administrator
 *
 */
public abstract class BaseNewsCenterPage {
	protected MainActivity mainActivity;
	protected View root;
	public BaseNewsCenterPage(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		root = initView();
		initEvent();
	}
	//TEXTGITTTT, 现在的推送应该在trunk干下了吧
	public void initEvent(){};
	/**
	 * 覆盖此方法来现实自定义VIEW
	 * @return
	 * 
	 */
	public abstract View initView();
	
	public View getRoot(){
		return root;
	}
	
	/**
	 * 覆盖此方法完成显示
	 */
	public void initData(){
		
	}
}
