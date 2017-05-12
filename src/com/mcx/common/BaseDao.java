package com.mcx.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Description 数据库操作基类
 **/

@Repository
public class BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private T entity;
	
	/**
	 * 获取Hibernate4的Session
	 */
	public Session createSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 创建hql查询
	 * 
	 * @param hql语句
	 */
	public Query createQuery(String hql) {
		return createSession().createQuery(hql);
	}

	/**
	 * 根据Id查询实体
	 */
	@SuppressWarnings("unchecked")
	public T get(final Class<T> entityClass, final Serializable id) {
		return (T) createSession().get(entityClass, id);
	}

	/**
	 * 根据Id查询实体
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public T get(final Class<T> entityClass, final Serializable id,
			LockMode lockMode) {
		return (T) createSession().get(entityClass, id, lockMode);
	}

	/**
	 * 根据Id查询实体（加载）
	 */
	@SuppressWarnings("unchecked")
	public T load(final Class<T> entityClass, final Serializable id) {
		return (T) createSession().load(entityClass, id);
	}

	/**
	 * 保存实体
	 */
	public void save(final T entity) {
		createSession().save(entity);
	}

	/**
	 * 修改实体
	 */
	public void update(final T entity) {
		createSession().update(entity);
		createSession().flush();
	}

	/**
	 * 保存或修改实体
	 */
	public void saveOrUpdate(final T entity) {
		createSession().saveOrUpdate(entity);
	}

	/**
	 * 删除实体
	 */
	public void delete(final T entity) {
		createSession().delete(entity);
		createSession().flush();
	}

	/**
	 * 根据hql获取全部记录
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return createQuery(
				"SELECT en FROM " + entity.getClass().getName() + " en").list();
	}

	/**
	 * 根据hql语句查询list(问号占位符方式传值)
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            查询条件值数组
	 */
	@SuppressWarnings("unchecked")
	public List<T> findList(String hql, Object... params) {
		Query query = createQuery(hql);
		if (params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}

	/**
	 * 根据Hql语句查询list(命名占位符形式传值)
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            查询条件值map(key:占位名,value:查询条件值)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findList(String hql, Map<String, Object> params) {
		Query query = createQuery(hql);
		if (params != null && params.size() > 0) {
			for (Entry<String, Object> param : params.entrySet()) {
				query.setParameter(param.getKey(), param.getValue());
			}
		}
		return query.list();
	}

	/**
	 * 分页查找list(问号占位符方式传值)
	 * 
	 * @param hql
	 *            查询语句
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示数
	 * 
	 * @param params
	 *            查询条件
	 */
	@SuppressWarnings("unchecked")
	public List<T> findList(String hql, int pageNo, int pageSize,
			Object... params) {
		Query query = createQuery(hql);
		if (params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.setFirstResult(pageSize * (pageNo - 1))
				.setMaxResults(pageSize).list();
	}

	/**
	 * 分页查找list(命名占位符形式传值)
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            查询条件值map(key:占位名,value:查询条件值)
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示数
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findList(String hql, Map<String, Object> params, int pageNo,
			int pageSize) {
		Query query = createQuery(hql);
		if (params != null && params.size() > 0) {
			for (Entry<String, Object> param : params.entrySet()) {
				query.setParameter(param.getKey(), param.getValue());
			}
		}
		return query.setFirstResult(pageSize * (pageNo - 1))
				.setMaxResults(pageSize).list();
	}

	/**
	 * 根据hql查询实体是否存在(问号占位符方式传值)
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            查询条件数组
	 */
	public boolean checkExist(String hql, Object... params) {
		return findList(hql, params).size() > 0;
	}

	/**
	 * 根据hql查询实体是否存在(命名占位符形式传值)
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            查询条件值map(key:占位名,value:查询条件值)
	 */
	public boolean checkExist(String hql, Map<String, Object> params) {
		return findList(hql, params).size() > 0;
	}

	/**
	 * 根据hql统计存在实体数量(问号占位符方式传值)
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            查询条件数组
	 */
	public Long count(String hql, Object... params) {
		return (long) findList(hql, params).size();
	}

	/**
	 * 根据hql统计存在实体数量(命名占位符形式传值)
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            查询条件值map(key:占位名,value:查询条件值)
	 */
	public Long count(String hql, Map<String, Object> params) {
		return (long) findList(hql, params).size();
	}

	/**
	 * 根据detachedCriteria查询实体
	 * 
	 * @param detachedCriteria
	 *            离线查询条件
	 * @return 实体类
	 */
	@SuppressWarnings("unchecked")
	public T findEntityByCriteria(final DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(createSession());
		detachedCriteria
				.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		List<T> list = criteria.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据detachedCriteria查询全部记录
	 * 
	 * @param detachedCriteria
	 *            离线查询条件
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(createSession());
		detachedCriteria
				.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		return criteria.list();
	}

	/**
	 * 根据detachedCriteria查询记录
	 * 
	 * @param detachedCriteria
	 *            离线查询条件
	 * @param start
	 *            开始条数
	 * @param size
	 *            查询数量
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final DetachedCriteria detachedCriteria,
			int start, int size) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(createSession());
		detachedCriteria
				.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		return criteria.setFirstResult(start).setMaxResults(size).list();
	}

	/**
	 * 根据detachedCriteria查询全部记录的数量
	 * 
	 * @param detachedCriteria
	 *            离线查询条件
	 * @return count数量
	 */
	public long getCountByCriteria(final DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(createSession());
		return ((Long) criteria.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();
	}

	/**
	 * 根据sql使用jdbcTemplate查询统计
	 * 
	 * @param sql
	 *            :sql语句
	 */
	public List<Map<String, Object>> findByjdbcTemplate(String sql) {
		return jdbcTemplate.queryForList(sql);
	}
}
