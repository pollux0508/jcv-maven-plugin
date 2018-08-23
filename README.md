#jcv-maven-plugin

#### 项目介绍
一个用于用于修改在指定文件中的特定内容添加版本号的maven插件

#### 软件架构
软件架构说明


#### 安装教程

1.下载1.0.0版本后使用mvn install命令将插件安装到本地maven仓库

#### 使用说明

			<plugin>
				<groupId>com.polluxframework</groupId>
				<artifactId>jcv-maven-plugin</artifactId>
				<executions>
					<execution>
						<id>process</id>
						<phase>process-resources</phase>
						<goals>
							<goal>process</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
					<suffix>
						<param>html</param>
					</suffix>
					<excludes>
						<exclude>/static/3rd/</exclude>
					</excludes>
					<includes>
						<include>/static/pollux</include>
						<include>/static/modules</include>
					</includes>
				</configuration>
			</plugin>
    配置suffix 页面后缀
    配置excludes排除不需要追加版本号的CSS 和JS
    需要includes追加版本号的CSS和JS
#### 参与贡献
1、主要思想来源于byzy的jcv-maven-plugin插件
2、修复插件的部分工具类可能存在的问题
3、重构大部分代码，显得更加精炼


