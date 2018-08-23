package com.polluxframework.maven.plugin.utils;


import com.polluxframework.maven.plugin.constant.Constants;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import static com.polluxframework.maven.plugin.utils.BaseUtils.checkNextCharIndex;

/**
 * @author zhumin0508
 * created in  2018/8/21 16:13
 * modified By:
 */
public class FileUtils {
	private FileUtils() {
	}

	/**
	 * 遍历查找目标目录了中指定类型的文件，并保存在集合中
	 *
	 * @param collected 指定类型文件集合
	 * @param file      目标文件或文件夹
	 * @param includes  文件后缀,不包含点
	 */
	public static void collectFiles(List<File> collected, File file, List<String> includes) {
		if (file.isFile()) {
			for (String suffix : includes) {
				if (file.getName().endsWith(Constants.STR_DOT + suffix)) {
					collected.add(file);
					break;
				}
			}
		} else {
			File[] files = file.listFiles();
			if (files != null) {
				for (File sub : files) {
					collectFiles(collected, sub, includes);
				}
			}
		}
	}

	/**
	 * 读取file文件，将文件中的数据按照行读取到String数组中
	 *
	 * @param file           需要读取的文件
	 * @param sourceEncoding 文件编码格式
	 * @return String[] 文件内容字符数组
	 * @throws IOException 文件读取IO异常
	 */
	public static String[] readFileToString(final File file, final String sourceEncoding) throws IOException {
		String[] result;
		Long fileLength = file.length();
		byte[] fileContent = new byte[fileLength.intValue()];
		try (InputStream in = Files.newInputStream(file.toPath())) {
			int count = in.read(fileContent);
			if (count == 0) {
				return new String[0];
			}
			String string = new String(fileContent, Charset.forName(sourceEncoding));
			char[] lineFeedChar = getFileLineFeed(string);
			StringBuilder lineFeed = new StringBuilder();
			if (lineFeedChar.length == 0) {
				result = new String[1];
				result[0] = string;
				return result;
			} else {
				lineFeed.append(lineFeedChar);
			}
			result = string.split(lineFeed.toString());
		}
		return result;
	}

	/**
	 * 获取字符串的换行符
	 *
	 * @param str 字符串
	 * @return char[] 回车换行
	 * /r Mac
	 * /r/n Windows
	 * /n Unix/Linux
	 */
	public static char[] getFileLineFeed(final String str) {
		if (null == str || str.length() == 0) {
			return new char[0];
		}
		char[] charArray = str.toCharArray();
		char[] windowsLineFeed = newWindowsLineFeed();
		char[] macLineFeed = newMacLineFeed();
		char[] linuxLineFeed = newLinuxLineFeed();
		if (checkNextCharIndex(charArray, 0, windowsLineFeed) != -1) {
			return windowsLineFeed;
		} else if (checkNextCharIndex(charArray, 0, linuxLineFeed) != -1) {
			return linuxLineFeed;
		} else if (checkNextCharIndex(charArray, 0, macLineFeed) != -1) {
			return macLineFeed;
		} else {
			return linuxLineFeed;
		}

	}

	private static char[] newMacLineFeed() {
		char[] ret = new char[1];
		ret[0] = Constants.CHAR_ENTER_SIGN;
		return ret;
	}

	private static char[] newWindowsLineFeed() {
		char[] ret = new char[2];
		ret[0] = Constants.CHAR_ENTER_SIGN;
		ret[1] = Constants.CHAR_NEWLINE_SIGN;
		return ret;
	}

	private static char[] newLinuxLineFeed() {
		char[] ret = new char[1];
		ret[0] = Constants.CHAR_NEWLINE_SIGN;
		return ret;
	}

	/**
	 * 读取文件
	 *
	 * @param file           文件
	 * @param sourceEncoding 文件编码
	 * @return 文件内容字符串
	 * @throws IOException 文件读取IO异常
	 */
	public static String readFileToStr(final File file, final String sourceEncoding) throws IOException {
		Long fileLength = file.length();
		byte[] fileContent = new byte[fileLength.intValue()];
		String string;
		try (InputStream in = Files.newInputStream(file.toPath())) {
			int count = in.read(fileContent);
			if (count == 0) {
				return null;
			}
			string = new String(fileContent, Charset.forName(sourceEncoding));
		}
		return string;
	}

