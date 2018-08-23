package com.polluxframework.maven.plugin.entity;

import com.polluxframework.maven.plugin.constant.MethodEnum;

import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/8/23 15:21
 * modified By:
 */
public class Config {
	/**
	 * 需要替换内容的后缀
	 */
	private List<String> suffix;

	/**
	 * 域名
	 * JS和CSS采用域名配置时的连接，非必填项
	 */
	private List<String> domains;

	/**
	 * js 使用方法
	 */
	private MethodEnum method;

	/**
	 * 版本号标签
	 **/
	private String versionLabel;

	/**
	 * 文件编码
	 **/
	private String sourceEncoding;

	/**
	 * 跳过文件名后缀(后缀之前的名称)
	 **/
	private String skipSuffix;

	/**
	 * 排除文件(暂时只支持根路径开始的，不支持正则)
	 **/
	private List<String> excludes;

	/**
	 * 包含文件(暂时只支持根路径开始的，不支持正则)
	 */
	private List<String> includes;

	/**
	 * 根目录名称
	 **/
	private String webRootName;
	/**
	 * 输出文件目录
	 */
	private String outDirRoot;

	private int versionLength;

	private boolean inName;

	public List<String> getSuffix() {
		return suffix;
	}

	public void setSuffix(List<String> suffix) {
		this.suffix = suffix;
	}

	public List<String> getDomains() {
		return domains;
	}

	public void setDomains(List<String> domains) {
		this.domains = domains;
	}

	public MethodEnum getMethod() {
		return method;
	}

	public void setMethod(MethodEnum method) {
		this.method = method;
	}

	public String getVersionLabel() {
		return versionLabel;
	}

	public void setVersionLabel(String versionLabel) {
		this.versionLabel = versionLabel;
	}

	public String getSourceEncoding() {
		return sourceEncoding;
	}

	public void setSourceEncoding(String sourceEncoding) {
		this.sourceEncoding = sourceEncoding;
	}

	public String getSkipSuffix() {
		return skipSuffix;
	}

	public void setSkipSuffix(String skipSuffix) {
		this.skipSuffix = skipSuffix;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public String getWebRootName() {
		return webRootName;
	}

	public void setWebRootName(String webRootName) {
		this.webRootName = webRootName;
	}

	public String getOutDirRoot() {
		return outDirRoot;
	}

	public void setOutDirRoot(String outDirRoot) {
		this.outDirRoot = outDirRoot;
	}

	public int getVersionLength() {
		return versionLength;
	}

	public void setVersionLength(int versionLength) {
		this.versionLength = versionLength;
	}

	public boolean isInName() {
		return inName;
	}

	public void setInName(boolean inName) {
		this.inName = inName;
	}
}
