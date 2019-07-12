package com.base.teacher.config.exception;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.base.teacher.config.common.ApiResult;
import com.base.teacher.config.enums.BaseErrorEnum;
import com.base.teacher.config.message.LocaleMessages;


@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
    
    @Autowired
	private LocaleMessages localeMessages;
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ApiResult handleMissingServletRequestPartExceptionHandler(MissingServletRequestPartException e) {
        logger.error("请求缺少部分(检查文件名称、内容)", e);
        return error(BaseErrorEnum.ERR_MISSING_SERVLET_REQUEST_PART_EXCEPTION.getErrorMessage(),e.getMessage());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("缺少请求参数(检查参数名称、内容)", e);
        return error(BaseErrorEnum.ERR_MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION.getErrorMessage(),e.getMessage());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败(检查参数内容格式)", e);
        return error(BaseErrorEnum.ERR_HTTP_MESSAGE_NOT_READABLE_EXCEPTION.getErrorMessage(),e.getMessage());
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        logger.error("参数验证失败(@Valid对应实体类验证异常)", e);
        return error(BaseErrorEnum.ERR_METHOD_ARGUMENT_NOT_VALID_EXCEPTION.getErrorMessage(),message);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult handleServiceException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        logger.error("参数验证失败(@Validated验证实体的异常)", e);
        return error(BaseErrorEnum.ERR_CONSTRAINT_VIOLATIONEXCEPTION.getErrorMessage(),message);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ApiResult handleValidationException(ValidationException e) {
    	 logger.error("参数验证失败(javax基础验证异常)", e);
         return error(BaseErrorEnum.ERR_VALIDATION_EXCEPTION.getErrorMessage(),e.getMessage());
    }
 
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiResult handleBindException(BindException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        logger.error("参数绑定失败(检查参数名称)", e);
        return error(BaseErrorEnum.ERR_BIND_EXCEPTION.getErrorMessage(),message);
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResult noHandlerFoundException(NoHandlerFoundException e) {
    	 logger.error(" API地址不存在(检查请求地址)", e);
         return error(BaseErrorEnum.ERR_NO_HANDLER_FOUND_EXCEPTION.getErrorMessage());
    }
    
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    	 logger.error("不支持当前请求方法(检查请求方法)", e);
         return error(BaseErrorEnum.ERR_HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION.getErrorMessage());
    }
    
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ApiResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        logger.error("不支持当前媒体类型(检查下请求头contentType)", e);
        return error(BaseErrorEnum.ERR_HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION.getErrorMessage());
    }
    
    
    @ExceptionHandler(ClientException.class)
    public ApiResult handleClientException(ClientException e) {
        logger.error("远程服务器返回异常", e);
        if(e.getMessage().substring(0, 5) != null && isNumeric(e.getMessage().substring(0, 5))) {
			return ApiResult.error(e.getMessage().substring(5), Integer.valueOf(e.getMessage().substring(0, 5)));
		}
        return error(BaseErrorEnum.ERR_INTERNAL_SERVER_ERROR.getErrorMessage());
    }
    public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
    
    
    @ExceptionHandler(UndeclaredThrowableException.class)
    public ApiResult handleUndeclaredThrowableException(UndeclaredThrowableException e) {
        logger.error("未声明的抛出异常", e);
        return error(BaseErrorEnum.ERR_INTERNAL_SERVER_ERROR.getErrorMessage());
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ApiResult handleClientException(RuntimeException e) {
        logger.error("运行时抛出异常", e);
        return error(BaseErrorEnum.ERR_INTERNAL_SERVER_ERROR.getErrorMessage());
    }
    
    
   /**
    * 其他未知未定义异常
    * @param e
    * @return
    */
   @ExceptionHandler(value = Exception.class)
   private <T> ApiResult defaultErrorHandler(Exception e) {
	   logger.error("\n ---------> 未定义异常!", e);
	  
       return error(BaseErrorEnum.ERR_INTERNAL_SERVER_ERROR.getErrorMessage());
   }
   
   /**
    * 国际化
    * @param baseErrorEnumMessage
    * @return
    */
   private ApiResult error(String baseErrorEnumMessage) {
	   BaseErrorEnum bre = BaseErrorEnum.valueOf(baseErrorEnumMessage);
	   return ApiResult.error(localeMessages.getMessage(bre.getErrorMessage()),bre.getErrorCode());
   }
   /**
    * 国际化
    * @param baseErrorEnumMessage 错误提示
    * @param object  具体错误信息
    * @return
    */
   private ApiResult error(String baseErrorEnumMessage,Object object) {
	   BaseErrorEnum bre = BaseErrorEnum.valueOf(baseErrorEnumMessage);
	   return ApiResult.error(localeMessages.getMessage(bre.getErrorMessage()),bre.getErrorCode(),object);
   }
    
}
