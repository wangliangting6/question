package com.base.teacher.config.common.base;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;

import com.base.teacher.config.common.ApiResult;
import com.base.teacher.config.enums.BaseErrorEnum;
import com.base.teacher.config.utils.DateUtils;
import com.base.teacher.config.utils.StringUtils;

@CrossOrigin(maxAge = 3600)
public class BaseController {

	
	
	public static final String PAGE_NUM = "pageNum";
	public static final String PAGE_SIZE = "pageSize";
	
	
/****************************************国际化方法satrt************************************************/
	/**
	 * 获取国际化信息
	 * @param messageCcode  国际化编码 key
	 * @return
	 */
	protected String getMessage(String messageCcode) {
		String msg = "";
		if (StringUtils.isNotBlank(msg)) {
			return msg;
		}
		return messageCcode;
	}

	/**
	 * 获取国际化信息
	 * @param messageCcode 国际化编码key
	 * @param args 默认值 取不到则返回本数据
	 * @return
	 */
	protected String getMessage(String messageCcode, Object[] args) {
		String msg = "";
		if (StringUtils.isNotBlank(msg)) {
			return msg;
		}
		return messageCcode;
	}
/****************************************国际化方法end  统一返回值  ************************************************/	
	
	/****************************************查询  ************************************************/
	/**查询成功:单个String，实体，分页对象*/
	protected ApiResult selectSuccess(Object object) {
		return success(BaseErrorEnum.SUCCESS_SELECT.getErrorMessage(),object);
	}
	/**查询成功：多个实体，list等 */
	protected ApiResult selectSuccess(Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_SELECT.getErrorMessage(),attributes);
	}
	/**查询成功：单个主实体对象，附加的多个list */
	protected ApiResult selectSuccess(Object object,Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_SELECT.getErrorMessage(),object,attributes);
	}
	/**查询失败：属性不满足要求(不存在，不符合，不在之内等) */
	protected ApiResult selectAttributeNotRequirements(Object object) {
		return error(BaseErrorEnum.ERR_SELECT_ATTRIBUTE_NOT_REQUIREMENTS.getErrorMessage(),object);
	}
	/** 查询失败：找不到数据*/
	protected ApiResult selectNotFound(Object object) {
		return error(BaseErrorEnum.ERR_SELECT_NOT_FOUND.getErrorMessage(),object);
	}
	/** 查询失败：找不到数据*/
	protected ApiResult selectNotFound() {
		return error(BaseErrorEnum.ERR_SELECT_NOT_FOUND.getErrorMessage());
	}
	
	/****************************************新增  ************************************************/
	/**新增成功:单个String，实体，分页对象*/
	protected ApiResult insertSuccess(Object object) {
		return success(BaseErrorEnum.SUCCESS_INSERT.getErrorMessage(),object);
	}
	/**新增成功：多个实体，list等 */
	protected ApiResult insertSuccess(Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_INSERT.getErrorMessage(),attributes);
	}
	/**新增成功：单个主实体对象，附加的多个list */
	protected ApiResult insertSuccess(Object object,Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_INSERT.getErrorMessage(),object,attributes);
	}
	
	/** 新增失败：属性不满足要求(不存在，不符合，不在之内等) */
	protected ApiResult insertAttributeNotRequirements(Object object) {
		return error(BaseErrorEnum.ERR_INSERT_ATTRIBUTE_NOT_REQUIREMENTS.getErrorMessage(),object);
	}
	/** 新增失败：数据已存在*/
	protected ApiResult insertDataRepeat(Object object) {
		return error(BaseErrorEnum.ERR_INSERT_DATA_REPEAT.getErrorMessage(),object);
	}
	/** 新增失败：数据已存在*/
	protected ApiResult insertDataRepeat() {
		return error(BaseErrorEnum.ERR_INSERT_DATA_REPEAT.getErrorMessage());
	}
	
	/****************************************权限不足 ************************************************/
	/****************************************修改 ************************************************/
	/**修改成功:单个String，实体，分页对象*/
	protected ApiResult updateSuccess(Object object) {
		return success(BaseErrorEnum.SUCCESS_UPDATE.getErrorMessage(),object);
	}
	/**修改成功：多个实体，list等 */
	protected ApiResult updateSuccess(Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_UPDATE.getErrorMessage(),attributes);
	}
	/**修改成功：单个主实体对象，附加的多个list */
	protected ApiResult updateSuccess(Object object,Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_UPDATE.getErrorMessage(),object,attributes);
	}
	
	/**修改失败：属性不满足要求(不存在，不符合，不在之内等) */
	protected ApiResult updateAttributeNotRequirements(Object object) {
		return error(BaseErrorEnum.ERR_UPDATE_ATTRIBUTE_NOT_REQUIREMENTS.getErrorMessage(),object);
	}
	/**修改失败：找不到数据*/
	protected ApiResult updateNotFound(Object object) {
		return error(BaseErrorEnum.ERR_UPDATE_DATA_NOT_FOUND.getErrorMessage(),object);
	}
	/**修改失败：找不到数据*/
	protected ApiResult updateNotFound() {
		return error(BaseErrorEnum.ERR_UPDATE_DATA_NOT_FOUND.getErrorMessage());
	}
	
	/****************************************删除************************************************/
	/**删除成功:单个String，实体，分页对象*/
	protected ApiResult deleteSuccess(Object object) {
		return success(BaseErrorEnum.SUCCESS_DELETE.getErrorMessage(),object);
	}
	/**删除成功：多个实体，list等 */
	protected ApiResult deleteSuccess(Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_DELETE.getErrorMessage(),attributes);
	}
	/**删除成功：单个主实体对象，附加的多个list */
	protected ApiResult deleteSuccess(Object object,Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_DELETE.getErrorMessage(),object,attributes);
	}
	/** 删除失败：属性不满足要求(不存在，不符合，不在之内等) */
	protected ApiResult deleteAttributeNotRequirements(Object object) {
		return error(BaseErrorEnum.ERR_DELETE_ATTRIBUTE_NOT_REQUIREMENTS.getErrorMessage(),object);
	}
	/** 删除失败：找不到数据*/
	protected ApiResult deleteNotFound(Object object) {
		return error(BaseErrorEnum.ERR_DELETE_DATA_NOT_FOUND.getErrorMessage(),object);
	}
	/** 删除失败：找不到数据*/
	protected ApiResult deleteNotFound() {
		return error(BaseErrorEnum.ERR_DELETE_DATA_NOT_FOUND.getErrorMessage());
	}
	
	/****************************************公用************************************************/
	
	protected ApiResult success(String baseErrorEnumMessage,Object object) {
		return ApiResult.success(getMessage(baseErrorEnumMessage),object);
	}
	protected ApiResult success(String baseErrorEnumMessage,Map<String, Object> attributes) {
		return ApiResult.success(getMessage(baseErrorEnumMessage),attributes);
	}
	protected ApiResult success(String baseErrorEnumMessage,Object object,Map<String, Object> attributes) {
		return ApiResult.success(getMessage(baseErrorEnumMessage),object,attributes);
	}
	
	protected ApiResult error(String baseErrorEnumMessage,Object object) {
		return ApiResult.error(getMessage(baseErrorEnumMessage),BaseErrorEnum.valueOf(baseErrorEnumMessage).getErrorCode(),object);
	}
	protected ApiResult error(String baseErrorEnumMessage) {
		return ApiResult.error(getMessage(baseErrorEnumMessage),BaseErrorEnum.valueOf(baseErrorEnumMessage).getErrorCode(),null);
	}

	/**
	 * 初始化数据绑定 1. 将所有传递进来的String进行HTML编码，防止XSS攻击 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? DateUtils.formatDateTime((Date) value) : "";
			}
		});
	}
}
