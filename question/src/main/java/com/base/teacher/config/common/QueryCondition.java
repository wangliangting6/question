package com.base.teacher.config.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.base.teacher.config.common.base.BaseEntity;


/**
 * @Description 查询条件构造器 
 * @author shenzhixiong 
 * @date 2017-3-8 上午9:38:11
 * @version v1.0  
 * @param <T>
 * @param <T>
 */
public final class QueryCondition extends BaseEntity {
	private static final long serialVersionUID = -2111154113320858338L;
	protected String orderByClause;
	protected String groupByClause;
	protected List<Criteria> oredCriteria;
	protected Object t;

	public Object getT() {
		return t;
	}

	public void setT(Object t) {
		this.t = t;
	}

	public QueryCondition() {
		oredCriteria = new ArrayList<Criteria>();
	}

	protected QueryCondition(QueryCondition example) {
		this.groupByClause = example.groupByClause;
		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}
	
	public String getGroupByClause() {
		return groupByClause;
	}
	
	/**
	 * 放入分组条件   对应xml里面的分组
	 * @param groupByClause
	 */
	public void setGroupByClause(String groupByClause) {
		this.groupByClause = groupByClause;
	}
	/**
	 * 放入排序条件 对应xml的排序
	 * @param orderByClause
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	
	public String getOrderByClause() {
		return orderByClause;
	}

	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}
	/**
	 * 
	 * 多个查询构造器 用or连接 
	 * @param criteria
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}
	/**
	 * 创建where条件 构造器  如果有将会新增一个  多个用 (构造条件1) or (构造条件2)连接
	 * @return
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	public static class Criteria {
		protected List<String> criteriaWithoutValue;
		protected List<Map<String,Object>> criteriaWithSingleValue;
		protected List<Map<String,Object>> criteriaWithListValue;
		protected List<Map<String,Object>> criteriaWithBetweenValue;

		protected Criteria() {
			super();
			criteriaWithoutValue = new ArrayList<String>();
			criteriaWithSingleValue = new ArrayList<Map<String,Object>>();
			criteriaWithListValue = new ArrayList<Map<String,Object>>();
			criteriaWithBetweenValue = new ArrayList<Map<String,Object>>();
		}

		public boolean isValid() {
			return criteriaWithoutValue.size() > 0
					|| criteriaWithSingleValue.size() > 0
					|| criteriaWithListValue.size() > 0
					|| criteriaWithBetweenValue.size() > 0;
		}

		public List<String> getCriteriaWithoutValue() {
			return criteriaWithoutValue;
		}

		public List<Map<String,Object>> getCriteriaWithSingleValue() {
			return criteriaWithSingleValue;
		}

		public List<Map<String,Object>> getCriteriaWithListValue() {
			return criteriaWithListValue;
		}

		public List<Map<String,Object>> getCriteriaWithBetweenValue() {
			return criteriaWithBetweenValue;
		}
		
		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteriaWithoutValue.add(condition);
		}

		protected void addCriterion(String condition, Object value) {
			if (value == null) {
				throw new RuntimeException("Value for " + value + " cannot be null");
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("condition", condition);
			map.put("value", value);
			criteriaWithSingleValue.add(map);
		}

		protected void addCriterion(String condition, List<Object> values) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list cannot be null or empty");
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("condition", condition);
			map.put("value", values);
			criteriaWithListValue.add(map);
		}

		protected void addCriterion(String condition, Object value1,
				Object value2) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values  cannot be null");
			}
			List<Object> list = new ArrayList<Object>();
			list.add(value1);
			list.add(value2);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("condition", condition);
			map.put("value", list);
			criteriaWithBetweenValue.add(map);
		}

		protected void addCriterionForJDBCDate(String condition, Date value) {
			addCriterion(condition, new java.sql.Date(value.getTime()));
		}

		protected void addCriterionForJDBCDate(String condition, List<Date> values) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list cannot be null or empty");
			}
			List<Date> dateList = new ArrayList<Date>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(((Date) iter.next()).getTime()));
			}
			addCriterion(condition, dateList);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1,
				Date value2) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()),
					new java.sql.Date(value2.getTime()));
		}
		
		/**
		 * 字段为空
		 * @param column
		 * @return
		 */
		public Criteria andIsNull(String column) {
			addCriterion(column+" is null");
			return this;
		}
		/**
		 * 字段不为空
		 * @param column 字段
		 * @return
		 */
		public Criteria andIsNotNull(String column) {
			addCriterion(column+" is not null");
			return this;
		}
		
