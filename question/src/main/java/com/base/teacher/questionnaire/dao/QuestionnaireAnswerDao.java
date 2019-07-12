/******************************************************************************
 * Copyright (C) 2018
 * All Rights Reserved.
 
 *****************************************************************************/
package com.base.teacher.questionnaire.dao;

import com.base.teacher.config.common.annotation.MyBatisDao;
import com.base.teacher.config.common.base.BaseDao;
import com.base.teacher.questionnaire.entity.QuestionnaireAnswer;
/** 
 * 类描述： dao类
 * @address com.base.teacher.questionnaire.dao.QuestionnaireAnswerDao
 * @author youkehai  
 * @email  717407966@qq.com
 * @date 创建时间：2019-7-8 9:17:06 
 * @version 1.0  
 */
 @MyBatisDao
public class QuestionnaireAnswerDao extends BaseDao<QuestionnaireAnswer>{

   @Override
	protected String getMapperNamespace() {
		return "QuestionnaireAnswerDao";
	}

}
