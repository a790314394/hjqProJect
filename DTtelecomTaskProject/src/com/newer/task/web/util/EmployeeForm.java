package com.newer.task.web.util;

import com.newer.task.data.pojo.TbEmployee;
import com.newer.task.data.pojo.TbRole;

public class EmployeeForm {
	TbEmployee emp;// 表单员工
	TbRole role;// 表单角色

	public TbEmployee getEmp() {
		return emp;
	}

	public void setEmp(TbEmployee emp) {
		this.emp = emp;
	}

	public TbRole getRole() {
		return role;
	}

	public void setRole(TbRole role) {
		this.role = role;
	}

	public EmployeeForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeForm(TbEmployee emp, TbRole role) {
		super();
		this.emp = emp;
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginForm [emp=" + emp + ", role=" + role + "]";
	}
}
