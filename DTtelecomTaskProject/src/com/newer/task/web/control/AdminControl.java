package com.newer.task.web.control;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.e;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newer.task.data.pojo.TbEmployee;
import com.newer.task.data.pojo.TbRole;
import com.newer.task.data.util.PageBean;
import com.newer.task.data.util.StringUtil;
import com.newer.task.service.IEmployeeService;

@Controller
public class AdminControl {
	@Resource(name = "employeeService")
	IEmployeeService service;

	@RequestMapping("useradmin.do")
	public String sel(Model model, HttpServletRequest request) {
		int pageNo = 1;// 初始为第一页
		int pageSize = 5;// 大小为5
		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			;
		List<TbEmployee> all = service.selectEmp(pageNo, pageSize);
		PageBean<TbEmployee> p = new PageBean<TbEmployee>();
		p.setData(all);
		p.setPageSize(pageSize);
		p.setPageNo(pageNo);
		p.setTotalRecords(service.selectEmp(1, 10000).size());
		request.getSession().setAttribute("all", p);
		return "admin/useradmin";
	}

	@RequestMapping("empadmin.do")
	public String sel1(Model model, HttpServletRequest request) {
		int pageNo = 1;// 初始为第一页
		int pageSize = 5;// 大小为5
		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			;
		List<TbEmployee> all = service.selectEmp(pageNo, pageSize);
		PageBean<TbEmployee> p = new PageBean<TbEmployee>();
		p.setData(all);
		p.setPageSize(pageSize);
		p.setPageNo(pageNo);
		p.setTotalRecords(service.selectEmp(1, 10000).size());
		request.getSession().setAttribute("all", p);
		return "admin/empadmin";
	}

	@RequestMapping("personadd.do")
	public String register_user(Model model, HttpServletRequest request) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(2, "系统管理员");
		map.put(3, "主管");
		map.put(4, "员工");
		request.getSession().setAttribute("roles", map);
		return "admin/personadd";
	}

	@RequestMapping("register.do")
	public String register(@ModelAttribute("emps") TbEmployee employee,
			Integer roleId, Model model, HttpServletRequest request) {
		System.out.println(employee.toString());
		TbRole r = new TbRole();
		r.setRoleId(roleId);
		employee.setRole(r);
		System.out.println("注册控制器");
		int count = service.insert(employee);
		return this.sel(model, request);
	}

	@RequestMapping("delete.do")
	public String del(@ModelAttribute("emp") TbEmployee emp, Model model,
			HttpServletRequest request) {
		int count = service.del(emp);
		if (count > 0) {
			System.out.println("删除成功");
		}
		return this.sel1(model, request);
	}

	@RequestMapping("empdistribute.do")
	public String sel2(Model model, HttpServletRequest request) {
		int pageNo = 1;// 初始为第一页
		int pageSize = 5;// 大小为5
		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			;
		// 查询所有员工
		List<TbEmployee> all = service.queryAllPerson(pageNo, pageSize);
		PageBean<TbEmployee> p = new PageBean<TbEmployee>();
		p.setData(all);
		p.setPageSize(pageSize);
		p.setPageNo(pageNo);
		p.setTotalRecords(service.queryAllPerson(1, 10000).size());
		request.getSession().setAttribute("all", p);
		return "admin/empdistribute";
	}

	@RequestMapping("selById.do")
	public String selById(Integer index, HttpServletRequest request)
			throws ParseException {
		PageBean<TbEmployee> p = (PageBean<TbEmployee>) request.getSession()
				.getAttribute("all");
		TbEmployee employee = p.getData().get(index);
		// 查询所有主管信息
		List<TbEmployee> allManager = this.service.queryAllManager();
		System.out.println("打印一下员工属性" + employee);
		request.setAttribute("manager", allManager);
		request.setAttribute("emp", employee);
		return "admin/persondesc";
	}

	@RequestMapping("update.do")
	public String update(@ModelAttribute("emp") TbEmployee employee,
			Integer parentId, Model model, HttpServletRequest request) {
		System.out.println("修改控制器");
		System.out.println("要修改的员工" + employee);
		TbEmployee parentEmployee = new TbEmployee();
		parentEmployee.setEmployeeId(parentId);
		employee.setParent(parentEmployee);
		int count = service.update(employee);
		System.out.println(count > 0 ? "修改成功了" : "修改失败了");

		return this.sel(model, request);
	}
}
