/*
package com.cz.boot.dao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.sql.DataSource;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDAO<T> {
	@Autowired
	private EntityManager em;

	protected EntityManager getCurrentSession() {
		em.setFlushMode(FlushModeType.COMMIT);
		return em;
	}

	@Autowired
	private DataSource dataSource;
	private Class<T> clazz;

	private Connection conn;
	private CallableStatement stmt;

	public Connection getConnect() {
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println("dataSource=" + dataSource + ";conn=" + conn);
		return conn;
	}

	public static void closeConn(Connection conn, Statement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public BaseDAO() {
		clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}


	public List<Map> findListPageSQL(String sql, Map<String, Object> params, int page, int rows)
	{
		Query q = getCurrentSession().createNativeQuery(sql);
		if ((params != null) && (!params.isEmpty())) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		((SQLQuery)q.unwrap(SQLQuery.class)).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);

		List retmap = q.setFirstResult((page - 1) * rows).setMaxResults(rows)
				.getResultList();
		return changeToLowerList(retmap);
	}

	public List<Map> findListPageSQL(String sql, int page, int rows)
	{
		Query q = getCurrentSession().createNativeQuery(sql);
		((SQLQuery)q.unwrap(SQLQuery.class)).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		List retmap = q.setFirstResult((page - 1) * rows).setMaxResults(rows).getResultList();
		return changeToLowerList(retmap);
	}

	public Object getCallProc(String sql) throws SQLException {
		System.out.println("getCallProc=" + sql);
		Object object = null;
		conn = getConnect();
		stmt = conn.prepareCall(sql);
		stmt.registerOutParameter(1, Types.NUMERIC);
		stmt.execute();
		object = stmt.getObject(1);
		return object;
	}

	// */
/**
	// * 保存对象
	// *
	// * @param o
	// * @return
	// *//*

	// public void save(T o) {
	// if (o != null) {
	// this.getCurrentSession().persist(o);
	// }
	// }
	//

	public T save(T o) {
		if (o != null) {
			getCurrentSession().persist(o);
			//getCurrentSession().merge(o);
		}
		return o;
	}
	*/
/**
	 * 删除对象
	 *
	 * @param o
	 *//*

	public void delete(T o) {
		if (o != null) {
			this.getCurrentSession().remove(o);
		}
	}

	*/
/**
	 * 根据ID删除对象
	 *
	 * @param id
	 *//*

	public void deleteById(Serializable id) {
		T obj = this.findById(id);
		this.getCurrentSession().remove(obj);
		this.getCurrentSession().flush();
	}

	*/
/**
	 * 修改对象
	 *
	 * @param o
	 *//*

	public void update(T o) {
		if (o != null) {
			this.getCurrentSession().merge(o);
		}
	}

	*/
/**
	 * 查询对象
	 *
	 * @param id
	 * @return
	 *//*

	public T get(Serializable id) {
		return this.getCurrentSession().find(clazz, id);
	}

	*/
/**
	 * 根据JPQL查询单个对象
	 *
	 * @param JPQL
	 * @return
	 *//*

	public T get(String jpql) {
		Query q = this.getCurrentSession().createQuery(jpql);

		q.setMaxResults(1);
		List<T> list = q.getResultList();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	*/
/**
	 * 根据本地SQL和参数,返回单个对象,map<String,Object>
	 *
	 * @param sql
	 * @param params
	 * @return
	 *//*

	public Map getBySQL(String sql, Map<String, Object> params)
			throws Exception {
		Query q = this.getCurrentSession().createNativeQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		q.unwrap(SQLQuery.class).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		q.setMaxResults(1);
		List<Map> l = q.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	*/
/**
	 * 根据本地SQL查询单个对象
	 *
	 * @param sql
	 * @return
	 *//*

	public Map getBySQL(String sql) {
		Query q = this.getCurrentSession().createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		q.setMaxResults(1);
		List<Map> l = q.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	*/
/**
	 * 根据jpql和参数查询单个对象
	 *
	 * @param JPQL
	 * @param params
	 * @return
	 *//*

	public T get(String jpql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(jpql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		q.setMaxResults(1);
		List<T> l = q.getResultList();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	*/
/**
	 * 根据ID查询对象
	 *
	 * @param id
	 * @return
	 *//*

	public T findById(Serializable id) {
		if (null == id || "".equals(id)) {
			return null;
		} else {
			return this.getCurrentSession().find(clazz, id);
		}
	}

	*/
