package com.base.teacher.config.exception;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.base.teacher.config.common.ApiResult;
import com.base.teacher.config.enums.BaseErrorEnum;
import com.base.teacher.config.message.LocaleMessages;
import com.base.teacher.config.utils.StringUtils;



@RestController
public class FinalExceptionHandler implements ErrorController {

Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LocaleMessages localeMessages;
	
	@Autowired
	private ErrorAttributes errorAttributes;

	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	/**
	 * 主要拦截所有错误请求返回页面    变成Json
	 * 改变service异常的拦截
	 * @param resp
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/error")
	public ApiResult error(HttpServletResponse resp, HttpServletRequest request) throws IOException {
		Map<String, Object> errorAttributes = getErrorAttributes(request, false);
		Integer status = (Integer) errorAttributes.get("status");
		
		String path = (String) errorAttributes.get("path");
		String messageFound = (String) errorAttributes.get("message");
		log.info(status +"------------" + path+"------------" + messageFound);
		if(status==401) {//缺少头部权限信息
			
			messageFound = BaseErrorEnum.ERR_LACK_HEAD_PARAMS_AUTHORIZATION.getErrorMessage();
			return ApiResult.error(localeMessages.getMessage(messageFound), BaseErrorEnum.valueOf(messageFound).getErrorCode());
		}
		if(status==500 && StringUtils.isNoneBlank(messageFound)&& !messageFound.equals(localeMessages.getMessage(messageFound))) {
			//500 同时错误信息存在于国际化中，即已登记的错误
			return ApiResult.error(localeMessages.getMessage(messageFound), BaseErrorEnum.valueOf(messageFound).getErrorCode());
		}
		// 错误处理逻辑
		return ApiResult.error(messageFound, status, path);
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		  ServletWebRequest servletWebRequest = new ServletWebRequest(request);
		    return this.errorAttributes.getErrorAttributes(servletWebRequest,
		            includeStackTrace);
	}
}
