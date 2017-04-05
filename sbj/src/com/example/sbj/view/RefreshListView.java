package com.example.sbj.view;

import com.example.sbj.R;

import android.R.array;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * @author shaoyyyy
 * @创建时间 2017-4-5 下午2:34:45
 * @描述 自定义刷新头和尾的ListView , 下拉刷新和尾部加载
 * 
 * 
 */
public class RefreshListView extends ListView {

	private View root;
	private LinearLayout head;
	private LinearLayout ll_refresh_head_root;
	private int ll_refresh_head_root_height;
	private int ll_refresh_root_height;
	private float downY;

	public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);

	}

	public RefreshListView(Context context) {
		this(context, null);
	}

	private void initView() {
		initRoot();
		initHead();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * 覆盖次方法完成事件处理
	 * 
	 * @see android.widget.AbsListView#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// 下拉拖动，（当LV显示第一条数据的时候，并且向下拖动）
		switch (ev.getAction()) {
		// 按下
		case MotionEvent.ACTION_DOWN:
			downY = ev.getY();
			break;
		// 移动
		case MotionEvent.ACTION_MOVE:
			float moveY = ev.getY();
			
			//如果轮播图没有完全显示, 相应的是LV的事件
			
			float dy = moveY- downY;
			//下来拖动
			if(dy >0  && getFirstVisiblePosition() ==0){
				//当前padding top 的参数值
				float scrollYDis = - ll_refresh_head_root_height + dy;
				
				ll_refresh_head_root.setPadding(0, (int)scrollYDis, 0, 0);
				return true;
			}
				
			break;
		// 松开
		case MotionEvent.ACTION_UP:
			ll_refresh_head_root.setPadding(0, -ll_refresh_head_root_height, 0, 0);
			
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 底部
	 */
	private void initRoot() {
		root = View.inflate(getContext(), R.layout.listview_refresh_root, null);

		root.measure(0, 0);
		// 尾部组件的高度
		ll_refresh_root_height = root.getMeasuredHeight();

		root.setPadding(0, -ll_refresh_root_height, 0, 0);
		// 加载到LV中
		addFooterView(root);
	}

	/**
	 * @param view
	 *            LunBo View
	 */
	public void addLunBoView(View view) {
		head.addView(view);
	}

	/**
	 * 头部
	 */
	private void initHead() {
		head = (LinearLayout) View.inflate(getContext(),
				R.layout.listview_head_content, null);
		ll_refresh_head_root = (LinearLayout) head
				.findViewById(R.id.ll_listview_head_root);
		ll_refresh_head_root.measure(0, 0);

		ll_refresh_head_root_height = ll_refresh_head_root.getMeasuredHeight();
		head.setPadding(0, -ll_refresh_head_root_height, 0, 0);

		addHeaderView(head);
	}

}
