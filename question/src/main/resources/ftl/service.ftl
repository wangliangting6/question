/******************************************************************************
 * Copyright (C) 2018 
 * All Rights Reserved.
 *****************************************************************************/
package ${javapackage}.${project}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.teacher.config.common.base.BaseDao;
import com.base.teacher.config.common.base.BaseService;

import ${javapackage}.${project}.entity.${className};
import ${javapackage}.${project}.dao.${className}Dao;

/** 
 * 类描述：${title} service类
 * @address ${javapackage}.${project}.service.${className}Service
 * @author ${author}
 * @email ${email}
 * @date 创建时间：${.now}
 * @version 1.0  
 */
@Service
@Transactional(readOnly = true)
public class ${className}Service extends BaseService<${className}> {
	
	@Autowired
	private ${className}Dao ${smallClassName}Dao;
	
	@Override
	protected BaseDao<${className}> getEntityDao() {
		return ${smallClassName}Dao;
	}
	
}