/**
	 * 根据JPQL查询对象列表
	 *
	 * @param JPQL
	 * @return
	 *//*

	public List<T> find(String jpql) {
		Query q = this.getCurrentSession().createQuery(jpql);
		return q.getResultList();
	}

	*/
/**
	 * 根据JPQL和参数查询对象列表
	 *
	 * @param jpql
	 * @param params
	 * @return
	 *//*

	public List<T> find(String jpql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(jpql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.getResultList();
	}

	*/
/**
	 * 根据原始SQL和参数查询某页数据
	 *
	 * @param sql
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 *//*

//	public List<Map> findListPageSQL(String sql, Map<String, Object> params,
//									 int page, int rows) {
//		Query q = this.getCurrentSession().createNativeQuery(sql);
//		if (params != null && !params.isEmpty()) {
//			for (String key : params.keySet()) {
//				q.setParameter(key, params.get(key));
//			}
//		}
//		q.unwrap(SQLQuery.class).setResultTransformer(
//				Transformers.ALIAS_TO_ENTITY_MAP);
//		return q.setFirstResult((page - 1) * rows).setMaxResults(rows)
//				.getResultList();
//	}

	*/
/**
	 * 根据原始SQL查询分页数据
	 *
	 * @param sql
	 * @param page
	 * @param rows
	 * @return
	 *//*

//	public List<Map> findListPageSQL(String sql, int page, int rows) {
//		Query q = this.getCurrentSession().createNativeQuery(sql);
//		q.unwrap(SQLQuery.class).setResultTransformer(
//				Transformers.ALIAS_TO_ENTITY_MAP);
//		return q.setFirstResult((page - 1) * rows).setMaxResults(rows)
//				.getResultList();
//	}

	*/
/**
	 * 根据jpql和参数查询分页数据
	 *
	 * @param jpql
	 * @param params
	 * @param page
	 * @param rows
	 * @return
	 *//*

	public List<T> find(String jpql, Map<String, Object> params, int page,
						int rows) {
		Query q = this.getCurrentSession().createQuery(jpql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows)
				.getResultList();
	}

	*/
/**
	 * 根据jpql查询分页数据
	 *
	 * @param jpql
	 * @param page
	 * @param rows
	 * @return
	 *//*

	public List<T> find(String jpql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(jpql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows)
				.getResultList();
	}

	*/
/**
	 * 查询总量
	 *
	 * @param JPQL
	 * @return
	 *//*

	public Long count(String jpql) {
		Query q = this.getCurrentSession().createQuery(jpql);
		return (Long) q.getSingleResult();
	}

	*/
/**
	 * 带参数的查询总量
	 *
	 * @param JPQL
	 * @param params
	 * @return
	 *//*

	public Long count(String jpql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(jpql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.getSingleResult();
	}

	*/
/**
	 * 执行JPQL
	 *
	 * @param JPQL
	 * @return
	 *//*

	public int executeHql(String jqpl) {
		Query q = this.getCurrentSession().createQuery(jqpl);
		return q.executeUpdate();
	}

	*/
/**
	 * 执行带参数的jqpl
	 *
	 * @param jqpl
	 * @param params
	 * @return
	 *//*

	public int executeHql(String jqpl, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(jqpl);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	*/
/**
	 * 原始SQL和参数查询，返回List<Map<String,Object>>结果集
	 *
	 * @param sql
	 * @param params
	 * @return
	 *//*

	public List<Map> findListBySqlMap(String sql, Map<String, Object> params) {
		if (null == sql || "".equals(sql)) {
			return null;
		}
		Query q = this.getCurrentSession().createNativeQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		q.unwrap(SQLQuery.class).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		return q.getResultList();
	}

	*/
/**
	 * 原始SQL查询，返回List<Map<String,Object>>结果集
	 *
	 * @param sql
	 * @return
	 *//*

	public List<Map> findListBySql(String sql) {
		if (null == sql || "".equals(sql)) {
			return null;
		}
		Query q = this.getCurrentSession().createNativeQuery(sql);
		q.unwrap(SQLQuery.class).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		return q.getResultList();
	}

	*/
/**
	 * 执行原始SQL
	 *
	 * @param sql
	 * @return
	 *//*

	public int executeSql(String sql) {
		Query q = this.getCurrentSession().createNativeQuery(sql);
		return q.executeUpdate();
	}

	*/
