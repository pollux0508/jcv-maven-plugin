package com.polluxframework.maven.plugin.utils;

/**
 * @author zhumin0508
 * created in  2018/8/23 15:02
 * modified By:
 */
public class StringUtils {

	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static boolean isNotEmpty(final CharSequence cs) {
		return !isEmpty(cs);
	}
}
