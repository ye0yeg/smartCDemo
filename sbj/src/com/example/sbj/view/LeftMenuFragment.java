package com.example.sbj.view;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sbj.R;
import com.example.sbj.domain.NewsCenterData;
import com.example.sbj.domain.NewsCenterData.NewsData;

/**
 * @author shaoyyyy
 * @创建时间 2017-3-23 上午10:22:39	
 * @描述 Left菜单的fragment
 * 
 */
public class LeftMenuFragment extends BaseFragment {

	private List<NewsData> data = new ArrayList<NewsCenterData.NewsData>();//新闻数据
	private ListView lv_leftData;
	private MyAdapter adapter;
	
	@Override
	public View initView() {
		lv_leftData = new ListView(mainActivity);
		
		
		return lv_leftData;
	}
	
	public void setLeftMenuData(List<NewsData> data){
		this.data = data;
		adapter.notifyDataSetChanged();//设置好数据通知更新
	}
	
	@Override
	public void initData() {
		adapter = new MyAdapter();
		lv_leftData.setAdapter(adapter);
		
	}
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView tv_currentView;
			//现实数据
			if(convertView == null){
				tv_currentView = (TextView) View.inflate(mainActivity, R.layout.leftmenu_list_menu, null);
			}else
			{
				tv_currentView = (TextView) convertView;
			}
			//设置数据
			tv_currentView.setText(data.get(position).title);
			
			return tv_currentView;
		}
		
	}
}
