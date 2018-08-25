package com.polluxframework.maven.plugin.support;

import com.polluxframework.maven.plugin.constant.Constants;
import com.polluxframework.maven.plugin.constant.MethodEnum;
import com.polluxframework.maven.plugin.entity.Config;
import com.polluxframework.maven.plugin.entity.DocLabel;
import com.polluxframework.maven.plugin.entity.FileInfo;
import com.polluxframework.maven.plugin.entity.PageInfo;
import com.polluxframework.maven.plugin.utils.BaseUtils;
import com.polluxframework.maven.plugin.utils.DocUtils;
import com.polluxframework.maven.plugin.utils.StringUtils;
import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhumin0508
 * created in  2018/8/23 15:21
 * modified By:
 */
public abstract class AbstractProcessFactory implements ProcessFactory {
	protected final static int SPLIT_INDEX = 2;

	protected Log logger;
	protected Config config;
	protected Map<String, FileInfo> files;
	protected List<PageInfo> pages;

	public AbstractProcessFactory(Config config) {
		this.config = config;
	}

	protected String getFileVersion(File file, MethodEnum method) {
		try {
			return BaseUtils.getFileHashKey(file, method.getMethod());
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		return System.currentTimeMillis() + "";
	}

	protected int processVersion(StringBuffer html, int index, List<FileInfo> processSuccessFiles, final String fileType) {
		DocLabel docLabel = newDocLabelByType(index, fileType);
		if (docLabel == null) {
			return -1;
		}
		int heardLength = docLabel.getStartSign().length();
		String checkEndLabel = "";

		DocUtils.getDocLabelPosition(html, docLabel);
		if (!docLabel.isHasFind()) {
			if (index < html.length() && docLabel.getSourceSignPos() > 0) {
				return processVersion(html, docLabel.getStartSignPos() + heardLength + 1, processSuccessFiles, fileType);
			} else {
				return -1;
			}
		}
		char[] cas = html.toString().toCharArray();
		char endChar = cas[docLabel.getSourceSignPos()];
		if (endChar != Constants.CHAR_SINGLE_QUOTE_MARK && endChar != Constants.CHAR_DOUBLE_QUOTE_MARK) {
			return -1;
		}
		index = docLabel.getSourceSignPos() - 1;
		docLabel = newDocLabelByType(index, fileType);
		if (docLabel == null) {
			return -1;
		}
		docLabel.setStartSign(endChar + Constants.EMPTY_STR);
		docLabel.setSourceSign(endChar + Constants.EMPTY_STR);
		DocUtils.getDocLabelPosition(html, docLabel);

		if (!docLabel.isHasFind()) {
			return -1;
		}
		int length = docLabel.getSourceSignPos() - docLabel.getStartSignPos() - 2;
		if (length < 0) {
			return -1;
		}
		char[] links = new char[length];
		System.arraycopy(cas, docLabel.getStartSignPos() + 1, links, 0, length);
		String link = new String(links);
		logger.debug("find " + fileType + " link:" + link);
		processLink(html, docLabel.getStartSignPos() - 1, docLabel.getSourceSignPos() - 1, link, fileType, processSuccessFiles);
		return processVersion(html, docLabel.getEndSignPos(), processSuccessFiles, fileType);
	}

	private void processLink(StringBuffer sb, final int start, final int end, final String historyLink, final String fileType, List<FileInfo> processSuccessFiles) {
		FileInfo fileInfo = null;
		StringBuilder fullLink = new StringBuilder();

		List<String> domains = config.getDomains();
		List<String> includes = config.getIncludes();
		if (historyLink.startsWith(Constants.HTTP_START_HEARD) || historyLink.startsWith(Constants.HTTPS_START_HEARD)) {
			for (String domain : domains) {
				fullLink.append(domain);
				String tempUrl = historyLink.replaceFirst(fullLink.toString(), Constants.EMPTY_STR);
				if (tempUrl.startsWith(Constants.STR_SLASH)) {
					tempUrl = tempUrl.replaceFirst(Constants.STR_SLASH, Constants.EMPTY_STR);
				}
				tempUrl = removeUrlPar(tempUrl);
				fileInfo = files.get(tempUrl);
				if (fileInfo != null) {
					break;
				}
			}
		} else {
			fullLink.append(historyLink);
			fullLink = new StringBuilder(removeUrlPar(fullLink.toString()));
			for (String include : includes) {
				int index = fullLink.indexOf(include, 0);
				if (index > 0) {
					fullLink.delete(0, index);
					break;
				}
			}
			if (fullLink.indexOf(Constants.STR_SLASH, 0) == 0) {
				fullLink.delete(0, 1);
			}
			fileInfo = files.get(fullLink.toString());

		}

		if (fileInfo != null && !Constants.EMPTY_STR.equals(fullLink.toString())) {
			insertVersion(sb, start, end, historyLink, fullLink.toString(), fileInfo, processSuccessFiles);
		}
	}

	private void insertVersion(StringBuffer sb, final int start, final int end, final String historyLink, String fullLink, FileInfo fileInfo, List<FileInfo> processSuccessFiles) {
		if (fileInfo != null) {
			logger.debug("process link:" + historyLink+" : "+fullLink);
			String versionStr = "";
			if (!checkIsSkip(fileInfo, config)) {
				versionStr = getVersionStr(fileInfo, config.isInName(), historyLink);
			}
			processSuccessFiles.add(fileInfo);
			if (config.isInName()) {
				int fileNameLength = fileInfo.getFileName().length();
				int parLength = 0;
				String par;
				if (historyLink.indexOf(Constants.STR_QUESTION_MARK) > 0) {
					par = getUrlPar(historyLink);
					parLength = par.length();
					parLength++;
				}
				sb.replace(end - fileNameLength - parLength, end, Constants.EMPTY_STR);
				sb.insert(end - fileNameLength - parLength, versionStr);
			} else {
				sb.insert(end, versionStr);
			}

		}

	}

	private String getVersionStr(FileInfo fileInfo, final boolean isInName, final String historyLink) {
		String param = "";
		if (historyLink.indexOf(Constants.STR_QUESTION_MARK) > 0) {
			param = getUrlPar(historyLink);
		}
		String version = fileInfo.getFileVersion();
		if (config.getVersionLength() >= 1 && version.length() > config.getVersionLength()) {
			version = version.substring(0, config.getVersionLength());
		}
		String versionStr;
		if (isInName) {
			versionStr = version + Constants.STR_DOT + fileInfo.getFileType();
			fileInfo.setFinalFileName(versionStr);
			if (StringUtils.isNotEmpty(param)) {
				versionStr += Constants.STR_QUESTION_MARK + param;
			}
		} else {
			if (StringUtils.isEmpty(param)) {
				versionStr = Constants.STR_QUESTION_MARK + config.getVersionLabel() + "=" + version;
			} else {
				versionStr = Constants.STR_AND_MARK + config.getVersionLabel() + "=" + version;
			}
		}
		return versionStr;
	}


	private boolean checkIsSkip(final FileInfo fileInfo, final Config config) {
		if (fileInfo.getFileName().contains(config.getSkipSuffix() + Constants.STR_DOT + fileInfo.getFileType())) {
			logger.info("The suffix is " + config.getSkipSuffix() + " ,not processed:" + fileInfo.getFileName());
			return true;
		}
		List<String> excludes = config.getExcludes();
		if ((!excludes.isEmpty()) && BaseUtils.checkStrIsInList(excludes, fileInfo.getRelativelyFilePath(), true)) {
			logger.info("The file  is not processed:" + fileInfo.getFileName());
			return true;
		}
		return false;
	}

	private String getUrlPar(String url) {
		if (url.indexOf(Constants.STR_QUESTION_MARK) > 0) {
			String[] split = url.split(Constants.STR_QUESTION_MARK_REGEX);
			if (split.length == SPLIT_INDEX) {
				return split[1];
			} else {
				return Constants.EMPTY_STR;
			}
		}
		return Constants.EMPTY_STR;

	}

	private String removeUrlPar(String url) {
		if (url.indexOf(Constants.STR_QUESTION_MARK) > 0) {
			String[] split = url.split(Constants.STR_QUESTION_MARK_REGEX);
			if (split.length == SPLIT_INDEX) {
				url = split[0];
			}
		}
		return url;
	}

	private DocLabel newDocLabelByType(int index, String fileType) {
		index = index < 0 ? 0 : index;
		if (FileInfo.JS.equals(fileType)) {
			return new DocLabel(index, DocUtils.HTML_JAVASCRIPT_START, DocUtils.HTML_JAVASCRIPT_SRC, DocUtils.HTML_JAVASCRIPT_END);
		} else if (FileInfo.CSS.equals(fileType)) {
			return new DocLabel(index, DocUtils.HTML_CSS_START, DocUtils.HTML_CSS_SRC, DocUtils.HTML_CSS_END);
		} else {
			logger.error("file type error :" + fileType);
			return null;
		}
	}

	public void buildLoggerFactory(Log logger) {
		this.logger = logger;
	}
}
