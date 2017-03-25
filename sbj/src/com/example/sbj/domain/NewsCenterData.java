package com.example.sbj.domain;

import java.util.List;

/**
 * @author Administrator
 * @Todo 新闻数据的封装
 */
public class NewsCenterData {
	public int retcode;
	
	public List<NewsData> data; //news数据
	
	public class NewsData{
		public List<ViewTagData> children;
		
		/**
		 * @author Administrator
		 * 新闻中心的页签的数据
		 *
		 */
		public class ViewTagData{
			public String id;
			public String title;
			public int type;
			public String url;
		}
		
		public String id;
		public String title;
		public int type;
		
		public String url;
		public String url1;
		
		public String dayurl;
		public String excurl;
		public String weekurl;
	}
	
	public List<String> extend; 
}
