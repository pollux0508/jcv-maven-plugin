package com.polluxframework.maven.plugin.entity;

import java.io.File;

/**
 * @author zhumin0508
 * created in  2018/8/23 15:28
 * modified By:
 */
public class FileInfo {
	public static final String CSS = "css";
	public static final String JS = "js";

	/**
	 * 文件类型
	 **/
	private String fileType;
	/**
	 * 文件名称
	 **/
	private String fileName;
	/**
	 * 输出最终文件名
	 **/
	private String finalFileName;

	/**
	 * 文件对象
	 **/
	private File file;

	/**
	 * 文件版本号
	 **/
	private String fileVersion;

	/**
	 * 文件hash值
	 **/
	private String fileHashKey;

	/**
	 * 相对文件路径  去除了文件webRoot 和全部前缀路径
	 **/
	private String relativelyFilePath;

	/**
	 * 文件是否被复制
	 **/
	private boolean hasCopy = false;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFinalFileName() {
		return finalFileName;
	}

	public void setFinalFileName(String finalFileName) {
		this.finalFileName = finalFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}

	public String getFileHashKey() {
		return fileHashKey;
	}

	public void setFileHashKey(String fileHashKey) {
		this.fileHashKey = fileHashKey;
	}

	public String getRelativelyFilePath() {
		return relativelyFilePath;
	}

	public void setRelativelyFilePath(String relativelyFilePath) {
		this.relativelyFilePath = relativelyFilePath;
	}

	public boolean isHasCopy() {
		return hasCopy;
	}

	public void setHasCopy(boolean hasCopy) {
		this.hasCopy = hasCopy;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("FileInfo{");
		sb.append("fileType='").append(fileType).append('\'');
		sb.append(", fileName='").append(fileName).append('\'');
		sb.append(", finalFileName='").append(finalFileName).append('\'');
		sb.append(", file=").append(file);
		sb.append(", fileVersion='").append(fileVersion).append('\'');
		sb.append(", fileHashKey='").append(fileHashKey).append('\'');
		sb.append(", relativelyFilePath='").append(relativelyFilePath).append('\'');
		sb.append(", hasCopy=").append(hasCopy);
		sb.append('}');
		return sb.toString();
	}
}
