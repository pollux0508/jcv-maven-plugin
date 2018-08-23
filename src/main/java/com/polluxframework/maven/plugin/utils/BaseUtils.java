package com.polluxframework.maven.plugin.utils;

import com.polluxframework.maven.plugin.constant.Constants;
import com.polluxframework.maven.plugin.constant.MethodEnum;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/8/21 17:07
 * modified By:
 */
public class BaseUtils {
	private BaseUtils() {

	}

	/**
	 * 判断target数组是否在source数组中，从startIndex位置开始
	 *
	 * @param source     被检索的字符数组
	 * @param startIndex 起始位置
	 * @param target     检索的字符数组
	 * @return boolean 是否相等
	 */
	public static boolean compareCharArray(final char[] source, final int startIndex, final char[] target) {
		int sourceLength = source.length;
		int targetLength = target.length;
		if (sourceLength < targetLength + startIndex) {
			return false;
		}
		for (int i = 0; i < targetLength; i++) {
			if (source[i + startIndex] != target[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查下一个字符位置
	 *
	 * @param source 被检索的字符数组
	 * @param index  检索的开始位置
	 * @param target 检索的字符
	 * @return 检索的在被检索字符数组的下一个字符位置 不在则返回-1
	 */
	public static int checkNextCharIndex(final char[] source, final int index, final char target) {
		char[] sub = new char[1];
		sub[0] = target;
		return checkNextCharIndex(source, index, sub);
	}

	/**
	 * 检查下一个字符数组位置(由于考虑大数组情况下内存消耗问题，未采用递归模式)
	 *
	 * @param source     被检索的字符数组
	 * @param startIndex 检索的开始位置
	 * @param target     检索的字符数组
	 * @return 检索的数组在被检索字符数组的下一个字符组位置 不在则返回-1
	 */
	public static int checkNextCharIndex(final char[] source, final int startIndex, final char[] target) {
		int sourceLength = source.length;
		int targetLength = target.length;
		if (sourceLength < targetLength + startIndex) {
			return -1;
		}
		for (int i = startIndex; i < sourceLength; i++) {
			for (int j = 0; j < targetLength && i + j < sourceLength; j++) {
				if (source[i + j] != target[j]) {
					break;
				} else if (j == targetLength - 1) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * 检查下一个字符串位置
	 *
	 * @param source     被检索的字符数组
	 * @param startIndex 检索的开始位置
	 * @param target     检索的字符串
	 * @return 检索的字符串在被检索字符数组的下一个字符串位置 不在则返回-1
	 */
	public static int checkNextStrIndex(final char[] source, final int startIndex, final String target) {
		if (StringUtils.isEmpty(target) || startIndex < 0) {
			return -1;
		}
		char[] sub = target.toCharArray();
		return checkNextCharIndex(source, startIndex, sub);
	}

	/**
	 * 检查上一个字符的位置
	 *
	 * @param source   被检索的字符数组
	 * @param endIndex 检索的结束位置
	 * @param str      检索的字符串
	 * @return 检索的字符串在被检索字符数组的上一个字符的位置 不在则返回-1
	 */
	public static int checkUpStrIndex(final char[] source, final int endIndex, final String str) {
		if (StringUtils.isEmpty(str) || endIndex <= 0) {
			return -1;
		}
		char[] target = str.toCharArray();

		int sourceLength = source.length;
		int targetLength = target.length;

		if (sourceLength - endIndex < targetLength) {
			return -1;
		}
		for (int i = endIndex - 1; i >= 0; i--) {
			for (int j = 0; j < targetLength && i + j < sourceLength; j++) {
				if (source[i + j] != target[j]) {
					break;
				} else if (j == targetLength - 1) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * 检查checkStr是否在list中
	 *
	 * @param list     字符串数组
	 * @param checkStr 要检查的字符串
	 * @param isLike   是否模糊匹配
	 * @return 返回checkStr是否在list中
	 */
	public static boolean checkStrIsInList(final List<String> list, final String checkStr, final boolean isLike) {
		if (list == null || list.isEmpty()) {
			return false;
		}
		if (StringUtils.isEmpty(checkStr)) {
			return false;
		}
		if (isLike) {
			for (String bean : list) {
				if (StringUtils.isNotEmpty(bean) && bean.contains(checkStr)) {
					return true;
				}
			}
		} else {
			return list.contains(checkStr);
		}
		return false;
	}

	/**
	 * 替换字符串中的所有单个字符
	 *
	 * @param str         需要替换的字符串
	 * @param source      需要替换的字符
	 * @param replacement 替换后的字符
	 * @return 返回替换后的字符串
	 */
	public static String replaceAll(final String str, final char source, final char replacement) {
		if (str == null || str.length() == 0) {
			return str;
		}
		char[] sub = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char bean : sub) {
			sb.append(bean == source ? replacement : bean);
		}
		return sb.toString();
	}


	/**
	 * 转换为当前系统路径
	 *
	 * @param path 系统路径
	 * @return 将path的路径修改为当前系统路径
	 */
	public static String replaceCurrentSystemLine(final String path) {
		if (getSystemFileSeparatorIsLinux()) {
			//linux
			return replaceAll(path, Constants.CHAR_SLANT_LINE, Constants.CHAR_SLASH);
		} else {
			//windows
			return replaceAll(path, Constants.CHAR_SLASH, Constants.CHAR_SLANT_LINE);
		}
	}

	public static String replaceLinuxSystemLine(final String path) {
		if (getSystemFileSeparatorIsLinux()) {
			return path;
		} else {
			return StringUtils.isEmpty(path) ? path : replaceAll(path, Constants.CHAR_SLANT_LINE, Constants.CHAR_SLASH);
		}

	}

	/**
	 * 获取路径是否linux
	 *
	 * @return 是否linux
	 */
	public static boolean getSystemFileSeparatorIsLinux() {
		String property = System.getProperty("file.separator");
		return Constants.STR_SLASH.equals(property);

	}

	/**
	 * @param file   文件信息
	 * @param method 方法(仅支持文件MD5：FILE_MD5 和文件名MD5：FILE_NAME_MD5)
	 * @return 返回文件根据method方法不同而的HashKey
	 * @throws IOException 文件异常或method方法错误
	 */
	public static String getFileHashKey(final File file, final String method) throws IOException {
		if (MethodEnum.MD5_METHOD.getMethod().equals(method)) {
			return DigestUtils.md5Hex(Files.newInputStream(file.toPath()));
		} else if (MethodEnum.NAME_MD5_METHOD.getMethod().equals(method)) {
			return DigestUtils.md5Hex(file.getName());
		} else {
			throw new IOException("error method");
		}
	}
}
