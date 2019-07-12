package com.base.teacher.config.common.base;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.base.teacher.config.common.ApiResult;
import com.base.teacher.config.common.QueryCondition;
import com.base.teacher.config.common.QueryCondition.Criteria;
import com.base.teacher.config.enums.BaseErrorEnum;
import com.base.teacher.config.utils.StringUtils;



/**
 * 类描述：
 * 
 * @address com.celizi.base.common.service.BaseService
 * @author shenzhixiong
 * @email 731139982@qq.com
 * @date 创建时间：2018年8月1日17:19:49
 * @version 1.0
 * @param <T>
 */
@Transactional(readOnly = true)
public abstract class BaseService<T> {
	
	
	
	
	/**
	 * 在子类实现此函数,为下面的CRUD操作提供DAO.
	 */
	protected abstract BaseDao<T> getEntityDao();

	/***************************************** 基础方法：新增***************************************/

	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public int insert(T entity) {
		return this.getEntityDao().insert(entity);
	}


	/**
	 * 批量新增
	 * @param entitys 对象的list集合
	 */
	@Transactional(readOnly = false)
	public int batchInsert(final List<T> entitys) {
		return this.getEntityDao().batchInsert(entitys);
	}
	
	

	/*****************************************基础方法：删除***************************************/
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public int deleteById(String id) {
		return this.getEntityDao().deleteById(id);
	}

	/**
	 * 按条件进行删除
	 * @param qc 条件
	 * @return
	 */
	@Transactional(readOnly = false)
	public int deleteByCondition(QueryCondition qc) {
		return this.getEntityDao().deleteByCondition(qc);
	}

	/*****************************************基础方法：修改***************************************/
	
	/**
	 * 完全更新  
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = false)
	public int updateAll(T entity) {
		return this.getEntityDao().updateAll(entity);
	}
	
	/**
	 * 更新非空字段
	 * @param entity
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public int update(T entity, String id) {
		QueryCondition qc = new QueryCondition();
		qc.setT(entity);
		Criteria c = qc.createCriteria();
		c.andEqualTo(BaseGlobal.ID, id);
		return this.getEntityDao().batchUpdateByCondition(qc);
	}


	/**
	 * 批量修改
	 * @param entitys
	 */
	@Transactional(readOnly = false)
	public int batchUpdate(final List<T> entitys) {
		return this.getEntityDao().batchUpdate(entitys);
	}
	/**
	 * 按条件批量更新  非空   qc.T为修改的实体  
	 * @param qc
	 * @return
	 */
	@Transactional(readOnly = false)
	public int batchUpdateByCondition(QueryCondition qc){
		return this.getEntityDao().batchUpdateByCondition(qc);
	}
	/***************************************** 基础方法：查询 ***************************************/

	/**
	 * 查询全部信息
	 * @param qc
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return this.getEntityDao().findByCondition(new QueryCondition());
	}

	/**
	 * 按条件查询
	 * @param qc
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> findByCondition(QueryCondition qc) {
		return this.getEntityDao().findByCondition(qc);
	}

	/**
	 * 按条件查询单个对象
	 * @param qc 查询条件
	 * @return 单个实体类
	 */
	@Transactional(readOnly = true)
	public T getByCondition(QueryCondition qc) {
		return this.getEntityDao().getByCondition(qc);
	}

	/**
	 * 通过主键查询单个实体
	 * @param pk 主键
	 * @return
	 */
	@Transactional(readOnly = true)
	public T getById(Object pk) {
		if (pk != null) {
			return this.getEntityDao().getById(pk);
		}
		return null;
	}

	
	
/*****************************************继承使用：新增，修改，删除***************************************/ 
	
	
	@Transactional(readOnly = false)
	public int insert(String statementName) {
    	return this.getEntityDao().insert(statementName);
    }
	
	@Transactional(readOnly = false)
	public int delete(String statementName) {
		return this.getEntityDao().delete(statementName);
    }
	
	@Transactional(readOnly = false)
	public int update(String statementName) {
		return this.getEntityDao().update(statementName);
    }
	

	
	@Transactional(readOnly = false)
	public int insertByEntity(String statementName, T t) {
		return this.getEntityDao().insertByEntity(statementName, t);
    }
	
	
	@Transactional(readOnly = false)
	public int deleteByEntity(String statementName, T t) {
		return this.getEntityDao().deleteByEntity(statementName, t);
    }
	
