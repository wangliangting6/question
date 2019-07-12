/******************************************************************************
 * Copyright (C) 2018
 * All Rights Reserved.
 
 *****************************************************************************/
package ${javapackage}.${project}.dao;

import com.base.teacher.config.common.annotation.MyBatisDao;
import com.base.teacher.config.common.base.BaseDao;
import ${javapackage}.${project}.entity.${className};
/** 
 * 类描述：${title} dao类
 * @address ${javapackage}.${project}.dao.${className}Dao
 * @author ${author}  
 * @email  ${email}
 * @date 创建时间：${.now} 
 * @version 1.0  
 */
 @MyBatisDao
public class ${className}Dao extends BaseDao<${className}>{

   @Override
	protected String getMapperNamespace() {
		return "${className}Dao";
	}

}
