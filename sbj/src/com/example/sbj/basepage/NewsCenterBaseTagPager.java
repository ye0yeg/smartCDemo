package com.example.sbj.basepage;

import java.util.ArrayList;
import java.util.List;

import android.view.Gravity;
import android.widget.TextView;

import com.example.sbj.activity.MainActivity;
import com.example.sbj.domain.NewsCenterData;
import com.example.sbj.newscenterpage.BaseNewsCenterPage;
import com.example.sbj.newscenterpage.InteractBaseNewsCenterPage;
import com.example.sbj.newscenterpage.NewsBaseNewsCenterPage;
import com.example.sbj.newscenterpage.PhotosBaseNewsCenterPage;
import com.example.sbj.newscenterpage.TopicBaseNewsCenterPage;
import com.example.sbj.utils.MyContants;
import com.example.sbj.view.LeftMenuFragment.OnSwitchPageListener;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class NewsCenterBaseTagPager extends BaseTagPage {

	// 新闻中心要显示的4个页面
	private List<BaseNewsCenterPage> newsCentPages = new ArrayList<BaseNewsCenterPage>();
	private NewsCenterData newsCenterData;

	public NewsCenterBaseTagPager(MainActivity context) {
		super(context);
	}

	@Override
	public void initData() {
		// 获取网络数据
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, MyContants.NEWSCENTERURL,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// stupid
						String result = responseInfo.result;
						// 解析
						// System.out.println(result);
						// 解析
						parseData(result);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						System.out.println("访问失败：" + error);
					}
				});
		tv_title.setText("News");

		TextView tv = new TextView(mainActivity);
		tv.setText("News Content");
		tv.setTextSize(25);
		tv.setGravity(Gravity.CENTER);

		fl_content.addView(tv);
		super.initData();
	}

	/**
	 * 解析json数据，从网络服务器解析
	 * 
	 * @param result
	 */
	protected void parseData(String result) {
		// Google提供的json解析器
		Gson gson = new Gson();
		newsCenterData = gson.fromJson(result,
				NewsCenterData.class);
		
		System.out.println(newsCenterData.data.get(0).children.get(0).title);
		mainActivity.getLeftMenuFragment().setLeftMenuData(newsCenterData.data);
		mainActivity.getLeftMenuFragment().setOnSwitchListener(new OnSwitchPageListener() {
			
			@Override
			public void switchPage(int selectionIndex) {
				NewsCenterBaseTagPager.this.switchPage(selectionIndex);
			}
		});
		
		// 把读取的数据封装到界面的组件里
		// 根据服务器数据显示数据
		for (NewsCenterData.NewsData newsData : newsCenterData.data) {
			BaseNewsCenterPage newsPage = null;
			//遍历4个新闻中心页面
			switch (newsData.type) {
			case 1://新闻
				newsPage = new NewsBaseNewsCenterPage(mainActivity,newsCenterData.data.get(0).children);
				break;
			case 10://专题
				newsPage = new TopicBaseNewsCenterPage(mainActivity);
				break;
			case 2://组图
				newsPage = new PhotosBaseNewsCenterPage(mainActivity);
				break;
			case 3://互动
				newsPage = new InteractBaseNewsCenterPage(mainActivity);
				break;
			default:
				break;
			}
			//添加新闻中心的界面
			newsCentPages.add(newsPage);
		}
		//控制4个页面的显示
		switchPage(0);
		
		
	}
	/**
	 * 根据位置动态显示不同的新闻中心页面
	 * @param position
	 */
	public void switchPage(int position){
		BaseNewsCenterPage baseNewsCenterPage = newsCentPages.get(position);
		tv_title.setText(newsCenterData.data.get(position).title);
		fl_content.removeAllViews();
		baseNewsCenterPage.initData();
		fl_content.addView(baseNewsCenterPage.getRoot());
	}
	
}
