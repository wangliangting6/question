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
 * @address com.base.teacher.questionnaire.entity.QuestionnaireAnswer
 * @author youkehai
 * @email 717407966@qq.com
 * @date 创建时间：2019-7-9 10:00:35
 * @version 1.0  
 */
public class QuestionnaireAnswer extends BaseDataEntity<String> {

	private static final long serialVersionUID = 1L;
	/**********************************实体存放的其他字段区  不替换的区域 【other_start】******************************************/
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	/**********************************实体存放的其他字段区 【other_end】******************************************/
	
    /*****************************数据库字段区 不包含父类公共字段(属性)***********************************/
    /** 问卷ID */
    private String questionnaireId;
    /** 题目ID */
    private String questioId;
    /** 答案内容 */
    private String answer;
    /** 题目类型 */
    private String type;
    /*******************************************getset方法区************************************/
    public String getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
    public String getQuestioId() {
		return questioId;
	}
	public void setQuestioId(String questioId) {
		this.questioId = questioId;
	}
    public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

