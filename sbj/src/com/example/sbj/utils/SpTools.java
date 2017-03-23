

package com.example.sbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

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

}
