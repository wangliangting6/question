package com.base.teacher.config.message;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 国际化 语言类
 * 
 * @author xiong
 *
 */
@Component
public class LocaleMessages {

	@Autowired
	private MessageSource messageSource;

	/**
	 * 返回国际化信息
	 * @param code 键
	 * @return
	 */
	public String getMessage(String code) {
		return getMessage(code, null);
	}

	/**
	 * 
	 * @param code  键
	 * @param args 数组参数
	 * @return
	 */
	public String getMessage(String code, Object[] args) {
		return getMessage(code, args, code);
	}

	/**
	 * 
	 * @param code  键
	 * @param args 数组参数
	 * @param defaultMessage  没有设置key的时候的默认值.
	 * @return
	 */
	public String getMessage(String code, Object[] args, String defaultMessage) {
		Locale locale = LocaleContextHolder.getLocale();// 这里使用比较方便的方法，不依赖request.
		return messageSource.getMessage(code, args, defaultMessage, locale);
	}
}
