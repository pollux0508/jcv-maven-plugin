package com.polluxframework.maven.plugin.support;

import com.polluxframework.maven.plugin.constant.MethodEnum;
import com.polluxframework.maven.plugin.entity.Config;
import com.polluxframework.maven.plugin.support.logger.SystemLogger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DefaultProcessFactoryTest {
	@Test
	public void init() {
		Config config = new Config();
		config.setMethod(MethodEnum.MD5_METHOD);
		List<String> suffix = new ArrayList<>();
		suffix.add("html");
		config.setSuffix(suffix);
		config.setSourceEncoding("UTF-8");
		config.setOutDirRoot("E:\\Work\\pollux-resource\\target\\pollux-resource-1.0-SNAPSHOT");
		List<String> includes = new ArrayList<>();
		includes.add("/static/pollux");
		includes.add("/static/modules");
		config.setSkipSuffix(".min");
		config.setIncludes(includes);
		config.setExcludes(new ArrayList<String>());
		config.setVersionLabel("version");
		DefaultProcessFactory defaultProcessFactory = new DefaultProcessFactory(config);
		defaultProcessFactory.buildLoggerFactory(new SystemLogger());

		defaultProcessFactory.init("E:\\Work\\pollux-resource\\src\\main\\webapp");
		defaultProcessFactory.execute();
	}
}