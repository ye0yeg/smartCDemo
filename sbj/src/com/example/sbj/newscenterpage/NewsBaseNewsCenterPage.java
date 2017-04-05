package com.example.sbj.newscenterpage;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sbj.R;
import com.example.sbj.activity.MainActivity;
import com.example.sbj.domain.NewsCenterData;
import com.example.sbj.domain.NewsCenterData.NewsData.ViewTagData;
import com.example.sbj.newstpipage.TPINewsNewscenterPage;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

public class NewsBaseNewsCenterPage extends BaseNewsCenterPage {

	@ViewInject(R.id.newcenter_vp)
	private ViewPager vp_newscenter;
	@ViewInject(R.id.newcenter_tpi)
	private TabPageIndicator tpi_newcenter;
	//页签的数据
	private List<ViewTagData> viewTagDatas; //页签的数据
	
	public NewsBaseNewsCenterPage(MainActivity mainActivity, List<ViewTagData> children) {
		super(mainActivity);
		this.viewTagDatas = children;
	}
	@Override
	public void initEvent() {
		tpi_newcenter.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				if(arg0 == 0){
					mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				}else{
					mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		super.initEvent();
	}
	
	@Override
	public void initData() {
		//设置数据
		MyAdapter adapter = new MyAdapter();
		vp_newscenter.setAdapter(adapter);
		tpi_newcenter.setViewPager(vp_newscenter);
	}
	
	private class MyAdapter extends PagerAdapter{

		@Override
		public CharSequence getPageTitle(int position) {
			//获取页签的数据
			return viewTagDatas.get(position).title;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			TPINewsNewscenterPage tpiPager = new TPINewsNewscenterPage(mainActivity,viewTagDatas.get(position));
			View rootView = tpiPager.getRootView();
			container.addView(rootView);
			return rootView;
			/*TextView tv = new TextView(mainActivity);
			tv.setText(viewTagDatas.get(position).title);
			tv.setTextSize(25);
			tv.setGravity(Gravity.CENTER);
			container.addView(tv);
			return tv;*/
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return viewTagDatas.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
		
		//U should Know I Just wanna be alone.
		// Alone, but not sad.
		
		
	}
	
	@Override
	public View initView() {
		View newsCenterRoot = View.inflate(mainActivity, R.layout.newscenterpage_content, null);
		//通过xutils注入组件
		ViewUtils.inject(this, newsCenterRoot);
		return newsCenterRoot;
	} 
}