	@Transactional(readOnly = false)
	public int updateByEntity(String statementName, T t) {
		return this.getEntityDao().updateByEntity(statementName, t);
    }
	
	
	
	@Transactional(readOnly = false)
	public int insertByMap(String statementName, Map<String,?> params) {
		return this.getEntityDao().insertByMap(statementName, params);
    }
	
	@Transactional(readOnly = false)
	public int deleteByMap(String statementName, Map<String,?> params) {
		return this.getEntityDao().deleteByMap(statementName, params);
    }
	
	@Transactional(readOnly = false)
	public int updateByMap(String statementName, Map<String,?> params) {
		return this.getEntityDao().updateByMap(statementName, params);
    }
	
	
	
	@Transactional(readOnly = false)
	public int insertByCondition(String statementName, QueryCondition qc) {
		return this.getEntityDao().insertByCondition(statementName, qc);
    }
	
	@Transactional(readOnly = false)
	public int deleteByCondition(String statementName, QueryCondition qc) {
		return this.getEntityDao().deleteByCondition(statementName, qc);
    }
	
	@Transactional(readOnly = false)
	public int updateByCondition(String statementName, QueryCondition qc) {
		return this.getEntityDao().updateByCondition(statementName, qc);
    }
	
	/**
	 * 批量新增
	 * @param statementName
	 * @param entitys
	 * @return
	 */
	@Transactional(readOnly = false)
	public int batchInsert(String statementName, final List<T> entitys) {
		return this.getEntityDao().batchInsert(statementName, entitys);
	}
	
	@Transactional(readOnly = false)
	public int batchUpdate(String statementName, final List<T> entitys) {
		return this.getEntityDao().batchUpdate(statementName, entitys);
	}
	
	/***************************************** 继承使用：查询T 单个实体 ***************************************/
	/**
	 * 根据xml 查询语句的id查询单个实体
	 * @param statementName
	 * @return
	 */
	@Transactional(readOnly = true)
	public T selectOne(String statementName) {
		return this.getEntityDao().selectOne(statementName);
	}
	/**
	 * 通过实体 附加参数查询
	 * @param statementName
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = true)
	public T selectOne(String statementName, Object entity) {
		return this.getEntityDao().selectOne(statementName, entity);
	}
	/**
	 * 通过map 附加参数查询
	 * @param statementName
	 * @param params
	 * @return
	 */
	@Transactional(readOnly = true)
	public T selectOne(String statementName, Map<String, ?> params) {
		return this.getEntityDao().selectOne(statementName, params);
	}
	/**
	 * 通过查询条件 附加参数查询
	 * @param statementName
	 * @param qc
	 * @return
	 */
	@Transactional(readOnly = true)
	public T selectOne(String statementName, QueryCondition qc) {
		return this.getEntityDao().selectOne(statementName, qc);
	}
	
	
	/***************************************** 继承使用：查询List<T>***************************************/
	/**
	 * 通过方法名查询
	 * @param statementName
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> selectList(String statementName) {
		return this.getEntityDao().selectList(statementName);
	}

	/**
	 * 通过实体 附加参数查询
	 * @param statementName
	 * @param entity
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> selectList(String statementName, Object entity) {
		return this.getEntityDao().selectList(statementName, entity);
	}

	/**
	 * 附加键值对参数进行查询
	 * @param statementName
	 * @param params
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> selectList(String statementName, Map<String, ?> params) {
		return this.getEntityDao().selectList(statementName, params);
	}

	/**
	 * 附加通用构造器的参数 进行查询
	 * @param statementName
	 * @param qc
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> selectList(String statementName, QueryCondition qc) {
		return this.getEntityDao().selectList(statementName, qc);
	}

	/***************************************** 继承使用：查询<E> List<E>***************************************/
	@Transactional(readOnly = true)
	public <E> List<E> selectList2(String statementName) {
		return this.getEntityDao().selectList2(statementName);
	}

