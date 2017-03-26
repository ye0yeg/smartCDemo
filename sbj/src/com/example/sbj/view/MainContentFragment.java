package com.example.sbj.view;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.sbj.R;
import com.example.sbj.basepage.BaseTagPage;
import com.example.sbj.basepage.GovaffairsBaseTagPager;
import com.example.sbj.basepage.HomeBaseTagPager;
import com.example.sbj.basepage.NewsCenterBaseTagPager;
import com.example.sbj.basepage.SettingCenterBaseTagPager;
import com.example.sbj.basepage.SmartServiceBaseTagPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author shaoyyyy
 * @创建时间 2017-3-23 上午10:22:57
 * @描述 Main界面的 Fragment
 */
public class MainContentFragment extends BaseFragment {

	@ViewInject(R.id.vp_main_content_page)
	private MyViewPager viewPager;
	@ViewInject(R.id.rg_content_radios)
	private RadioGroup rg_radios; 
	private int selectIndex = 0;

	private List<BaseTagPage> pages = new ArrayList<BaseTagPage>();

	@Override
	public void initEvent() {
		// 添加自己的事件
		rg_radios.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {// 判断是哪个按钮点击的
				case R.id.rb_main_content_main:
					selectIndex = 0;
					break;
				case R.id.rb_main_content_newscenter:
					selectIndex = 1;
					break;
				case R.id.rb_main_content_smartservice:
					selectIndex = 2;
					break;
				case R.id.rb_main_content_settingcenter:
					selectIndex = 3;
					break;
				case R.id.rb_main_content_govafairs:
					selectIndex = 4;
					break;
				default:
					break;
				}
				switchPage();
			}
		});
	}
	
	/**
	 * 左侧菜单点击的时候让主界面切换不同页面
	 */
	public void leftMenuClickSwitchPage(int subSelectIndex){
		BaseTagPage baseTagPage = pages.get(selectIndex);
		baseTagPage.switchPage(subSelectIndex);
	}
	
	/**
	 * 设置选中的页面
	 */
	protected void switchPage() {
		// BaseTagPage currentPage = pages.get(selectIndex);
		viewPager.setCurrentItem(selectIndex);// 设置VP的显示的页面
		if (selectIndex == 0 || selectIndex == pages.size() - 1) {
			// 不滑动
			mainActivity.getSlidingMenu().setTouchModeAbove(
					SlidingMenu.TOUCHMODE_NONE);// 不可互动

		} else {
			mainActivity.getSlidingMenu().setTouchModeAbove(
					SlidingMenu.TOUCHMODE_FULLSCREEN);// 不可互动
			// 可滑动
		}
	}

	@Override
	public View initView() {
		
		View root = View.inflate(mainActivity, R.layout.fragment_content_view,
				null);
		// xutils, 动态注入view
		ViewUtils.inject(this, root);
		return root;
	}

	@Override
	public void initData() {
		pages.add(new HomeBaseTagPager(mainActivity));
		pages.add(new NewsCenterBaseTagPager(mainActivity));
		pages.add(new SmartServiceBaseTagPager(mainActivity));
		pages.add(new GovaffairsBaseTagPager(mainActivity));
		pages.add(new SettingCenterBaseTagPager(mainActivity));
		MyAdapter adapter = new MyAdapter();
		viewPager.setAdapter(adapter);
		
		switchPage();
		//设置第一个按钮的被选中
		rg_radios.check(R.id.rb_main_content_main);

	}

	private class MyAdapter extends PagerAdapter {

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			BaseTagPage baseTagPage = pages.get(position);
			View root = baseTagPage.getRoot();
			container.addView(root);
			baseTagPage.initData();
			return root;
		}

		@Override
		public int getCount() {
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

	}

}
