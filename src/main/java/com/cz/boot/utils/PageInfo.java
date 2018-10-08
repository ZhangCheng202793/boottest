package com.cz.boot.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用分页模型
 * 
 * @author zc
 * 
 */
public class PageInfo {
	
	
	/**
	 * 总条数
	 */
	private Long total;
	/**
	 * 总页数
	 */
	private Long pageTotal;
	/**
	 * 当前页
	 */
	private Integer currPageNo = 1;
	/**
	 * 每页条数
	 */
	private Integer pageSize = 20;
	/**
	 * 当前页详细数据
	 */
	private List<?> rows = new ArrayList<Object>();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Long pageTotal) {
		this.pageTotal = pageTotal;
	}

	public Integer getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(Integer currPageNo) {
		this.currPageNo = currPageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
