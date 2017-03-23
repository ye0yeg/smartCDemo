

package com.example.sbj.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.sbj.R;
import com.example.sbj.utils.MyContants;
import com.example.sbj.utils.SpTools;

/**
 * @author shaoyyyy
 * @创建时间 2017-3-22 上午10:21:30
 * @描述 The Splash , Animation
 * 
 */
public class SplashActivity extends Activity {
	private ImageView		iv_main_view;
	private AnimationSet	as;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initView();
		startAnimation();
		initEvent();
	}

	private void initEvent() {
		// 动画完毕进入主界面
		// 判断进入向导界面还是主界面
		// 监听动画监听事件,
		as.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// 监听播完
				if (SpTools.getBoolean(getApplicationContext(),
						MyContants.isSetUp, false)) {
					// True -> Main
					System.out.println("LOAD MAIN");
					Intent main = new Intent(SplashActivity.this,
							MainActivity.class);
					startActivity(main);
					finish();
				} else {
					System.out.println("LOAD GUIRD");
					Intent in = new Intent(SplashActivity.this,
							GuideActivity.class);
					startActivity(in);
					finish();
				}

			}
		});
	}

	/**
	 * Animation 旋转, 缩放, 渐变
	 */
	private void startAnimation() {
		as = new AnimationSet(false);

		// 旋转, 锚点
		RotateAnimation ra = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		ra.setDuration(2000);
		ra.setFillAfter(true);// 完毕以后停留在当前位置
		// 添加到set中
		as.addAnimation(ra);
		// 渐变动画
		AlphaAnimation aa = new AlphaAnimation(0, 1);// 从透明到先显示
		aa.setDuration(2000);
		aa.setFillAfter(true);
		as.addAnimation(aa);

		ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		sa.setDuration(2000);
		sa.setFillAfter(true);
		as.addAnimation(sa);
		// 播放动画
		iv_main_view.startAnimation(as);

	}

	private void initView() {
		setContentView(R.layout.activity_splash);
		iv_main_view = (ImageView) findViewById(R.id.iv_splash_mainview);

	}
}
