package com.example.sbj.basepage;

import android.view.Gravity;
import android.widget.TextView;

import com.example.sbj.activity.MainActivity;
import com.example.sbj.domain.NewsCenterData;
import com.example.sbj.utils.MyContants;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class NewsCenterBaseTagPager extends BaseTagPage {

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

						String result = responseInfo.result;
						// 解析
//						System.out.println(result);
						//解析
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
	 * @param result
	 */
	protected void parseData(String result) {
		//Google提供的json解析器
		Gson gson = new Gson();
		NewsCenterData newsCenterData = gson.fromJson(result, NewsCenterData.class);
		System.out.println(newsCenterData.data.get(0).children.get(0).title);
		mainActivity.getLeftMenuFragment().setLeftMenuData(newsCenterData.data);
		
	}
}
