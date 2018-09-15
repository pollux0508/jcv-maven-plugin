package com.polluxframework.maven.plugin;

import com.polluxframework.maven.plugin.entity.Config;
import com.polluxframework.maven.plugin.support.DefaultProcessFactory;
import com.polluxframework.maven.plugin.support.ProcessFactory;
import com.polluxframework.maven.plugin.utils.FileUtils;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * @author: zhumin0508
 * @create 2018-08-23 21:44
 **/

@Mojo( name = "process" , defaultPhase = LifecyclePhase.PROCESS_RESOURCES, threadSafe = true)
public class JcvMojo extends BaseMojo {
	@Parameter( defaultValue = "${basedir}/src/main/webapp", property = "webapp", required = true )
	protected File webapp;

	@Override
	public void execute(){
		String webRoot=webapp.getPath();
		if(!webRoot.endsWith(FileUtils.getSystemLineSeparator())){
			webRoot+=FileUtils.getSystemFileSeparator();
		}
		String outputRoot=outputDir.getPath()+FileUtils.getSystemFileSeparator()+webRootName;

		Config config = new Config();
		config.setVersionLabel(versionLabel);
		config.setExcludes(excludes);
		config.setIncludes(includes);
		config.setDomains(domains);
		config.setSourceEncoding(sourceEncoding);
		config.setMethod(method);
		config.setSuffix(suffix);
		config.setSkipSuffix(skipSuffix);
		config.setOutDirRoot(outputRoot);

		ProcessFactory processFactory=new DefaultProcessFactory(config);
		processFactory.buildLoggerFactory(getLog());
		processFactory.init(webRoot);
		processFactory.execute();
	}
}
