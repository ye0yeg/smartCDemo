

package com.example.sbj.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.example.sbj.R;
import com.example.sbj.utils.DensityUtil;
import com.example.sbj.utils.MyContants;
import com.example.sbj.utils.SpTools;

/**
 * @author shaoyyyy
 * @创建时间 2017-3-22 上午11:40:26
 * @描述 GUIDE , using Viewpager to switch
 * 
 */
public class GuideActivity extends Activity {
	private ViewPager		vp_guides;
	private LinearLayout	ll_point;
	private View			v_point;
	private Button			bt_startExp;
	private List<ImageView>	guids;
	private MyAdapter		adapter;
	private int				disPoints;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
		initEvent();// 初始化方法

	}

	@SuppressWarnings("deprecation")
	private void initEvent() {

		// 监听布局完成触发的结果, 所有界面进行显示才会触发该方法
		v_point.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						v_point.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						disPoints = ll_point.getChildAt(1).getLeft()
								- ll_point.getChildAt(0).getLeft();
					}
				});

		bt_startExp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 保存设置的状态
				SpTools.setBoolean(getApplicationContext(), MyContants.isSetUp,
						true);
				// 进入主界面
				Intent main = new Intent(GuideActivity.this, MainActivity.class);
				startActivity(main);
				// 关闭自己
				finish();
			}
		});

		// 给ViewPage添加页面改变的事件
		vp_guides.setOnPageChangeListener(new OnPageChangeListener() {
			
			/**
			 * 当前的Viewpage显示的页面
			 * 
			 * @param position
			 */
			@Override
			public void onPageSelected(int position) {
				if (position == guids.size() - 1) {
					bt_startExp.setVisibility(View.VISIBLE);// 设置按钮的显示

				} else {
					// 不是最后一页隐藏
					bt_startExp.setVisibility(View.GONE);
				}
			}

			/**
			 * 页面滑动的的过程中调用的方法
			 * 
			 * @param position
			 *            位置
			 * @param positionOffset
			 *            偏移的比例
			 * @param positionOffsetPixels
			 *            偏移的像素 (px)
			 */
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// positionOffset来控制redPoint 20dp
				float leftMargin = disPoints * (position + positionOffset);
				// 获取红点距离
				RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v_point
						.getLayoutParams();
				layoutParams.leftMargin = Math.round(leftMargin);// 对float类型四舍五入

				// 设置布局参数
				v_point.setLayoutParams(layoutParams);
			}

			/**
			 * @param state
			 */
			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	private void initData() {
		// viewpaper view adapter
		// 图片数组
		int[] pics = new int[] { R.drawable.guide_1, R.drawable.guide_2,
				R.drawable.guide_3 };
		guids = new ArrayList<ImageView>();
		// 初始化数据
		for (int i = 0; i < pics.length; i++) {
			ImageView iv_temp = new ImageView(getApplicationContext());
			iv_temp.setBackgroundResource(pics[i]);
			guids.add(iv_temp);
			// 加红点
			View v_point = new View(this);
			v_point.setBackgroundResource(R.drawable.gray_point);
			int dip = 10;

			LayoutParams params = new LayoutParams(DensityUtil.dip2px(
					getApplicationContext(), dip), DensityUtil.dip2px(
					getApplicationContext(), dip));// 单位是px, 不是dp ,
			// 所以不是设别像素
			if (i != 0) {
				params.leftMargin = 10;
			}
			v_point.setLayoutParams(params);

			ll_point.addView(v_point);
		}
		// 开始创建适配器
		adapter = new MyAdapter();

		vp_guides.setAdapter(adapter);
	}

	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return guids.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {

			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View child = guids.get(position);
			container.addView(child);

			return child;
		}
	}

	private void initView() {
		setContentView(R.layout.activity_guide);
		// Vp组件
		vp_guides = (ViewPager) findViewById(R.id.vp_guide_pages);
		// 动态POINT
		ll_point = (LinearLayout) findViewById(R.id.ll_guide_points);
		// red point
		v_point = findViewById(R.id.v_guide_redpoint);
		// 开始
		bt_startExp = (Button) findViewById(R.id.bt_guide_startexp);
	}

}
