package com.newer.task.service;

import java.util.List;

import com.newer.task.data.pojo.TbEmployee;

public interface IEmployeeService {
	// 验证用户登录的方法
	public abstract TbEmployee checkLogin(String employeeName, String password);

	// 根据主管编号查询下属的方法
	public List<TbEmployee> queryByManagerId(Integer Id,int pageNo, int pageSize);

	// 根据编号查询员工信息
	public TbEmployee queryEmpInfoByEmpId(Integer Id);

	public List<TbEmployee> selectEmp(int pageNo, int pageSize);

	public int insert(TbEmployee employee);

	public int del(TbEmployee employee);

	public TbEmployee selectById(TbEmployee employee);

	public int update(TbEmployee employee);
	
	//根据编号查询员工总数
	public int queryByManagerIdCount(Integer Id);
	
	//查询所有主管
	public List<TbEmployee> queryAllManager();
	
	//查询所有员工信息
	public List<TbEmployee> queryAllPerson(int pageNo, int pageSize);
	

}