/**
	 * 执行带参数的原始SQL
	 *
	 * @param sql
	 * @param params
	 * @return
	 *//*

	public int executeSql(String sql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createNativeQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	*/
/**
	 * 原始SQL總計
	 *
	 * @param sql
	 * @return
	 *//*

	public Long countBySql(String sql) {
		Query q = this.getCurrentSession().createNativeQuery(sql);
		Object obj = q.getSingleResult();
		return  Long.parseLong(obj.toString());
	}

	*/
/**
	 * 原始SQL带参数总计
	 *
	 * @param sql
	 * @param params
	 * @return
	 *//*

	public Long countBySql(String sql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createNativeQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		Object obj = q.getSingleResult();
		return  Long.parseLong(obj.toString());
	}

	*/
/**
	 * 批量保存数据
	 *
	 * @param <T>
	 * @param entitys
	 *            要持久化的临时实体对象集合
	 *//*

	public void batchSave(List<T> entitys) throws SQLException {
		for (int i = 0; i < entitys.size(); i++) {
			this.save(entitys.get(i));
			if (i % 20 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getCurrentSession().flush();
				getCurrentSession().clear();
			}
		}
		// 最后清理一下----防止大于20小于40的不保存
		getCurrentSession().flush();
		getCurrentSession().clear();
	}

	*/
/**
	 * 总计
	 *
	 * @param finder
	 * @return
	 *//*

	public Long countQueryResult(Finder finder) {
		Query query = getCurrentSession().createQuery(finder.getRowCountJpql());
		finder.setParamsToQuery(query);
		return (Long) query.getSingleResult();
	}

	*/
/**
	 * JPQL分页方法
	 *
	 * @param finder
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *//*

	public PageInfo getPageList(Finder finder, Integer pageNo, Integer pageSize) {
		Long totalCount = countQueryResult(finder);
		PageInfo page = new PageInfo();
		if (pageNo != null) {
			page.setCurrPageNo(pageNo);
		} else {
			pageNo = page.getCurrPageNo();
		}
		if (pageSize != null) {
			page.setPageSize(pageSize);
		} else {
			pageSize = page.getPageSize();
		}
		page.setTotal(totalCount);
		long cnt = totalCount % pageSize;
		long pagetotal = 0;
		if (cnt == 0) {
			pagetotal = totalCount / pageSize;
		} else {
			pagetotal = totalCount / pageSize + 1;
		}

		page.setPageTotal(pagetotal);
		if (totalCount < 1) {
			page.setRows(new ArrayList());
			return page;
		}
		int offset = (pageNo - 1) * page.getPageSize();
		Query query = getCurrentSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List list = query.getResultList();
		page.setRows(list);
		return page;
	}

	*/
/**
	 * 获取条数
	 *
	 * @param finder
	 * @return
	 *//*

	private Long countSQLQueryResult(Finder finder) {
		Query query = getCurrentSession().createNativeQuery(
				finder.getRowCountJpql());
		finder.setParamsToQuery(query);
		Object o = query.getSingleResult();
		BigInteger bi = (BigInteger) o;
		return bi.longValue();
	}

	*/
