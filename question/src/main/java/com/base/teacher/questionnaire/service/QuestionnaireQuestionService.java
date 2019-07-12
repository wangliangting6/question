/******************************************************************************
 * Copyright (C) 2018 
 * All Rights Reserved.
 *****************************************************************************/
package com.base.teacher.questionnaire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.teacher.config.common.base.BaseDao;
import com.base.teacher.config.common.base.BaseService;

import com.base.teacher.questionnaire.entity.QuestionnaireQuestion;
import com.base.teacher.questionnaire.dao.QuestionnaireQuestionDao;

/** 
 * 类描述： service类
 * @address com.base.teacher.questionnaire.service.QuestionnaireQuestionService
 * @author youkehai
 * @email 717407966@qq.com
 * @date 创建时间：2019-7-8 9:17:09
 * @version 1.0  
 */
@Service
@Transactional(readOnly = true)
public class QuestionnaireQuestionService extends BaseService<QuestionnaireQuestion> {
	
	@Autowired
	private QuestionnaireQuestionDao questionnaireQuestionDao;
	
	@Override
	protected BaseDao<QuestionnaireQuestion> getEntityDao() {
		return questionnaireQuestionDao;
	}
	
}
