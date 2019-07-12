/******************************************************************************
 * Copyright (C) 2018 celizi.com 
 * All Rights Reserved.
 * 本软件为网址：celizi.com 开发研制。未经本站正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.base.teacher.config.common.base;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.base.teacher.config.common.IdGen;
import com.fasterxml.jackson.annotation.JsonFormat;


/** 
 * 类描述：
 * @address com.celizi.base.common.base.DataEntity
 * @author shenzhixiong  
 * @email 731139982@qq.com
 * @date 创建时间：2017年4月8日 上午9:27:06 
 * @version 1.0  
 */
public abstract class BaseDataEntity<T> extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4105801886976217482L;
	protected String createId;	// 创建者
	protected String createName;//创建者
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	protected Date createDate;	// 创建日期
	protected String updateId;	// 更新者
	protected String updateName;	// 更新者
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	protected Date updateDate;	// 更新日期
	@Length(min=0, max=255)
	protected String remarks;	// 备注
	@Length(min=1, max=1)
	protected String delFlag; 	// 删除标记（0：正常；1：删除；）
	
	public BaseDataEntity() {
		super();
	}
	
	public BaseDataEntity(String id) {
		super(id);
	}
	

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 新增 实体前手动调用
	 * 设置ID为UUID
	 *  
	 */
	@Override
	public void preInsert() {
		preInsert(IdGen.getId().toString());
	}
	
	
	/**
	 * 新增  主键为传入主键
	 * @param id 主键ID
	 */
	public void preInsert(String id) {
		setId(id);//设置主键
		this.createDate = new Date();
	}
	/**
	 * 设置修改时间
	 */
	@Override
	public void preUpdate() {
		this.updateDate = new Date();
	}

}
