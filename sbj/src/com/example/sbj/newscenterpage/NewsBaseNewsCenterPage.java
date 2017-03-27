package com.example.sbj.newscenterpage;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sbj.R;
import com.example.sbj.activity.MainActivity;
import com.example.sbj.domain.NewsCenterData.NewsData.ViewTagData;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
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
			TextView tv = new TextView(mainActivity);
			tv.setText(viewTagDatas.get(position).title);
			tv.setTextSize(25);
			tv.setGravity(Gravity.CENTER);
			container.addView(tv);
			return tv;
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
		
		
		
	}
	
	@Override
	public View initView() {
		View newsCenterRoot = View.inflate(mainActivity, R.layout.newscenterpage_content, null);
		//通过xutils注入组件
		ViewUtils.inject(this, newsCenterRoot);
		return newsCenterRoot;
	} 
}
