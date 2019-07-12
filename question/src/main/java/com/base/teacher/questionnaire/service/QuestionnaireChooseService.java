/******************************************************************************
 * Copyright (C) 2018 
 * All Rights Reserved.
 *****************************************************************************/
package com.base.teacher.questionnaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.teacher.config.common.QueryCondition;
import com.base.teacher.config.common.QueryCondition.Criteria;
import com.base.teacher.config.common.base.BaseDao;
import com.base.teacher.config.common.base.BaseService;

import com.base.teacher.questionnaire.entity.QuestionnaireChoose;
import com.base.teacher.questionnaire.dao.QuestionnaireChooseDao;

/** 
 * 类描述： service类
 * @address com.base.teacher.questionnaire.service.QuestionnaireChooseService
 * @author youkehai
 * @email 717407966@qq.com
 * @date 创建时间：2019-7-8 9:17:12
 * @version 1.0  
 */
@Service
@Transactional(readOnly = true)
public class QuestionnaireChooseService extends BaseService<QuestionnaireChoose> {
	
	@Autowired
	private QuestionnaireChooseDao questionnaireChooseDao;
	
	@Override
	protected BaseDao<QuestionnaireChoose> getEntityDao() {
		return questionnaireChooseDao;
	}

	@Transactional(readOnly=false)
	public int insertAll(List<QuestionnaireChoose> questionnaireChooses, String questionId) {
		QueryCondition qc=new QueryCondition();
		Criteria c=qc.createCriteria();
		c.andEqualTo("question_id", questionId);
		questionnaireChooseDao.deleteByCondition(qc);
		int i=questionnaireChooseDao.batchInsert(questionnaireChooses);
		return i;
	}
	
}
