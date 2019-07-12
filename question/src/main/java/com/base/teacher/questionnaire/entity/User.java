/******************************************************************************
 * Copyright (C) 2018 
 * All Rights Reserved.
 *****************************************************************************/
package com.base.teacher.questionnaire.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.base.teacher.config.common.base.BaseDataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/** 
 * 类描述： 实体类
 * @address com.base.teacher.questionnaire.entity.User
 * @author youkehai
 * @email 717407966@qq.com
 * @date 创建时间：2019-7-8 12:37:50
 * @version 1.0  
 */
public class User extends BaseDataEntity<String> {

	private static final long serialVersionUID = 1L;
	/**********************************实体存放的其他字段区  不替换的区域 【other_start】******************************************/
	
	
	/**********************************实体存放的其他字段区 【other_end】******************************************/
	
    /*****************************数据库字段区 不包含父类公共字段(属性)***********************************/
    /** 名称 */
    private String name;
    /** 正常(0),停用(1), */
    private String status;
    /** 密码 */
    private String password;
    /** 账号 */
    private String username;
    /*******************************************getset方法区************************************/
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}

