package com.polluxframework.maven.plugin.support.logger;

import org.apache.maven.plugin.logging.Log;

/**
 * @author: zhumin0508
 * @create 2018-08-23 20:45
 **/
public class SystemLogger implements Log {
	@Override
	public boolean isDebugEnabled() {
		return true;
	}

	@Override
	public void debug(CharSequence charSequence) {
		System.out.println(charSequence);
	}

	@Override
	public void debug(CharSequence charSequence, Throwable throwable) {
		System.out.println(charSequence);
		System.out.println(throwable.getMessage());
	}

	@Override
	public void debug(Throwable throwable) {
		System.out.println(throwable.getMessage());
	}

	@Override
	public boolean isInfoEnabled() {
		return true;
	}

	@Override
	public void info(CharSequence charSequence) {
		System.out.println(charSequence);
	}

	@Override
	public void info(CharSequence charSequence, Throwable throwable) {
		System.out.println(charSequence);
		System.out.println(throwable.getMessage());
	}

	@Override
	public void info(Throwable throwable) {
		System.out.println(throwable.getMessage());
	}

	@Override
	public boolean isWarnEnabled() {
		return true;
	}

	@Override
	public void warn(CharSequence charSequence) {
		System.out.println(charSequence);
	}

	@Override
	public void warn(CharSequence charSequence, Throwable throwable) {
		System.out.println(charSequence);
		System.out.println(throwable.getMessage());
	}

	@Override
	public void warn(Throwable throwable) {
		System.out.println(throwable.getMessage());
	}

	@Override
	public boolean isErrorEnabled() {
		return true;
	}

	@Override
	public void error(CharSequence charSequence) {
		System.out.println(charSequence);
	}

	@Override
	public void error(CharSequence charSequence, Throwable throwable) {
		System.out.println(charSequence);
		System.out.println(throwable.getMessage());
	}

	@Override
	public void error(Throwable throwable) {
		System.out.println(throwable.getMessage());
	}
}
