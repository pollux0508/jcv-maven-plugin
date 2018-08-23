package com.polluxframework.maven.plugin.entity;

/**
 * @author zhumin0508
 * created in  2018/8/23 11:05
 * modified By:
 */
public class DocLabel {
	/**
	 * 从indexPos位置开始检查标签
	 */
	private int indexPos;
	/**
	 * 标签开始标识
	 */
	private String startSign;
	/**
	 * 标签开始位置
	 */
	private int startSignPos;
	/**
	 * 标签配置源标识
	 */
	private String sourceSign;
	/**
	 * 标签配置源位置
	 */
	private int sourceSignPos;
	/**
	 * 标签结束标识
	 */
	private String endSign;
	/**
	 * 表姐结束位置
	 */
	private int endSignPos;
	/**
	 * 是否找到此标签
	 */
	private boolean hasFind;

	public DocLabel() {
	}

	public DocLabel(int indexPos, String startSign, String sourceSign, String endSign) {
		this.indexPos = indexPos;
		this.startSign = startSign;
		this.sourceSign = sourceSign;
		this.endSign = endSign;
	}

	public int getIndexPos() {
		return indexPos;
	}

	public void setIndexPos(int indexPos) {
		this.indexPos = indexPos;
	}

	public String getStartSign() {
		return startSign;
	}

	public void setStartSign(String startSign) {
		this.startSign = startSign;
	}

	public int getStartSignPos() {
		return startSignPos;
	}

	public void setStartSignPos(int startSignPos) {
		this.startSignPos = startSignPos;
	}

	public String getSourceSign() {
		return sourceSign;
	}

	public void setSourceSign(String sourceSign) {
		this.sourceSign = sourceSign;
	}

	public int getSourceSignPos() {
		return sourceSignPos;
	}

	public void setSourceSignPos(int sourceSignPos) {
		this.sourceSignPos = sourceSignPos;
	}

	public String getEndSign() {
		return endSign;
	}

	public void setEndSign(String endSign) {
		this.endSign = endSign;
	}

	public int getEndSignPos() {
		return endSignPos;
	}

	public void setEndSignPos(int endSignPos) {
		this.endSignPos = endSignPos;
	}

	public boolean isHasFind() {
		return hasFind;
	}

	public void setHasFind(boolean hasFind) {
		this.hasFind = hasFind;
	}
}
