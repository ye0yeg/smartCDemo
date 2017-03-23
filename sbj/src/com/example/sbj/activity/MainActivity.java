

package com.example.sbj.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.sbj.R;
import com.example.sbj.view.LeftMenuFragment;
import com.example.sbj.view.MainContentFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * @author shaoyyyy
 * @创建时间 2017-3-22 下午2:47:16
 * @描述 主界面
 * 
 */
public class MainActivity extends SlidingFragmentActivity {

	private static final String	LEFT_MUNE_TAG	= "left";
	private static final String	MAIN_MUNE_TAG	= "menu";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();// 初始化界面
		initData();// 初始化数据
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		//1.获取事物
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		//2.完成替换
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(), LEFT_MUNE_TAG);
		transaction.replace(R.id.fl_main_menu, new MainContentFragment(), MAIN_MUNE_TAG);
		
		//3.提交
		transaction.commit();
	}

	// The queen of Saigons. Swinging, swinging. ~
	// He is crazy y Cubano como yo lala
	private void initView() {
		setContentView(R.layout.fragment_content_tag);
		setBehindContentView(R.layout.fragment_left);
		SlidingMenu sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setBehindOffset(400);
	}
}
