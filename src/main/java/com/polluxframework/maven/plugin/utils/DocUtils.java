package com.polluxframework.maven.plugin.utils;

import com.polluxframework.maven.plugin.entity.DocLabel;
import org.codehaus.plexus.util.StringUtils;

/**
 * @author zhumin0508
 * created in  2018/8/23 11:17
 * modified By:
 */
public class DocUtils {
	private DocUtils() {
	}

	/**
	 * 获取从指定字符串流中指定文本标签的位置信息
	 *
	 * @param stringBuffer 字符串流
	 * @param docLabel     文本标签
	 */
	public static void getDocLabelPosition(final StringBuffer stringBuffer, DocLabel docLabel) {
		if (stringBuffer == null || stringBuffer.length() == 0) {
			return;
		}
		int startLength = docLabel.getStartSign().length();
		int sourceLength = docLabel.getSourceSign().length();
		char[] cas = stringBuffer.toString().toCharArray();
		int index = BaseUtils.checkNextStrIndex(cas, docLabel.getIndexPos(), docLabel.getStartSign());
		if (index == -1) {
			docLabel.setHasFind(false);
			return;
		}
		docLabel.setStartSignPos(index);
		index = BaseUtils.checkNextStrIndex(cas, index + startLength, docLabel.getSourceSign());
		if (index == -1) {
			docLabel.setHasFind(false);
			return;
		}
		docLabel.setSourceSignPos(index + sourceLength);
		if (StringUtils.isNotEmpty(docLabel.getEndSign())) {
			index = BaseUtils.checkNextStrIndex(cas, docLabel.getSourceSignPos(), docLabel.getEndSign());
			if (index == -1) {
				docLabel.setHasFind(false);
				return;
			}
			docLabel.setEndSignPos(index);
		}
		docLabel.setHasFind(true);
	}


	/**
	 * JS开始标识
	 */
	public static final String HTML_JAVASCRIPT_START = "<script";
	/**
	 * JS引用标识
	 */
	public static final String HTML_JAVASCRIPT_SRC = "src=";
	/**
	 * JS结束标识
	 */
	public static final String HTML_JAVASCRIPT_END = ">";

	/**
	 * css开始标识
	 */
	public static final String HTML_CSS_START = "<link";
	/**
	 * css引用标识
	 */
	public static final String HTML_CSS_SRC = "href=";
	/**
	 * css结束标识
	 */
	public static final String HTML_CSS_END = ">";

}