	@Transactional(readOnly = true)
	public <E> List<E> selectList2(String statementName, Object entity) {
		return this.getEntityDao().selectList2(statementName, entity);
	}
	
	@Transactional(readOnly = true)
	public <E> List<E> selectList2(String statementName, Map<String, ?> params) {
		return this.getEntityDao().selectList2(statementName, params);
	}

	@Transactional(readOnly = true)
	public <E> List<E> selectList2(String statementName, QueryCondition qc) {
		return this.getEntityDao().selectList2(statementName, qc);
	}

	/***************************************** 继承使用：查询<E> E***************************************/

	@Transactional(readOnly = true)
	public <E> E selectOne2(String statementName) {
		return this.getEntityDao().selectOne2(statementName);
	}

	@Transactional(readOnly = true)
	public <E> E selectOne2(String statementName, Object entity) {
		return this.getEntityDao().selectOne2(statementName, entity);
	}

	@Transactional(readOnly = true)
	public <E> E selectOne2(String statementName, Map<String, ?> params) {
		return this.getEntityDao().selectOne2(statementName, params);
	}

	@Transactional(readOnly = true)
	public <E> E selectOne2(String statementName, QueryCondition qc) {
		return this.getEntityDao().selectOne2(statementName, qc);
	}
	
	/****************************************国际化方法satrt************************************************/
	/**
	 * 获取国际化信息
	 * @param messageCcode  国际化编码 key
	 * @return
	 */
	protected String getMessage(String messageCcode) {
		String msg = "";
		if (StringUtils.isNotBlank(msg)) {
			return msg;
		}
		return messageCcode;
	}

	/**
	 * 获取国际化信息
	 * @param messageCcode 国际化编码key
	 * @param args 默认值 取不到则返回本数据
	 * @return
	 */
	protected String getMessage(String messageCcode, Object[] args) {
		String msg = "";
		if (StringUtils.isNotBlank(msg)) {
			return msg;
		}
		return messageCcode;
	}
/****************************************国际化方法end  统一返回值  ************************************************/	
	