	/**
	 * 写文件
	 *
	 * @param file           要写的文件
	 * @param sourceEncoding 文件编码
	 * @param strList        要写的内容
	 * @throws IOException 文件IO异常
	 */
	public static void writeFile(final File file, final String sourceEncoding, final List<String> strList) throws IOException {
		try (OutputStream out = Files.newOutputStream(file.toPath())) {
			for (String str : strList) {
				if (StringUtils.isNotEmpty(str)) {
					out.write(str.getBytes(Charset.forName(sourceEncoding)));
					out.write(getSystemLineSeparator().getBytes(Charset.forName(sourceEncoding)));
				}
			}
		}

	}

	/**
	 * 系统换行符号
	 *
	 * @return 系统换行符号
	 */
	public static String getSystemLineSeparator() {
		return System.getProperty("line.separator");
	}

	/**
	 * 文件复制
	 *
	 * @param source 源文件
	 * @param to     目标文件
	 * @throws IOException 文件IO异常
	 */
	public static void fileChannelCopy(final File source, final File to) throws IOException {
		try (FileChannel in = new FileInputStream(source).getChannel();
			 FileChannel out = new FileOutputStream(to).getChannel()) {
			in.transferTo(0, in.size(), out);
		}
	}

	/**
	 * 获取文件换行符数组
	 *
	 * @param file           文件
	 * @param sourceEncoding 文件编码
	 * @return char[] 文件换行数组
	 * @throws IOException 文件IO异常
	 */
	public static char[] getFileLineFeed(final File file, final String sourceEncoding) throws IOException {
		if (file == null) {
			throw new IOException();
		}
		Long fileLength = file.length();
		byte[] fileContent = new byte[fileLength.intValue()];
		try (InputStream in = Files.newInputStream(file.toPath())) {
			int count = in.read(fileContent);
			if (count == 0) {
				return new char[0];
			}
			String str = new String(fileContent, Charset.forName(sourceEncoding));
			return getFileLineFeed(str);
		}

	}

	/**
	 * 获取系统文件分割符
	 *
	 * @return 系统文件分割符
	 */
	public static String getSystemFileSeparator() {
		return System.getProperty("file.separator");
	}

	/**
	 * 清除多余换行
	 *
	 * @param stringBuilder 字符流
	 */
	public static void clearBlankLines(StringBuilder stringBuilder) {
		if (stringBuilder == null || stringBuilder.length() == 0) {
			return;
		}
		char[] chas = stringBuilder.toString().toCharArray();
		int chasLength = chas.length;
		char[] lineFeedChar = getFileLineFeed(stringBuilder.toString());
		int lineFeedLength = lineFeedChar.length;
		int doubleLineFeedLength = lineFeedLength * 2;
		int deleteCount = 0;
		int index = 0;
		while (chasLength >= doubleLineFeedLength) {
			index = checkNextCharIndex(chas, index, lineFeedChar);
			if (index == -1) {
				return;
			}
			int index2 = checkNextCharIndex(chas, index + lineFeedLength, lineFeedChar);
			if (index2 == -1) {
				return;
			}
			if (index + lineFeedLength == index2) {
				int start = index - deleteCount;
				stringBuilder.delete(start, start + lineFeedLength);
				deleteCount += lineFeedLength;
			}
			index = index2;
		}
	}

	/**
	 * 获取文件的目录地址
	 *
	 * @param path 文件路径
	 * @return 文件目录地址
	 */
	public static String getFilePathDirectory(String path) {
		if (StringUtils.isEmpty(path)) {
			return path;
		}
		int lastIndexOf = path.lastIndexOf(FileUtils.getSystemFileSeparator());
		if (lastIndexOf > -1) {
			path = path.substring(0, lastIndexOf);
		}
		return path;
	}

}
