package com.newer.task.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newer.task.data.dao.IEmployeeDAO;
import com.newer.task.data.dao.impl.EmployeeDaoImpl;
import com.newer.task.data.pojo.TbEmployee;
import com.newer.task.service.IEmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

	@Resource(name = "employeeDao")
	IEmployeeDAO employeeDAO;

	@Override
	public TbEmployee checkLogin(String employeeName, String password) {
		TbEmployee employee = null;

		List<TbEmployee> employeeList = new ArrayList<TbEmployee>();

		String whereSql = "select * from t_employee where employee_name=? and password=? and STATUS=0";
		Object[] params = new Object[] { employeeName, password };

		employeeList = this.employeeDAO.selectEmployees(whereSql, params);

		if (employeeList != null && employeeList.size() > 0) {
			employee = employeeList.get(0);
		}
		return employee;
	}

	@Override
	public List<TbEmployee> queryByManagerId(Integer Id, int pageNo,
			int pageSize) {
		// ��дsql �������ܱ�Ų�ѯ ������������Ա��
		String sql = "select *from (select row_number() over(order by EMPLOYEE_ID) no,t.* from  t_employee t  where PARENT_ID=?) temp where temp.no between ? and ?";
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;
		// ����DAO������ѯ��Ա������
		List<TbEmployee> employeeList = employeeDAO.selectEmployees(sql,
				new Object[] { Id, start, end });
		return employeeList;
	}

	@Override
	public TbEmployee queryEmpInfoByEmpId(Integer Id) {
		TbEmployee employee = null;
		List<TbEmployee> employeeList = new ArrayList<TbEmployee>();

		String whereSql = "select * from t_employee where EMPLOYEE_ID=?";
		Object[] params = new Object[] { Id };

		employeeList = this.employeeDAO.selectEmployees(whereSql, params);

		if (employeeList != null && employeeList.size() > 0) {
			employee = employeeList.get(0);
		}
		return employee;
	}

	@Override
	public List<TbEmployee> selectEmp(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		List<TbEmployee> all = employeeDAO.selectEmp(pageNo, pageSize);
		return all;
	}

	@Override
	public int insert(TbEmployee employee) {
		// TODO Auto-generated method stub
		int count = employeeDAO.insert(employee);
		return count;
	}

	@Override
	public int del(TbEmployee employee) {
		// TODO Auto-generated method stub
		int count = employeeDAO.delete(employee);
		return count;
	}

	@Override
	public TbEmployee selectById(TbEmployee employee) {
		// TODO Auto-generated method stub
		TbEmployee tbemp = null;
		if (employeeDAO.selectById(employee).size() > 0) {
			tbemp = employeeDAO.selectById(employee).get(0);
		}
		return tbemp;
	}

	@Override
	public int update(TbEmployee employee) {
		// TODO Auto-generated method stub
		int count = employeeDAO.update(employee);
		return count;
	}

	@Override
	public int queryByManagerIdCount(Integer Id) {
		String sql = "select count(1) as num from t_employee where PARENT_ID=?";
		int count = this.employeeDAO.queryCount(sql, Id);
		return count;
	}

	@Override
	public List<TbEmployee> queryAllManager() {
		// ��дsql
		String sql = "select * from t_employee where ROLE_ID=3 and STATUS=0";
		return this.employeeDAO.selectEmployees(sql, null);

	}

	@Override
	public List<TbEmployee> queryAllPerson(int pageNo, int pageSize) {
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;
		String sql = "select * from(select row_number() over(order by e.employee_id ) no, e.* from t_employee e where e.role_id=4) te where te.no between ? and ?";
		return this.employeeDAO.selectEmployees(sql,
				new Object[] { start, end });
	}

}
