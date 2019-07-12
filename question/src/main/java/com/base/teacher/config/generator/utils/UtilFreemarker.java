package com.base.teacher.config.generator.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.base.teacher.config.utils.StringUtils;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/** 
 * 类描述：
 * @address com.celizi.base.common.generator.utils.UtilFreemarker
 * @date 创建时间：2017年6月14日 下午1:57:37 
 * @version 1.0  
 */
public class UtilFreemarker {
	/**
	 * 获取表中列并转换成java属性
	 * @param tableName 表名
	 * @return list
	 */
	public static List<String> getColumnName(String tableName) {
		List<String> retCol = new LinkedList<String>();
		List<String> columns = UtilDB.getColumnNameByTableNameWithList(tableName);
		for(String column : columns){
			retCol.add(UtilString.dbNameToVarName(column));
		}
		return retCol;
	}
	/**
	 * 获取表中列
	 * @param tableName 表名
	 * @return list
	 */
	public static List<String> getColumnNameForMybatis(String tableName) {
		List<String> retCol = new LinkedList<String>();
		List<String> columns = UtilDB.getColumnNameByTableNameWithList(tableName);
		for(String column : columns){
			retCol.add(column);
		}
		return retCol;
	}
	/**
	 * 得到表中列字段长度最长的列字段
	 * @param tableName 表名
	 * @return 最长的列字段
	 */
	public static int getMaxColumnLength(String tableName){
		int maxLen = 0;
		List<String> columns = getColumnNameForMybatis(tableName);
		for(String column : columns){
			if(column.length() > maxLen){
				maxLen = column.length();
			}
		}
		return maxLen;
	}
	/**
	 * 得到Java属性字段长度最长的字段长度
	 * @param tableName 表名
	 * @return Java属性最长字段长度
	 */
	public static int getMaxJavaPropertyLength(String tableName){
		int maxLen = 0;
		List<String> property = getColumnName(tableName);
		for(String column : property){
			if(column.length() > maxLen){
				maxLen = column.length();
			}
		}
		return maxLen;
	}
	/**
	 * 获取表中列注释
	 * @param tableName 表名
	 * @return list
	 */
	public static List<String> getRemarks(String tableName) {
		List<String> remarks = new ArrayList<String>();
		remarks = UtilDB.getColumnRemarksByTableNameWithList(tableName);
		return remarks;
	}
	
	/**
	 * 获取表中列类型并转换成java类型
	 * @param tableName 表名
	 * @return list
	 */
	public static List<String> getColumnType(String tableName) {
		List<String> retTypes = new ArrayList<String>();
		List<String> types = UtilDB.getColumnTyBypeTableNameWithList(tableName);
		for(String type : types){
			retTypes.add(UtilString.dbTypeToJavaType(type));
		}
		return retTypes;
	}
	/**
	 * 获取mybatis类型
	 * @param tableName 表名
	 * @return list
	 */
	public static List<String> getMybatisType(String tableName) {
		List<String> retTypes = new ArrayList<String>();
		List<String> types = UtilDB.getColumnTyBypeTableNameWithList(tableName);
		for (String type : types) {
			retTypes.add(UtilString.mybatisType(type));
		}
		return retTypes;
	}
	/**
	 * 获取title
	 * @param tableName 表名
	 * @return
	 */
	public static String getTitle(String tableName) {
		return UtilDB.getTableRemarksByTableName(tableName);
	}
	/**
	 * 获取dataModel
	 * @param tableName 表名
	 * @return Map
	 */
	public static Map<Object, Object> getTableInfo(String tableName){
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("title",          getTitle(tableName));
		map.put("columns",        getColumnName(tableName));
		map.put("mybatisColumns", getColumnNameForMybatis(tableName));
		map.put("remarks",        getRemarks(tableName));
		map.put("columnTypes",    getColumnType(tableName));
		map.put("mybatisTypes",   getMybatisType(tableName));
		map.put("maxColumnLength", getMaxColumnLength(tableName));
		map.put("maxJavaPropertyLength", getMaxJavaPropertyLength(tableName));
		return map;
	}
	@SuppressWarnings("deprecation")
	public static void generateFile(String fileName, String templateName, Map<Object, Object> map){
		//获取不要修改的文字
		String startEnd = UtilNoReplace.getNoReplaceText(fileName);
		
		Configuration config = new Configuration();
		config.setDefaultEncoding("UTF-8");
		config.setTemplateLoader(new ClassTemplateLoader(UtilFreemarker.class, "/"));
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		try{
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"));
			Template template = config.getTemplate(templateName);
			template.process(map, out);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(StringUtils.isNoneBlank(startEnd)) {
			//新字符串覆盖
			UtilNoReplace.setTextToFile(fileName, startEnd);
		}
		
	}
}
