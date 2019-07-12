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

import com.base.teacher.questionnaire.entity.QuestionnaireIp;
import com.base.teacher.questionnaire.dao.QuestionnaireIpDao;

/** 
 * 类描述： service类
 * @address com.base.teacher.questionnaire.service.QuestionnaireIpService
 * @author youkehai
 * @email 717407966@qq.com
 * @date 创建时间：2019-7-8 9:17:07
 * @version 1.0  
 */
@Service
@Transactional(readOnly = true)
public class QuestionnaireIpService extends BaseService<QuestionnaireIp> {
	
	@Autowired
	private QuestionnaireIpDao questionnaireIpDao;
	
	@Override
	protected BaseDao<QuestionnaireIp> getEntityDao() {
		return questionnaireIpDao;
	}
	
}
