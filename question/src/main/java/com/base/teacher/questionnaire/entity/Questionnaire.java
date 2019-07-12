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
 * @address com.base.teacher.questionnaire.entity.Questionnaire
 * @author youkehai
 * @email 717407966@qq.com
 * @date 创建时间：2019-7-8 13:21:41
 * @version 1.0  
 */
public class Questionnaire extends BaseDataEntity<String> {

	private static final long serialVersionUID = 1L;
	/**********************************实体存放的其他字段区  不替换的区域 【other_start】******************************************/
	/**问卷题目List*/
	private List<QuestionnaireQuestion> questionList;
	
   public List<QuestionnaireQuestion> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<QuestionnaireQuestion> questionList) {
		this.questionList = questionList;
	}
	
	/**********************************实体存放的其他字段区 【other_end】******************************************/
	
    /*****************************数据库字段区 不包含父类公共字段(属性)***********************************/
    /** 问卷创建者ID */
    private String userId;
    /** 问卷被答数 */
    private String num;
    /** 问卷调查背景 */
    private String tips;
    /** 问卷标题 */
    private String title;
    /*******************************************getset方法区************************************/
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
    public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}

