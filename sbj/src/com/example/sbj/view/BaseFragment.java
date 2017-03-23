

package com.example.sbj.view;

import com.example.sbj.activity.MainActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author shaoyyyy
 * 
 */
public abstract class BaseFragment extends Fragment {

	protected MainActivity	mainActivity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainActivity = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = initView(); //View 
		return root;
	}

	/**
	 * 只要实现该方法就可以得到一个VIEW , 必须覆盖来完成界面的显示
	 * @return
	 */
	public abstract View initView();

	public void initData(){}
	/**
	 * 子类覆盖此方法完成事件的添加
	 */
	public void initEvent(){};
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		//初始化事件和数据
		super.onActivityCreated(savedInstanceState);
		initData(); //初始化数据
		initEvent(); //初始化事件

	}

}
