/******************************************************************************
 * Copyright (C) 2018 celizi.com 
 * All Rights Reserved.
 * 本软件为网址：celizi.com 开发研制。未经本站正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.base.teacher.config.generator;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.base.teacher.config.generator.entity.Params;
import com.base.teacher.config.generator.entity.Tables;
import com.base.teacher.config.generator.utils.UtilFile;
import com.base.teacher.config.generator.utils.UtilFreemarker;
import com.base.teacher.config.generator.utils.UtilString;
import com.base.teacher.config.generator.utils.UtilXML;
import com.base.teacher.config.utils.StringUtils;

/** 
 * 类描述：代码生成 总控制枢纽
 * 需要先设置config.xml对应 数据库表 和 项目的模块名称   
 * @version 1.0  
 */
public class Gen {
	/** 配置文件名称 */
	public static final String CONFIGXMLNAME = "config.xml";
	
	public static void main(String[] args) {
		//配置文件路径
		String mavenDir =  (new File(Gen.class.getResource("").getPath()).toString()+"\\").replaceAll("\\\\", "\\\\\\\\").replaceAll("target", "src").replaceAll("classes", "main\\\\\\\\java");
		// 多返回了包名
		String configFilePath = mavenDir+CONFIGXMLNAME;
		// 1.创建目录
		UtilXML.parseXml(configFilePath);
		Params params = UtilXML.params;
		UtilFile.initDirName(params);
		// 2.生成文件
		List<Tables> tables = UtilXML.tableList;
		for (Tables table : tables) {
			//去掉前缀（T_  或者  sys_ 等   T_User变成User  sys_user 变成User  T_Bill_Log 变成 BillLog）
			String javaClassName = UtilString.capitalize(UtilString.dbNameToVarName(table.getTableName().substring(table.getTableName().indexOf("_"))));
			String javaPath = params.getOsdir() + File.separatorChar + params.getProject() + File.separatorChar;
			Map<Object, Object> map = UtilFreemarker.getTableInfo(table.getTableName());
			map.put("tableName", table.getTableName());
			map.put("author", params.getAuthor());
			map.put("email", params.getEmail());
			map.put("project", params.getProject());
			map.put("className", javaClassName);
			map.put("smallClassName", toLowerCaseFirstOne(javaClassName));
			map.put("voClassName", javaClassName);
			map.put("javapackage", params.getJavapackage());
			
			if(StringUtils.isBlank(table.getGenType())||table.getGenType().indexOf(Tables.GENXML)!=-1) {//不存在  或者  包含1
				// 1.xml
				String xmlName =  params.getXmlosdir()+ File.separatorChar +"mappings"+ File.separatorChar +params.getProject()+ File.separatorChar + javaClassName + "Dao.xml";
				System.out.println("输出文件："+map.get("title")+"的mapper.xml件");
				UtilFreemarker.generateFile(xmlName, "ftl/xml.ftl", map);
			}
			if(StringUtils.isBlank(table.getGenType())||table.getGenType().indexOf(Tables.GENXML)!=-1) {//不存在  或者  包含1
				//2.entity
				String entityName = javaPath + "entity" + File.separatorChar + javaClassName + ".java";
				System.out.println("输出文件："+map.get("title")+"的实体类");
				UtilFreemarker.generateFile(entityName, "ftl/entity.ftl", map);
			}
			if(StringUtils.isBlank(table.getGenType())||table.getGenType().indexOf(Tables.GENDAO)!=-1) {//不存在  或者  包含1
				//3.dao
				String daoName = javaPath +  "dao" + File.separatorChar + javaClassName + "Dao.java";
				System.out.println("输出文件："+map.get("title")+"的dao类");
				UtilFreemarker.generateFile(daoName,  "ftl/dao.ftl", map);
			}
			if(StringUtils.isBlank(table.getGenType())||table.getGenType().indexOf(Tables.GENSERVICE)!=-1) {//不存在  或者  包含1
				//4.service
				String serviceName = javaPath +  "service" + File.separatorChar + javaClassName + "Service.java";
				System.out.println("输出文件："+map.get("title")+"的service类");
				UtilFreemarker.generateFile(serviceName,  "ftl/service.ftl", map);
			}
			if(StringUtils.isBlank(table.getGenType())||table.getGenType().indexOf(Tables.GENCONTROLLER)!=-1) {//不存在  或者  包含1
				//5.controller
				String controllerName = javaPath + "web" + File.separatorChar + javaClassName + "Controller.java";
				System.out.println("输出文件："+map.get("title")+"的控制器类");
				UtilFreemarker.generateFile(controllerName,  "ftl/controller.ftl", map);
			}
		}
	}
	
	
	/**
	 * 首字母 小写
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s){
		if(Character.isLowerCase(s.charAt(0))){
		    return s;
		} else {
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}
}
