package com.polluxframework.maven.plugin.support;

import org.apache.maven.plugin.logging.Log;

/**
 * @author zhumin0508
 * created in  2018/8/23 15:09
 * modified By:
 */
public interface ProcessFactory {
	/**
	 * 初始化配置信息
	 *
	 * @param webapp 应用根目录
	 */
	void init(final String webapp);

	/**
	 * 执行具体信息
	 */
	void execute();

	/**
	 * 执行成功后的处理
	 */
	void success();

	/**
	 * 创建日志
	 * @param logger 日志处理器
	 */
	void buildLoggerFactory(Log logger);
}
