package com.polluxframework.maven.plugin.entity;

import java.io.File;

/**
 * @author zhumin0508
 * created in  2018/8/23 16:06
 * modified By:
 */
public class PageInfo {
	private File file;
	/**
	 * 输出文件位置
	 */
	private File  outFile;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getOutFile() {
		return outFile;
	}

	public void setOutFile(File outFile) {
		this.outFile = outFile;
	}
}
