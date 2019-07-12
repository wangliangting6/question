/******************************************************************************
 * Copyright (C) 2018 
 * All Rights Reserved.
 *****************************************************************************/
package ${javapackage}.${project}.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.base.teacher.config.common.base.BaseDataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/** 
 * 类描述：${title} 实体类
 * @address ${javapackage}.${project}.entity.${className}
 * @author ${author}
 * @email ${email}
 * @date 创建时间：${.now}
 * @version 1.0  
 */
public class ${className} extends BaseDataEntity<String> {

	private static final long serialVersionUID = 1L;
	/**********************************实体存放的其他字段区  不替换的区域 【other_start】******************************************/
	
	
	/**********************************实体存放的其他字段区 【other_end】******************************************/
	
    /*****************************数据库字段区 不包含父类公共字段(属性)***********************************/
    <#list columns as propertyName>
    <#if propertyName != 'id'& propertyName != 'companyCode'
    & propertyName != 'createId'& propertyName != 'createName'& propertyName != 'createDate'
    & propertyName != 'updateId'& propertyName != 'updateName'& propertyName != 'updateDate'
    & propertyName != 'delFlag'& propertyName != 'remarks'>
    /** ${remarks[propertyName_index]} */
    private ${columnTypes[propertyName_index]} ${propertyName};
    </#if>
    </#list>
    /*******************************************getset方法区************************************/
    <#list columns as propertyName>
    <#if propertyName != 'id'& propertyName != 'companyCode'
    & propertyName != 'createId'& propertyName != 'createName'& propertyName != 'createDate'
    & propertyName != 'updateId'& propertyName != 'updateName'& propertyName != 'updateDate'
    & propertyName != 'delFlag'& propertyName != 'remarks'>
    <#if columnTypes[propertyName_index] == 'Date'>
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    </#if>
    public ${columnTypes[propertyName_index]} get${propertyName?cap_first}() {
		return ${propertyName};
	}
	public void set${propertyName?cap_first}(${columnTypes[propertyName_index]} ${propertyName}) {
		this.${propertyName} = ${propertyName};
	}
	</#if>
    </#list>
}