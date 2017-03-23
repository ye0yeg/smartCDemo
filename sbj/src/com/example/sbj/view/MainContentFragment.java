package com.example.sbj.view;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.sbj.R;
import com.example.sbj.basepage.BaseTagPage;
import com.example.sbj.basepage.GovaffairsBaseTagPager;
import com.example.sbj.basepage.HomeBaseTagPager;
import com.example.sbj.basepage.NewsCenterBaseTagPager;
import com.example.sbj.basepage.SettingCenterBaseTagPager;
import com.example.sbj.basepage.SmartServiceBaseTagPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author shaoyyyy
 * @创建时间 2017-3-23 上午10:22:57
 * @描述 Main界面的 Fragment
 */
public class MainContentFragment extends BaseFragment {

	@ViewInject(R.id.vp_main_content_page)
	private ViewPager viewPager;
	@ViewInject(R.id.rg_content_radios)
	private RadioGroup rg_radios;

	private List<BaseTagPage> pages = new ArrayList<BaseTagPage>();

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
