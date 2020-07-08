package com.newer.task.web.control;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.newer.task.data.dao.IRoleDAO;
import com.newer.task.data.pojo.TbEmployee;
import com.newer.task.data.pojo.TbRole;
import com.newer.task.service.IEmployeeService;
import com.newer.task.web.util.EmployeeForm;

@Controller
@SessionAttributes("form")
public class LoginController {
	public LoginController() {
		System.out.println("登录控制器实例化LoginController");
	}

	@Resource(name = "employeeService")
	IEmployeeService employeeService;

	@Resource(name = "roleDao")
	IRoleDAO roleDAO;

	// 装填员工登录表单请求
	@RequestMapping(value = "/emp_input.do")
	public String inputEmps(Model model) {
		System.out.println("装填表单数据inputEmps");
		TbEmployee emps = new TbEmployee();// 表单员工空白对象
		TbRole role = new TbRole();// 表单角色空白对象

		EmployeeForm loginForm = new EmployeeForm();// 登录表单提交数据封装工具类
		loginForm.setEmp(emps);
		loginForm.setRole(role);

		// 查询所有角色：
		List<TbRole> allRoles = this.roleDAO.findAllRoles();
		model.addAttribute("form", loginForm);// 空白表单对象

		model.addAttribute("allroles", allRoles);// 给角色下拉列表装填数据

		return "login";// login.jsp->转发到登录页面
	}

	// 表单登录请求
	@RequestMapping(value = "/emplogin", method = RequestMethod.POST)
	public String empLogin(@ModelAttribute("form") EmployeeForm form,
			ModelMap map, HttpSession session) {
		String nextAction = "";// 跳转路径

		// 使用业务层验证登录：
		TbEmployee employee = employeeService.checkLogin(form.getEmp()
				.getEmployeeName(), form.getEmp().getPassword());// 查询员工表（用户名/密码）-〉外键形式存在RoleId
		System.out.println("员工信息为" + employee);
		if (employee != null) {
			// 验证用户成功（数据库存在员工记录）
			if (employee.getRole().getRoleId()
					.equals(form.getRole().getRoleId())) {// EmployeeForm表单提交的RoleID
				System.out.println("角色/用户名/密码");
				// 用户名和密码及其角色相一致的登录成功员工保存在会话范围内！！！
				session.setAttribute("employee", employee);
				String roleName = employee.getRole().getRoleName();
				if ("系统管理员".equals(roleName)) {
					nextAction = "redirect:/admin_login_success.do";
				} else if ("主管".equals(roleName)) {
					nextAction = "redirect:/manager_login_success.do";
				} else if ("员工".equals(roleName)) {
					System.out.println("普通员工");
					nextAction = "redirect:/employee_login_success.do";
				}
			} else {
				map.put("message", "该用户无此角色");
				nextAction = "error";
			}

		} else {
			map.put("message", "登录失败，用户名和密码不一致");
			nextAction = "error";
		}
		return nextAction;
	}

	// 注销请求
	@RequestMapping("loginOut.do")
	public String loginOut(HttpSession session, Model model) {
		// 注销业务 清空session中存储的数据
		session.invalidate();
		return this.inputEmps(model);
	}
}
