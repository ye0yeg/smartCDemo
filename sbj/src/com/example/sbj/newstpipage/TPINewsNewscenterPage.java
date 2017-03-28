package com.example.sbj.newstpipage;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sbj.R;
import com.example.sbj.activity.MainActivity;
import com.example.sbj.domain.NewsCenterData.NewsData.ViewTagData;
import com.example.sbj.utils.MyContants;
import com.example.sbj.utils.SpTools;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author shaoyyyy
 * @TODO NewsCenter 页签对应的页面s
 * 
 */
public class TPINewsNewscenterPage {
	//注入组件
	@ViewInject(R.id.vp_tpi_news_lunbo_pic)
	private ViewPager vp_lunbo;//轮播图的显示组件
	@ViewInject(R.id.tv_tpi_news_pic_desc)
	private TextView tv_pic_desc;
	@ViewInject(R.id.ll_tpi_news_pic_points)
	private LinearLayout ll_points;//图片对应的POINT
	@ViewInject(R.id.lv_tpi_news_pic_desc)
	private ListView lv_listnews;
	
	
	private MainActivity mainActivity;
	private View root;
	private ViewTagData viewTagData;

	public TPINewsNewscenterPage(MainActivity mainActivity, ViewTagData viewTagData) {
		this.mainActivity = mainActivity;
		this.viewTagData = viewTagData;
		initView();// 在构造函数中完成初始化
		initData();// 初始化数据
		initEvent();//初始化事件
	}

	private void initEvent() {
	}

	private void initData() {
		//从本地缓存获取数据
		
		//网络获取数据
		getDateFromNet();
		
	}

	private void getDateFromNet() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, MyContants.SERVICEURL+viewTagData.url,new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				System.out.println("TPINewsNewscenterPage 成功");
				String jsonData = responseInfo.result;
				SpTools.setString(mainActivity, viewTagData.url, jsonData);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("TPINewsNewscenterPage 失败");
			}
		});
	}

	private void initView() {
		root = View.inflate(mainActivity, R.layout.tpi_news_content, null);
		ViewUtils.inject(this, root); 
	}
	
	public View getRootView(){
		return root;
	}

}
