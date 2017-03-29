package com.example.sbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Administrator
 * @创建时间 2015-7-7 上午11:20:13
 * @描述 父空间不拦截的ViewPager 自己来处理touch事件
 * 
 *     @ svn提交者：$Author: gd $ @ 提交时间: $Date: 2015-07-07 11:34:25 +0800 (Tue, 07
 *     Jul 2015) $ @ 当前版本: $Rev: 41 $
 */
public class InterceptScrollViewPager extends ViewPager {

	private float downX;
	private float downY;

	public InterceptScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public InterceptScrollViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// true 申请父控件不拦截我的touch事件,false 默认父类先拦截事件
		getParent().requestDisallowInterceptTouchEvent(true);
		// 事件完全由自己处理
		// 如果在第一个页面，并且是从左往右滑动,让父控件拦截我
		// 如果在最后一个页面,并且是从右往左滑动，父控件拦截
		// 否则都不让父类拦截
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = ev.getX();
			downY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			float moveX = ev.getX();
			float moveY = ev.getY();

			float dx = moveX - downX;
			float dy = moveY - downY;
			if (Math.abs(dx) > Math.abs(dy)) {
				if (getCurrentItem() == 0 && dx > 0) {
					getParent().requestDisallowInterceptTouchEvent(false);
				} else if (getCurrentItem() == getAdapter().getCount() - 1 && dx < 0) {
					getParent().requestDisallowInterceptTouchEvent(false);
				} else {
					getParent().requestDisallowInterceptTouchEvent(true);
				}
			} else {
				getParent().requestDisallowInterceptTouchEvent(true);
			}
			break;

		default:
			break;
		}

		return super.dispatchTouchEvent(ev);
	}

}
