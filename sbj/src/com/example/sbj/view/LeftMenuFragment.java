package com.example.sbj.view;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbj.R;
import com.example.sbj.domain.NewsCenterData;
import com.example.sbj.domain.NewsCenterData.NewsData;
import com.example.sbj.utils.DensityUtil;

/**
 * @author shaoyyyy
 * @创建时间 2017-3-23 上午10:22:39
 * @描述 Left菜单的fragment
 * 
 */
public class LeftMenuFragment extends BaseFragment {
	public interface OnSwitchPageListener {
		void switchPage(int selectionIndex);
	}

	private OnSwitchPageListener switchListen;

	/**
	 * 设置监听回调接口
	 * 
	 * @param listener
	 */
	public void setOnSwitchListener(OnSwitchPageListener listener) {
		this.switchListen = listener;
	}

	private List<NewsData> data = new ArrayList<NewsCenterData.NewsData>();// 新闻数据
	private ListView lv_leftData;
	private MyAdapter adapter;

	private int selectPosition; // 选中的按钮

	@Override
	public void initEvent() {
		// 设置lv的选择事件
		lv_leftData.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 保存选中的位置
				selectPosition = position;
				// 跟新界面
				adapter.notifyDataSetChanged();
				// 控制新闻中心页面的显示
				// mainActivity.getMainMenuFragment().leftMenuClickSwitchPage(selectPosition);

				if (switchListen != null) {
					switchListen.switchPage(selectPosition);
				}else{
					 mainActivity.getMainMenuFragment().leftMenuClickSwitchPage(selectPosition);
				}
			}
		});
	}

	@Override
	public View initView() {
		lv_leftData = new ListView(mainActivity);
		lv_leftData.setBackgroundColor(Color.BLACK);
		// 拖动的背景色
		lv_leftData.setCacheColorHint(Color.TRANSPARENT);
		// 设置选种的颜色
		lv_leftData.setSelector(new ColorDrawable(Color.TRANSPARENT));
		// 分割线的高度为0
		lv_leftData.setDividerHeight(0);
		int px = 45;

		// 距离
		lv_leftData.setPadding(0, DensityUtil.dip2px(mainActivity, px), 0, 0);
		return lv_leftData;
	}

	public void setLeftMenuData(List<NewsData> data) {
		this.data = data;
		adapter.notifyDataSetChanged();// 设置好数据通知更新
	}

	@Override
	public void initData() {
		adapter = new MyAdapter();
		lv_leftData.setAdapter(adapter);

	}

	private class MyAdapter extends BaseAdapter {

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
			// 现实数据
			if (convertView == null) {
				tv_currentView = (TextView) View.inflate(mainActivity,
						R.layout.leftmenu_list_menu, null);
			} else {
				tv_currentView = (TextView) convertView;
			}
			// 设置数据
			tv_currentView.setText(data.get(position).title);

			// 判断是否被选中,默认第0条
			tv_currentView.setEnabled(position == selectPosition);
			System.out.println(selectPosition);

			return tv_currentView;
		}

	}
}