	/****************************************查询  ************************************************/
	/**查询成功:单个String，实体，分页对象*/
	protected ApiResult selectSuccess(Object object) {
		return success(BaseErrorEnum.SUCCESS_SELECT.getErrorMessage(),object);
	}
	/**查询成功：多个实体，list等 */
	protected ApiResult selectSuccess(Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_SELECT.getErrorMessage(),attributes);
	}
	/**查询成功：单个主实体对象，附加的多个list */
	protected ApiResult selectSuccess(Object object,Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_SELECT.getErrorMessage(),object,attributes);
	}
	/**查询失败：属性不满足要求(不存在，不符合，不在之内等) */
	protected ApiResult selectAttributeNotRequirements(Object object) {
		return error(BaseErrorEnum.ERR_SELECT_ATTRIBUTE_NOT_REQUIREMENTS.getErrorMessage(),object);
	}
	/** 查询失败：找不到数据*/
	protected ApiResult selectNotFound(Object object) {
		return error(BaseErrorEnum.ERR_SELECT_NOT_FOUND.getErrorMessage(),object);
	}
	/** 查询失败：找不到数据*/
	protected ApiResult selectNotFound() {
		return error(BaseErrorEnum.ERR_SELECT_NOT_FOUND.getErrorMessage());
	}
	
	/****************************************新增  ************************************************/
	/**新增成功:单个String，实体，分页对象*/
	protected ApiResult insertSuccess(Object object) {
		return success(BaseErrorEnum.SUCCESS_INSERT.getErrorMessage(),object);
	}
	/**新增成功：多个实体，list等 */
	protected ApiResult insertSuccess(Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_INSERT.getErrorMessage(),attributes);
	}
	/**新增成功：单个主实体对象，附加的多个list */
	protected ApiResult insertSuccess(Object object,Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_INSERT.getErrorMessage(),object,attributes);
	}
	
	/** 新增失败：属性不满足要求(不存在，不符合，不在之内等) */
	protected ApiResult insertAttributeNotRequirements(Object object) {
		return error(BaseErrorEnum.ERR_INSERT_ATTRIBUTE_NOT_REQUIREMENTS.getErrorMessage(),object);
	}
	/** 新增失败：数据已存在*/
	protected ApiResult insertDataRepeat(Object object) {
		return error(BaseErrorEnum.ERR_INSERT_DATA_REPEAT.getErrorMessage(),object);
	}
	/** 新增失败：数据已存在*/
	protected ApiResult insertDataRepeat() {
		return error(BaseErrorEnum.ERR_INSERT_DATA_REPEAT.getErrorMessage());
	}
	
	
	/****************************************修改 ************************************************/
	/**修改成功:单个String，实体，分页对象*/
	protected ApiResult updateSuccess(Object object) {
		return success(BaseErrorEnum.SUCCESS_UPDATE.getErrorMessage(),object);
	}
	/**修改成功：多个实体，list等 */
	protected ApiResult updateSuccess(Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_UPDATE.getErrorMessage(),attributes);
	}
	/**修改成功：单个主实体对象，附加的多个list */
	protected ApiResult updateSuccess(Object object,Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_UPDATE.getErrorMessage(),object,attributes);
	}
	
	/**修改失败：属性不满足要求(不存在，不符合，不在之内等) */
	protected ApiResult updateAttributeNotRequirements(Object object) {
		return error(BaseErrorEnum.ERR_UPDATE_ATTRIBUTE_NOT_REQUIREMENTS.getErrorMessage(),object);
	}
	/**修改失败：找不到数据*/
	protected ApiResult updateNotFound(Object object) {
		return error(BaseErrorEnum.ERR_UPDATE_DATA_NOT_FOUND.getErrorMessage(),object);
	}
	/**修改失败：找不到数据*/
	protected ApiResult updateNotFound() {
		return error(BaseErrorEnum.ERR_UPDATE_DATA_NOT_FOUND.getErrorMessage());
	}
	
	/****************************************删除************************************************/
	/**删除成功:单个String，实体，分页对象*/
	protected ApiResult deleteSuccess(Object object) {
		return success(BaseErrorEnum.SUCCESS_DELETE.getErrorMessage(),object);
	}
	/**删除成功：多个实体，list等 */
	protected ApiResult deleteSuccess(Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_DELETE.getErrorMessage(),attributes);
	}
	/**删除成功：单个主实体对象，附加的多个list */
	protected ApiResult deleteSuccess(Object object,Map<String, Object> attributes) {
		return success(BaseErrorEnum.SUCCESS_DELETE.getErrorMessage(),object,attributes);
	}
	/** 删除失败：属性不满足要求(不存在，不符合，不在之内等) */
	protected ApiResult deleteAttributeNotRequirements(Object object) {
		return error(BaseErrorEnum.ERR_DELETE_ATTRIBUTE_NOT_REQUIREMENTS.getErrorMessage(),object);
	}
	/** 删除失败：找不到数据*/
	protected ApiResult deleteNotFound(Object object) {
		return error(BaseErrorEnum.ERR_DELETE_DATA_NOT_FOUND.getErrorMessage(),object);
	}
	/** 删除失败：找不到数据*/
	protected ApiResult deleteNotFound() {
		return error(BaseErrorEnum.ERR_DELETE_DATA_NOT_FOUND.getErrorMessage());
	}
	
	/****************************************公用************************************************/
	
	protected ApiResult success(String baseErrorEnumMessage,Object object) {
		return ApiResult.success(getMessage(baseErrorEnumMessage),object);
	}
	protected ApiResult success(String baseErrorEnumMessage,Map<String, Object> attributes) {
		return ApiResult.success(getMessage(baseErrorEnumMessage),attributes);
	}
	protected ApiResult success(String baseErrorEnumMessage,Object object,Map<String, Object> attributes) {
		return ApiResult.success(getMessage(baseErrorEnumMessage),object,attributes);
	}
	
	protected ApiResult error(String baseErrorEnumMessage,Object object) {
		return ApiResult.error(getMessage(baseErrorEnumMessage),BaseErrorEnum.valueOf(baseErrorEnumMessage).getErrorCode(),object);
	}
	protected ApiResult error(String baseErrorEnumMessage) {
		return ApiResult.error(getMessage(baseErrorEnumMessage),BaseErrorEnum.valueOf(baseErrorEnumMessage).getErrorCode(),null);
	}
}
