/******************************************************************************
 * Copyright (C) 2018 
 * All Rights Reserved.
 *****************************************************************************/
package com.base.teacher.questionnaire.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.base.teacher.config.common.base.BaseDao;
import com.base.teacher.config.common.base.BaseService;

import com.base.teacher.questionnaire.entity.QuestionnaireAnswer;
import com.base.teacher.questionnaire.entity.QuestionnaireIp;
import com.base.teacher.questionnaire.dao.QuestionnaireAnswerDao;
import com.base.teacher.questionnaire.dao.QuestionnaireIpDao;

/** 
 * 类描述： service类
 * @address com.base.teacher.questionnaire.service.QuestionnaireAnswerService
 * @author youkehai
 * @email 717407966@qq.com
 * @date 创建时间：2019-7-8 9:17:06
 * @version 1.0  
 */
@Service
@Transactional(readOnly = true)
public class QuestionnaireAnswerService extends BaseService<QuestionnaireAnswer> {
	
	@Autowired
	private QuestionnaireAnswerDao questionnaireAnswerDao;
	@Autowired
	private QuestionnaireIpDao questionnaireIpDao;
	
	@Override
	protected BaseDao<QuestionnaireAnswer> getEntityDao() {
		return questionnaireAnswerDao;
	}

	@Transactional(readOnly=false)
	public int insertAll(List<QuestionnaireAnswer> questionnaireAnswers, QuestionnaireIp qi) {
//		Map<String, StringBuffer> resultMap = new HashMap<>();
//		for (QuestionnaireAnswer questionnaireAnswer : questionnaireAnswers) {
//			 if (resultMap.containsKey(questionnaireAnswer.getQuestioId())) {
//	                //map中存在此id，将数据存放当前key的map中
//	                resultMap.get(questionnaireAnswer.getQuestioId()).append(",").append(questionnaireAnswer.getAnswer());
//	            } else {
//	                //map中不存在，新建key，用来存放数据
//	            	StringBuffer sb=new StringBuffer();
//	            	sb.append(questionnaireAnswer.getAnswer());
//	                resultMap.put(questionnaireAnswer.getQuestioId(), sb);
//	            }
//		}
		
		questionnaireAnswerDao.batchInsert(questionnaireAnswers);
		int i=questionnaireIpDao.insert(qi);
		return i;
	}
	
}