/**
	 * 原始SQL分页查询
	 *
	 * @param finder
	 * @param pageNo
	 * @param pageSize
	 * @return
	 *//*

	public PageInfo getSQLPageList(Finder finder, Integer pageNo,
								   Integer pageSize) {
		long totalCount = countSQLQueryResult(finder);
		PageInfo page = new PageInfo();
		if (pageNo != null) {
			page.setCurrPageNo(pageNo);
		} else {
			pageNo = page.getCurrPageNo();
		}
		if (pageSize != null) {
			page.setPageSize(pageSize);
		} else {
			pageSize = page.getPageSize();
		}
		page.setTotal(totalCount);
		long cnt = totalCount % pageSize;
		long pagetotal = 0;
		if (cnt == 0) {
			pagetotal = totalCount / pageSize;
		} else {
			pagetotal = totalCount / pageSize + 1;
		}

		page.setPageTotal(pagetotal);
		if (totalCount < 1) {
			page.setRows(new ArrayList());
			return page;
		}
		int offset = (pageNo - 1) * page.getPageSize();
		Query query = getCurrentSession()
				.createNativeQuery(finder.getOrigHql());
		query.unwrap(SQLQuery.class).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		finder.setParamsToQuery(query);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List list = query.getResultList();
		page.setRows(list);
		return page;
	}

	public List<T> findListByJpql(String jpql) {
		Query q = getCurrentSession().createQuery(jpql);
		return q.getResultList();
	}

	public List<T> findListByJpql(String jpql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(jpql);
		if ((params != null) && (!params.isEmpty())) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.getResultList();
	}

	public List<Map> findListBySql(String sql, Map<String, Object> params) {
		if ((sql == null) || ("".equals(sql))) {
			return null;
		}
		Query q = getCurrentSession().createNativeQuery(sql);
		if ((params != null) && (!params.isEmpty())) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		q.unwrap(SQLQuery.class).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		return changeToLowerList(q.getResultList());
	}

	public List<Map> changeToLowerList(List<Map> list) {
		List<Map> returnlist = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map mapreturn = new HashMap();
			Map mapresult = list.get(i);
			Set set = mapresult.entrySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				Map.Entry mapentry = (Map.Entry) iterator.next();
				mapreturn.put(mapentry.getKey().toString().toLowerCase(),
						mapentry.getValue());
			}
			returnlist.add(mapreturn);
		}
		return returnlist;
	}

	public PageInfo getJpqlPageList(Finder finder, Integer pageNo,
									Integer pageSize) {
		Long totalCount = countJpqlQueryResult(finder);
		PageInfo page = new PageInfo();
		if (pageNo != null) {
			page.setCurrPageNo(pageNo);
		} else {
			pageNo = page.getCurrPageNo();
		}
		if (pageSize != null) {
			page.setPageSize(pageSize);
		} else {
			pageSize = page.getPageSize();
		}
		page.setTotal(totalCount);
		long cnt = totalCount.longValue() % pageSize.intValue();
		long pagetotal = 0L;
		if (cnt == 0L) {
			pagetotal = totalCount.longValue() / pageSize.intValue();
		} else {
			pagetotal = totalCount.longValue() / pageSize.intValue() + 1L;
		}
		page.setPageTotal(Long.valueOf(pagetotal));
		if (totalCount.longValue() < 1L) {
			page.setRows(new ArrayList());
			return page;
		}
		int offset = (pageNo.intValue() - 1) * page.getPageSize().intValue();
		Query query = getCurrentSession().createQuery(finder.getOrigHql());
		finder.setParamsToQuery(query);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize.intValue());
		List list = query.getResultList();
		page.setRows(list);
		return page;
	}

	public Long countJpqlQueryResult(Finder finder) {
		Query query = getCurrentSession().createQuery(finder.getRowCountJpql());
		finder.setParamsToQuery(query);
		return (Long) query.getSingleResult();
	}

	public T findByJpql(String jpql) {
		Query q = getCurrentSession().createQuery(jpql);
		q.setMaxResults(1);
		List<T> list = q.getResultList();
		if ((list != null) && (!list.isEmpty())) {
			return list.get(0);
		}
		return null;
	}

	public T findByJpql(String jpql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(jpql);
		if ((params != null) && (!params.isEmpty())) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		q.setMaxResults(1);
		List<T> l = q.getResultList();
		if ((l != null) && (l.size() > 0)) {
			return l.get(0);
		}
		return null;
	}

	public int executeJpql(String jpql) {
		Query q = getCurrentSession().createQuery(jpql);
		return q.executeUpdate();
	}

	public int executeJpql(String jpql, Map<String, Object> params) {
		Query q = getCurrentSession().createQuery(jpql);
		if ((params != null) && (!params.isEmpty())) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	public Map findBySql(String sql) {
		Query q = getCurrentSession().createNativeQuery(sql);
		q.unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		q.setMaxResults(1);
		List<Map> l = q.getResultList();
		if ((l != null) && (l.size() > 0)) {
			return changeToLowerMap(l.get(0));
		}
		return null;
	}

	public Map findBySql(String sql, Map<String, Object> params) {
		Query q = getCurrentSession().createNativeQuery(sql);
		if ((params != null) && (!params.isEmpty())) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		q.unwrap(SQLQuery.class)
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		q.setMaxResults(1);
		List<Map> l = q.getResultList();
		if ((l != null) && (l.size() > 0)) {
			return changeToLowerMap(l.get(0));
		}
		return null;
	}

	private Map changeToLowerMap(Map map) {
		Map mapreturn = new HashMap();
		Set set = map.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iterator.next();
			mapreturn.put(mapentry.getKey().toString().toLowerCase(),
					mapentry.getValue());
		}
		return mapreturn;
	}


}
*/
