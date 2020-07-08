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
		int pageNo = 1;// ��ʼΪ��һҳ
		int pageSize = 5;// ��СΪ5
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
		int pageNo = 1;// ��ʼΪ��һҳ
		int pageSize = 5;// ��СΪ5
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
		map.put(2, "ϵͳ����Ա");
		map.put(3, "����");
		map.put(4, "Ա��");
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
		System.out.println("ע�������");
		int count = service.insert(employee);
		return this.sel(model, request);
	}

	@RequestMapping("delete.do")
	public String del(@ModelAttribute("emp") TbEmployee emp, Model model,
			HttpServletRequest request) {
		int count = service.del(emp);
		if (count > 0) {
			System.out.println("ɾ���ɹ�");
		}
		return this.sel1(model, request);
	}

	@RequestMapping("empdistribute.do")
	public String sel2(Model model, HttpServletRequest request) {
		int pageNo = 1;// ��ʼΪ��һҳ
		int pageSize = 5;// ��СΪ5
		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			;
		// ��ѯ����Ա��
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
		// ��ѯ����������Ϣ
		List<TbEmployee> allManager = this.service.queryAllManager();
		System.out.println("��ӡһ��Ա������" + employee);
		request.setAttribute("manager", allManager);
		request.setAttribute("emp", employee);
		return "admin/persondesc";
	}

	@RequestMapping("update.do")
	public String update(@ModelAttribute("emp") TbEmployee employee,
			Integer parentId, Model model, HttpServletRequest request) {
		System.out.println("�޸Ŀ�����");
		System.out.println("Ҫ�޸ĵ�Ա��" + employee);
		TbEmployee parentEmployee = new TbEmployee();
		parentEmployee.setEmployeeId(parentId);
		employee.setParent(parentEmployee);
		int count = service.update(employee);
		System.out.println(count > 0 ? "�޸ĳɹ���" : "�޸�ʧ����");

		return this.sel(model, request);
	}
}
