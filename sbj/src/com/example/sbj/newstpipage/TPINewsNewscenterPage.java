package com.example.sbj.newstpipage;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap.Config;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sbj.R;
import com.example.sbj.activity.MainActivity;
import com.example.sbj.domain.NewsCenterData.NewsData.ViewTagData;
import com.example.sbj.domain.TPINewsData;
import com.example.sbj.domain.TPINewsData.TPINewsData_Data.TPINewsData_Data_LunBoData;
import com.example.sbj.utils.DensityUtil;
import com.example.sbj.utils.MyContants;
import com.example.sbj.utils.SpTools;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author Administrator
 * @创建时间 2015-7-7 上午9:53:48
 * @描述 新闻中心页签对应的页面
 * 
 *     @ svn提交者：$Author: gd $ @ 提交时间: $Date: 2015-07-07 11:09:11 +0800 (Tue, 07
 *     Jul 2015) $ @ 当前版本: $Rev: 40 $
 */
public class TPINewsNewscenterPage {
	private static final Class<Object> TPINewsData = null;

	// 所有组件

	@ViewInject(R.id.vp_tpi_news_lunbo_pic)
	private ViewPager vp_lunbo; // 轮播图的显示组件

	@ViewInject(R.id.tv_tpi_news_pic_desc)
	private TextView tv_pic_desc;// 图片的描述信息

	@ViewInject(R.id.ll_tpi_news_pic_points)
	private LinearLayout ll_points;// 轮播图的每张图片对应的点组合

	@ViewInject(R.id.lv_tpi_news_listnews)
	private ListView lv_listnews;// 显示列表新闻的组件

	// 数据
	private MainActivity mainActivity;
	private View root;
	private ViewTagData viewTagData;// 页签对应的数据

	private Gson gson;

	// 轮播图的数据
	private List<TPINewsData_Data_LunBoData> lunboDatas = new ArrayList<TPINewsData.TPINewsData_Data.TPINewsData_Data_LunBoData>();

	// 轮播图的适配器
	private LunBoAdapter lunboAdapter;

	private BitmapUtils bitmapUtils;

	private int picSelectIndex;

	public TPINewsNewscenterPage(MainActivity mainActivity,
			ViewTagData viewTagData) {
		this.mainActivity = mainActivity;
		this.viewTagData = viewTagData;
		gson = new Gson();

		// xutils bitmag 组件
		bitmapUtils = new BitmapUtils(mainActivity);
		bitmapUtils.configDefaultBitmapConfig(Config.ARGB_4444);

		initView();// 初始化界面
		initData();// 初始化数据
		initEvent();// 初始化事件
	}

	private void initEvent() {
		vp_lunbo.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				//当滑动完了
				picSelectIndex = position;
				setPicDescAndPointSelect(picSelectIndex);
				// Not things is change, 
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}

	private void initData() {
		// 轮播图的适配器
		lunboAdapter = new LunBoAdapter();
		// 给轮播图
		vp_lunbo.setAdapter(lunboAdapter);

		// 从本地获取数据
		String jsonCache = SpTools.getString(mainActivity, viewTagData.url, "");
		if (!TextUtils.isEmpty(jsonCache)) {
			// 有数据，解析数据
			TPINewsData newsData = parseJson(jsonCache);
			// 处理数据
			processData(newsData);
		}

		getDataFromNet();// 从网络获取数据
	}

	/**
	 * @param newsData
	 */
	private void processData(TPINewsData newsData) {
		// 完成数据的处理

		// 1.设置轮播图的数据
		setLunBoData(newsData);

		// 2 .轮播图点
		initPoints();
		
		picSelectIndex = 0;
		setPicDescAndPointSelect(picSelectIndex);
	}

	private void setPicDescAndPointSelect(int picSelectIndex) {
		//设置描述信息
		tv_pic_desc.setText(lunboDatas.get(picSelectIndex).title);
		
		//点是否是选中的
		for (int i = 0; i < lunboDatas.size(); i++) {
			ll_points.getChildAt(i).setEnabled(i == picSelectIndex);
		}
	}

	private void initPoints() {
		ll_points.removeAllViews();
		// 有几张几个点
		for (int i = 0; i < lunboDatas.size(); i++) {
			View v_point = new View(mainActivity);
			v_point.setBackgroundResource(R.drawable.point_seletor);
			v_point.setEnabled(false);
			LayoutParams params = new LayoutParams(DensityUtil.dip2px(
					mainActivity, 5), DensityUtil.dip2px(mainActivity, 5));
			params.leftMargin = DensityUtil.dip2px(mainActivity, 10);
			v_point.setLayoutParams(params);
			ll_points.addView(v_point);
		}
	}

	private void setLunBoData(TPINewsData newsData) {
		// 获取轮播图的数据
		lunboDatas = newsData.data.topnews;

		lunboAdapter.notifyDataSetChanged();// 更新界面
	}

	/**
	 * @author Administrator
	 * @创建时间 2015-7-7 上午10:54:11
	 * @描述 轮播图的适配器
	 * 
	 *     @ svn提交者：$Author: gd $ @ 提交时间: $Date: 2015-07-07 11:09:11 +0800 (Tue,
	 *     07 Jul 2015) $ @ 当前版本: $Rev: 40 $
	 */
	private class LunBoAdapter extends PagerAdapter {

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView iv_lunbo_pic = new ImageView(mainActivity);

			// 设备默认的图片,网络缓慢
			iv_lunbo_pic.setImageResource(R.drawable.home_scroll_default);
			iv_lunbo_pic.setScaleType(ScaleType.FIT_XY);

			// 给图片添加数据
			TPINewsData_Data_LunBoData tpiNewsData_Data_LunBoData = lunboDatas
					.get(position);

			// 图片的url
			String topimageUrl = tpiNewsData_Data_LunBoData.topimage;

			// 把url的图片给iv_lunbo_pic
			// 异步加载图片，并且显示到组件中
			bitmapUtils.display(iv_lunbo_pic, topimageUrl);

			container.addView(iv_lunbo_pic);

			return iv_lunbo_pic;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView((View) object);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lunboDatas.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

	}

	private TPINewsData parseJson(String jsonData) {
		// 解析json数据

		TPINewsData tpiNewsData = gson.fromJson(jsonData, TPINewsData.class);
		return tpiNewsData;
	}

	private void getDataFromNet() {
		// httpUtils
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, MyContants.SERVICEURL + viewTagData.url,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// 请求数据成功
						String jsonData = responseInfo.result;

						// 保存数据到本地
						SpTools.setString(mainActivity, viewTagData.url,
								jsonData);

						// 解析数据
						TPINewsData newsData = parseJson(jsonData);

						// 处理数据
						processData(newsData);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// 请求数据失败
					}
				});
	}

	private void initView() {
		// 页签对应页面的根布局
		root = View.inflate(mainActivity, R.layout.tpi_news_content, null);

		ViewUtils.inject(this, root);
	}

	public View getRootView() {
		return root;
	}

}
