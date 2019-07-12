package com.base.teacher.config.generator.utils;

/** 
 * 类描述：
 * @address com.celizi.base.common.generator.utils.UtilString
 * @author shenzhixiong  
 * @email 731139982@qq.com
 * @date 创建时间：2017年6月14日 下午1:57:37 
 * @version 1.0  
 */
public class UtilString {
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		if (null == str) {
			return null;
		} else if ("".equals(str)) {
			return str;
		}
		return Character.toTitleCase(str.charAt(0)) + str.substring(1);
	}
	/**
	 * 将表中列名去下划线且下划线后首字母大写其他字母小写
	 * @param columnName 表中列名
	 * @return java类属性名
	 */
	public static String dbNameToVarName(String columnName) {
		if (columnName == null) {
			return null;
		}
		StringBuilder fieldName = new StringBuilder();
		boolean toUpper = false;
		for (int i = 0; i < columnName.length(); i++) {
			char ch = columnName.charAt(i);
			if (ch == '_') {
				toUpper = true;
			} else if (toUpper == true) {
				fieldName.append(Character.toUpperCase(ch));
				toUpper = false;
			} else {
				fieldName.append(Character.toLowerCase(ch));
			}
		}
		return fieldName.toString();
	}
	/**
	 * 将数据库类型转换成java类型
	 * @param columnType 数据库类型
	 * @return java类型
	 */
	public static String dbTypeToJavaType(String columnType) {
		String type = "";
		if (columnType == null || columnType.trim().equals("")) {
			return null;
		}
		if ("VARCHAR".equals(columnType) || "CHAR".equals(columnType)
				|| "TEXT".equals(columnType)) {
			type = "String";
		} else if ("DATE".equals(columnType) || "DATETIME".equals(columnType)) {
			type = "Date";
		} else if ("INT".equals(columnType)
				|| "INT UNSIGNED".equals(columnType)) {
			type = "Integer";
		} else if ("MEDIUMBLOB".equals(columnType)
				|| "VARBINARY".equals(columnType)) {
			type = "byte[]";
		} else if ("DECIMAL".equals(columnType)) {
			type = "BigDecimal";
		} else if ("BIGINT".equals(columnType)) {
			type = "String";
		} else {
			System.out.println("未知的字段类型[" + columnType + "]");
			type = null;
		}
		return type;
	}
	/**
	 * 将数据库类型转换成mybatis配置文件类型
	 * @param sqlTypeName 数据库类型
	 * @return mybatis配置文件类型
	 */
	public static String mybatisType(String sqlTypeName) {
		String type = "";
		if (sqlTypeName == null || sqlTypeName.trim().equals("")) {
			return null;
		}
		if ("VARCHAR".equals(sqlTypeName) || "TEXT".equals(sqlTypeName)) {
			type = "VARCHAR";
		} else if ("TINYBLOB".equals(sqlTypeName) || "BLOB".equals(sqlTypeName)
				|| "MEDIUMBLOB".equals(sqlTypeName)
				|| "VARBINARY".equals(sqlTypeName)) {
			type = "BLOB";
		} else if ("CHAR".equals(sqlTypeName)) {
			type = "CHAR";
		} else if ("INT".equals(sqlTypeName) || "INT UNSIGNED".equals(sqlTypeName)) {
			type = "INTEGER";
		} else if ("DATETIME".equals(sqlTypeName) || "DATE".equals(sqlTypeName)) {
			type = "TIMESTAMP";
		} else if ("DECIMAL".equals(sqlTypeName)) {
			type = "DECIMAL";
		}  else if ("BIGINT".equals(sqlTypeName)) {
			type = "BIGINT";
		}else {
			System.out.println("未知的数据库类型[" + sqlTypeName + "]");
			type = null;
		}
		return type;
	}
	/*public static String getFileName(String tableName, String flag){
		String retName;
		if(){
			
		}
		switch(flag){
			case "po"  : retName = tableName + "_po"; break;
			case "vo"  : retName = tableName + "_vo"; break;
			default : retName = null;
		}
		return retName;
	}*/
}
