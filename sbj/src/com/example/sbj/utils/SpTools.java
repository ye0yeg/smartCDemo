

package com.example.sbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author shaoyyyy
 * @创建时间 2017-3-28 下午2:07:41	
 * @描述 TODO
 * 
 */
public class SpTools {
	/**
	 * @param key
	 *            关键字
	 * @param vlaue
	 *            值
	 */
	public static void setBoolean(Context context, String key, boolean value) {
		SharedPreferences sp = context.getSharedPreferences(
				MyContants.CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putBoolean(key, value).commit(); // 提交保存的键值对
	}

	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		SharedPreferences sp = context.getSharedPreferences(
				MyContants.CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getBoolean(key, defValue);
	}
	
	/**
	 * 传入context, key , value
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void setString(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(
				MyContants.CONFIGFILE, Context.MODE_PRIVATE);
		sp.edit().putString(key, value).commit(); // 提交保存的键值对
	}
	
	/**
	 * 传出 context, key , 
	 * @param contextdefValue
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(Context context, String key,
			String defValue) {
		SharedPreferences sp = context.getSharedPreferences(
				MyContants.CONFIGFILE, Context.MODE_PRIVATE);
		return sp.getString(key, defValue);
	}

}
