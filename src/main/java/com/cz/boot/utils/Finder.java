package com.cz.boot.utils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;

public class Finder
{
	private StringBuilder hqlBuilder;
	private Map<String, Object> queryParams;
	private List<String> params;
	private List<Object> values;
	private int firstResult = 0;
	private int maxResults = 0;
	private String countSql = "";
	public static final String ROW_COUNT = "select count(*) ";
	public static final String FROM = "from";
	public static final String DISTINCT = "distinct";
	public static final String HQL_FETCH = "fetch";
	public static final String ORDER_BY = "order";

	public int getFirstResult()
	{
		return this.firstResult;
	}
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	public int getMaxResults() {
		return this.maxResults;
	}
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	protected Finder() {
	}

	public Finder(String sql) {
		this.hqlBuilder = new StringBuilder(sql);
	}
	public Finder(String sql, String countSql) {
		this.hqlBuilder = new StringBuilder(sql);
		this.countSql = countSql;
	}
	public static Finder create(String sql) {
		Finder finder = new Finder(sql);
		return finder;
	}
	public Finder append(String sql) {
		this.hqlBuilder.append(sql);
		return this;
	}

	public String getOrigHql()
	{
		return this.hqlBuilder.toString();
	}

	public String getRowCountJpql()
	{
		String hql = this.hqlBuilder.toString();
		int fromIndex = hql.toLowerCase().indexOf("from");
		String projectionHql = hql.substring(0, fromIndex);
		hql = hql.substring(fromIndex);
		String rowCountHql = hql.replace("fetch", "");
		int index = rowCountHql.indexOf("order ");
		if (index > 0) {
			rowCountHql = rowCountHql.substring(0, index);
		}
		return wrapProjection(projectionHql) + rowCountHql;
	}

	public String getRowCountSql()
	{
		if (NullUtil.IsAllNotNullOfString(new String[] { this.countSql })) {
			return this.countSql;
		}
		String hql = this.hqlBuilder.toString();

		String result = "SELECT COUNT(1) FROM (" + hql + ") COUNTRESULTTMP1";

		return result;
	}

	public Finder setParam(String param, Object value)
	{
		getParams().add(param);
		getValues().add(value);
		return this;
	}

	public Finder setParams(Map<String, Object> paramMap)
	{
		this.queryParams = paramMap;
		for (Map.Entry entry : paramMap.entrySet()) {
			setParam((String)entry.getKey(), entry.getValue());
		}
		return this;
	}

	public Query setParamsToQuery(Query query)
	{
		if (this.queryParams != null) {
			for (Parameter parameter : query.getParameters()) {
				query.setParameter(parameter.getName(), this.queryParams.get(parameter.getName()));
			}
		}
		else if (this.params != null) {
			for (int i = 0; i < this.params.size(); i++) {
				query.setParameter((String)this.params.get(i), this.values.get(i));
			}
		}
		return query;
	}

	public Query createQuery(EntityManager em) {
		return setParamsToQuery(em.createQuery(getOrigHql()));
	}

	private String wrapProjection(String projection) {
		if (projection.toLowerCase().indexOf("new") > -1) {
			return "select count(*) ";
		}
		if (projection.toLowerCase().indexOf("select") == -1)
			return "select count(*) ";
		if (projection.toLowerCase().indexOf("distinct") > -1) {
			return projection.replace("select", "select count(") + ") ";
		}
		return "select count(*) ";
	}

	private List<String> getParams()
	{
		if (this.params == null) {
			this.params = new ArrayList();
		}
		return this.params;
	}

	private List<Object> getValues() {
		if (this.values == null) {
			this.values = new ArrayList();
		}
		return this.values;
	}

//	public static void main(String[] args) {
//		Finder find = create("select name,id,classid from student");
//
//		System.out.println(find.getRowCountSql());
//		System.out.println(find.getOrigHql());
//	}
}