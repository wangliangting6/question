/******************************************************************************
 * Copyright (C) 2018 
 * All Rights Reserved.
 *****************************************************************************/
package com.base.teacher.questionnaire.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import com.base.teacher.config.common.base.BaseDataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/** 
 * 类描述： 实体类
 * @address com.base.teacher.questionnaire.entity.QuestionnaireQuestion
 * @author youkehai
 * @email 717407966@qq.com
 * @date 创建时间：2019-7-8 9:17:09
 * @version 1.0  
 */
public class QuestionnaireQuestion extends BaseDataEntity<String> {

	private static final long serialVersionUID = 1L;
	/**********************************实体存放的其他字段区  不替换的区域 【other_start】******************************************/
	/**问卷选项List*/
	private List<QuestionnaireChoose> chooseList;
	/**问题答案List*/
	private List<QuestionnaireAnswer> answerList;
	
	
   public List<QuestionnaireAnswer> getAnswerList() {
		return answerList;
	}
	public void setAnswerList(List<QuestionnaireAnswer> answerList) {
		this.answerList = answerList;
	}
	public List<QuestionnaireChoose> getChooseList() {
		return chooseList;
	}
	public void setChooseList(List<QuestionnaireChoose> chooseList) {
		this.chooseList = chooseList;
	}
	/**********************************实体存放的其他字段区 【other_end】******************************************/
	
 
	/*****************************数据库字段区 不包含父类公共字段(属性)***********************************/
    /** 问卷ID */
	@NotNull
    private String questionnaireId;
    /** 题目类型 */
	@NotNull
    private String type;
    /** 题目内容 */
    private String tips;
    /** 提示文字 */
    private String placeholder;
    /*******************************************getset方法区************************************/
    public String getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
    public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
}

