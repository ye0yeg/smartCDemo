package com.example.sbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author Administrator
 * 不能滑动的VP
 *
 */
public class NoScrollViewPage extends MyViewPager {

	public NoScrollViewPage(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPage(Context context) {
		super(context);
	}
	/* (non-Javadoc)
	 * 不让自己拦截
	 * @see android.support.v4.view.ViewPager#onInterceptTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}
	
}