		/**
		 * 拼接sql 没有自带 外括号  
		 * @param conditionSql 条件sql
		 * @return
		 */
		public Criteria andConditionSql(String conditionSql) {
			addCriterion(conditionSql);
			return this;
		}
		
		/**
		 * 字段 等于 值
		 * @param column 字段
		 * @param value 值
		 * @return
		 */
		public Criteria andEqualTo(String column,Object value) {
			addCriterion(column+" =", value);
			return this;
		}
		
		/**
		 * 字段不等于值
		 * @param column 字段
		 * @param value 值
		 * @return
		 */
		public Criteria andNotEqualTo(String column,Object value) {
			addCriterion(column+" !=", value);
			return this;
		}
		
		/**
		 * 字段 大于 值
		 * @param column 字段
		 * @param value 值
		 * @return
		 */
		public Criteria andGreaterThan(String column,Object value) {
			addCriterion(column+" >", value);
			return this;
		}
		
		/**
		 * 字段 大于等于 值
		 * @param column 字段
		 * @param value 值
		 * @return
		 */
		public Criteria andGreaterThanOrEqualTo(String column,Object value) {
			addCriterion(column+" >=", value);
			return this;
		}
		
		/**
		 * 字段 小于 值
		 * @param column 字段
		 * @param value 值
		 * @return
		 */
		public Criteria andLessThan(String column,Object value) {
			addCriterion(column+" <", value);
			return this;
		}
		
		/**
		 * 字段 小于等于 值
		 * @param column 字段
		 * @param value 值
		 * @return
		 */
		public Criteria andLessThanOrEqualTo(String column,Object value) {
			addCriterion(column+" <=", value);
			return this;
		}
		
		/**
		 *  字段  在list集合中  
		 * @param column 
		 * @param values
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public Criteria andIn(String column,@SuppressWarnings("rawtypes") List values) {
			if(values!=null && values.size()>0){
				addCriterion(column+ " in", values);
			}
			return this;
		}
		
		/**
		 * 字段  不在list集合中  
		 * @param column
		 * @param values
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public Criteria andNotIn(String column,@SuppressWarnings("rawtypes") List values) {
			addCriterion(column+" not in", values);
			return this;
		}
		
		/**
		 * 字段在 之间   between value1 and value2
		 * @param column 
		 * @param value1
		 * @param value2
		 * @return
		 */
		public Criteria andBetween(String column,Object value1, Object value2) {
			addCriterion(column+" between", value1, value2);
			return this;
		}
		
		/**
		 * 字段不在 之间  not between value1 and value2
		 * @param column
		 * @param value1  
		 * @param value2
		 * @return
		 */
		public Criteria andNotBetween(String column,Object value1, Object value2) {
			addCriterion(column+" not between", value1, value2);
			return this;
		}
        /**
         * 字段 包含 value  已拼接CONCAT(CONCAT('%','" + value + "'),'%')
         * @param column 字段
         * @param value 值
         * @return
         */
		public Criteria andLike(String column,Object value) {
			addCriterion(column + " like concat(concat('%','" + value + "'),'%') ");
			return this;
		}
		/**
		 * 字段 以值开头
		 * @param column 字段
		 * @param value 值
		 * @return
		 */
		public Criteria andLikeStart(String column,Object value) {
			addCriterion(column + " like concat('" + value + "','%') ");
			return this;
		}
		
