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
 * @address com.base.teacher.questionnaire.entity.QuestionnaireChoose
 * @author youkehai
 * @email 717407966@qq.com
 * @date 创建时间：2019-7-8 9:17:12
 * @version 1.0  
 */
public class QuestionnaireChoose extends BaseDataEntity<String> {

	private static final long serialVersionUID = 1L;
	/**********************************实体存放的其他字段区  不替换的区域 【other_start】******************************************/
	
	
	/**********************************实体存放的其他字段区 【other_end】******************************************/
	
    /*****************************数据库字段区 不包含父类公共字段(属性)***********************************/
    /** 问卷ID */
	@NotNull
    private String questionnaireId;
    /** 题目ID */
	@NotNull
    private String questionId;
    /** 问题选项内容 */
    private String answer;
    /** 提示文字 */
    private String placeholder;
    /** 选项（A，b,c） */
    private String choose;
    /*******************************************getset方法区************************************/
    public String getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
    public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
    public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
    public String getPlaceholder() {
		return placeholder;
	}
	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}
    public String getChoose() {
		return choose;
	}
	public void setChoose(String choose) {
		this.choose = choose;
	}
}