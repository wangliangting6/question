package com.base.teacher.config.common.base;

import com.base.teacher.config.common.base.BaseGlobal;

public class BaseGlobal {
	/**
	 * 当前对象实例
	 */
	private static BaseGlobal global = new BaseGlobal();
	
	
	/** 默认主键字段为ID*/
	public static final String ID = "ID";
	/** 非法主键值 -1 */
	public static final String ID_INVALID = "-1";
	
	
	/**逗号 */
	public static final String D = ",";
	/**点 */
	public static final String DI = ".";
	/**斜杠 */
	public static final String G = "/";
	
	/** 删除标记0：正常*/
	public static final String DEL_FLAG_NORMAL = "0";
	/** 排序标记0：正常*/
	public static final String SORT_FLAG_NORMAL = "0";
	
	/** 删除标记 1：删除；）*/
	public static final String DEL_FLAG_DELETE = "1";
	
	/** 删除标记0：正常*/
	public static final String DEL_FLAG_NORMAL_CN = "启用";
	
	/** 删除标记 1：删除；）*/
	public static final String DEL_FLAG_DELETE_CN = "停用";
	
	
	/** IE */
	public static final String BROWSER_IE = "IE";
	/** 火狐 */
	public static final String BROWSER_FIREFOX = "Firefox";
	/** 欧派 */
	public static final String BROWSER_OPERA = "Opera";
	/** 其他浏览器 */
	public static final String BROWSER_ELSE = "Else";
	
	
	
	/** 显示/隐藏  */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/** 是/否 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/*是否子节点*/
	public static final String ISLEAF="0";
	public static final String NOTLEAF="1";
	
	/**
	 * 获取当前对象实例
	 */
	public static BaseGlobal getInstance() {
		return global;
	}
}