		/**
		 * 字段 以值结束
		 * @param column 字段
		 * @param value 值
		 * @return
		 */
		public Criteria andLikeEnd(String column,Object value) {
			addCriterion(column + " like concat('%','" + value + "') ");
			return this;
		}
		/**
		 * 一个值    同时多个字段模糊匹配   效率低    少用
		 * @param value  值
		 * @param columns
		 * @return
		 */
		public Criteria andLikeOr(Object value,String... columns) {
			StringBuffer sBuffer = new StringBuffer("(");
			for (int i = 0; i < columns.length; i++) {
				sBuffer.append(columns[i]).append(" like concat(concat('%','").append(value).append("'),'%')");
				if(i!=columns.length-1) {//如果不是最后一个
					sBuffer.append(" or ");
				}
			}
			sBuffer.append(")");
			addCriterion(sBuffer.toString());
			return this;
		}
		
		/**
		 * 字段 不 包含 value  已拼接CONCAT(CONCAT('%','" + value + "'),'%')
		 * @param column 字段
		 * @param value 值
		 * @return
		 */
		public Criteria andNotLike(String column,Object value) {
			addCriterion(column + " not like concat(concat('%','" + value + "'),'%')");
			return this;
		}
		
		/**
		 * 等于 该时间   
		 * @param column 字段
		 * @param value 时间
		 * @return
		 */
		public Criteria andDateEqualTo(String column,Date value) {
			addCriterionForJDBCDate(column+" =", value);
			return this;
		}
		
		/**
		 * 不等于 该时间
		 * @param column 字段
		 * @param value 时间
		 * @return
		 */
		public Criteria andDateNotEqualTo(String column,Date value) {
			addCriterionForJDBCDate(column+" <>", value);
			return this;
		}
		
		/**
		 * 大于该时间
		 * @param column 字段
		 * @param value 时间
		 * @return
		 */
		public Criteria andDateGreaterThan(String column,Date value) {
			addCriterionForJDBCDate(column+" >", value);
			return this;
		}
		
		/**
		 * 大于等于该时间
		 * @param column 字段
		 * @param value 时间
		 * @return
		 */
		public Criteria andDateGreaterThanOrEqualTo(String column,Date value) {
			addCriterionForJDBCDate(column+" >=", value);
			return this;
		}
		
		/**
		 * 小于该时间
		 * @param column 字段
		 * @param value  时间
		 * @return
		 */
		public Criteria andDateLessThan(String column,Date value) {
			addCriterionForJDBCDate(column+" <", value);
			return this;
		}
		
		/**
		 * 小于等于该时间
		 * @param column 字段
		 * @param value 时间
		 * @return
		 */
		public Criteria andDateLessThanOrEqualTo(String column,Date value) {
			addCriterionForJDBCDate(column+" <=", value);
			return this;
		}
		
		/**
		 * 字段在时间集合中
		 * @param column 字段
		 * @param dateList 时间集合
		 * @return
		 */
		public Criteria andDateIn(String column,List<Date> dateList) {
			addCriterionForJDBCDate(column+" in", dateList);
			return this;
		}
		
		/**
		 * 字段不在时间集合中
		 * @param column 字段
		 * @param dateList 时间集合
		 * @return
		 */
		public Criteria andDateNotIn(String column,List<Date> dateList) {
			addCriterionForJDBCDate(column+" not in", dateList);
			return this;
		}
		
		/**
		 * 时间在起始时间内
		 * @param column 字段
		 * @param beginDate 开始时间
		 * @param endDate 结束时间
		 * @return
		 */
		public Criteria andDateBetween(String column,Date beginDate, Date endDate) {
			addCriterionForJDBCDate(column+" between", beginDate, endDate);
			return this;
		}
		
		/**
		 * 时间不在起始时间内
		 * @param column 字段
		 * @param beginDate 开始时间
		 * @param endDate 结束时间
		 * @return 
		 */
		public Criteria andDateNotBetween(String column,Date beginDate, Date endDate) {
			addCriterionForJDBCDate(column+" not between", beginDate, endDate);
			return this;
		}

		
	}

	@Override
	public void preInsert() {
		
	}

	@Override
	public void preUpdate() {
		
	}
}