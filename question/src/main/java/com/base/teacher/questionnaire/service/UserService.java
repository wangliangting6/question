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

import com.base.teacher.questionnaire.entity.User;
import com.base.teacher.questionnaire.dao.UserDao;

/** 
 * 类描述： service类
 * @address com.base.teacher.questionnaire.service.UserService
 * @author youkehai
 * @email 717407966@qq.com
 * @date 创建时间：2019-7-8 9:17:10
 * @version 1.0  
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseService<User> {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	protected BaseDao<User> getEntityDao() {
		return userDao;
	}
	
}
