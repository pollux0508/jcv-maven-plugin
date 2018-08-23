package com.polluxframework.maven.plugin;

import com.polluxframework.maven.plugin.constant.MethodEnum;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.List;

/**
 * @author zhumin0508
 * created in  2018/8/23 14:29
 * modified By:
 */
public abstract class BaseMojo extends AbstractMojo {

	/**
	 * 需要替换内容的后缀
	 */
	@Parameter(defaultValue = "html")
	protected List<String> suffix;

	/**
	 * 域名
	 * JS和CSS采用域名配置时的连接，非必填项
	 */
	@Parameter(defaultValue = "http://www.pollux.com")
	protected List<String> domains;

	/**
	 * js 使用方法
	 */
	@Parameter(defaultValue = "MD5_METHOD")
	protected MethodEnum method;

	/**
	 * 版本号标签
	 **/
	@Parameter(defaultValue = "version")
	protected String versionLabel;

	/**
	 * 文件编码
	 **/
	@Parameter(defaultValue = "UTF-8")
	protected String sourceEncoding;

	/**
	 * 跳过文件名后缀(后缀之前的名称)
	 **/
	@Parameter(defaultValue = ".min")
	protected String skipSuffix;

	/**
	 * 排除文件(暂时只支持根路径开始的，不支持正则)
	 **/
	@Parameter
	protected List<String> excludes;

	/**
	 * 包含文件(暂时只支持根路径开始的，不支持正则)
	 */
	@Parameter
	protected List<String> includes;

	/**
	 * 输出文件目录
	 */
	@Parameter(defaultValue = "${project.build.directory}", property = "outputDir", required = true)
	protected File outputDir;

	/**
	 * 根目录名称
	 **/
	@Parameter(defaultValue = "${project.build.finalName}")
	protected String webRootName;

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

	public File getOutputDir() {
		return outputDir;
	}

	public void setOutputDir(File outputDir) {
		this.outputDir = outputDir;
	}

	public String getWebRootName() {
		return webRootName;
	}

	public void setWebRootName(String webRootName) {
		this.webRootName = webRootName;
	}
}
