package com.polluxframework.maven.plugin.constant;

/**
 * 目前暂时只支持时间戳、文件内容MD5、文件名称MD5三种方式
 *
 * @author zhumin0508
 * created in  2018/8/23 14:38
 * modified By:
 */
public enum MethodEnum {
	/**
	 * 时间戳方式
	 */
	TIMESTAMP_METHOD("timestamp", 1),
	/**
	 * 文件内容MD5方式
	 */
	MD5_METHOD("md5", 2),
	/**
	 * 文件名称MD5方式
	 */
	NAME_MD5_METHOD("filename", 2);

	private String method;

	private int id;

	MethodEnum(String method, int id) {
		this.method = method;
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
