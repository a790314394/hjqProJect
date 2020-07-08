package com.newer.task.data.dao;

import java.util.List;

import com.newer.task.data.pojo.TbEmployee;

public interface IEmployeeDAO {

	public abstract List<TbEmployee> selectEmployees(String sql, Object[] params);

	public abstract TbEmployee selectById(Integer id);

	public List<TbEmployee> selectEmp(int pageNo, int pageSize);

	public int insert(TbEmployee employee);

	public int delete(TbEmployee employee);

	public List<TbEmployee> selectById(TbEmployee employee);

	public int update(TbEmployee employee);
	
	public int queryCount(String sql,Integer id);
}
