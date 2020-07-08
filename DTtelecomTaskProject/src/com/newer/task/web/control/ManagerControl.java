package com.newer.task.web.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newer.task.data.pojo.TbEmployee;
import com.newer.task.data.pojo.TbPlan;
import com.newer.task.data.pojo.TbTask;
import com.newer.task.data.util.PageBean;
import com.newer.task.data.util.StringUtil;
import com.newer.task.service.IEmployeeService;
import com.newer.task.service.ITaskService;
import com.newer.task.web.util.TaskPlan;

@Controller
public class ManagerControl {
	@Resource(name = "employeeService")
	// 注入员工业务层
	IEmployeeService iEmployeeService;
	// 注入任务业务层
	@Resource(name = "taskService")
	ITaskService iTaskService;

	@RequestMapping("queryEmp.do")
	// 查看主管旗下所有员工
	public String queryEmpByManagerId(int id, HttpServletRequest request) {
		int pageNo = 1;// 初始为第一页
		int pageSize = 5;// 大小为5
		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		List<TbEmployee> list = iEmployeeService.queryByManagerId(id, pageNo,
				pageSize);
		PageBean<TbEmployee> p = new PageBean<TbEmployee>();
		p.setData(list);
		p.setPageSize(pageSize);
		p.setPageNo(pageNo);
		p.setTotalRecords(this.iEmployeeService.queryByManagerIdCount(id));
		p.setPageString();
		request.getSession().setAttribute("emps", p);
		System.out.println(list);
		return "manager/checkper";
	};

	// 根据编号查询员工信息
	@RequestMapping("queryEmpInfo.do")
	public String queryEmpByEmpId(int employeeId, Model model) {
		// 调用业务层根据员工编号查询员工信息的方法
		TbEmployee employee = iEmployeeService.queryEmpInfoByEmpId(employeeId);
		model.addAttribute("emp", employee);
		return "manager/personinfo";

	}

	// 根据主管编号查询主管所管理的任务
	@RequestMapping("querytask.do")
	public String queryTask(int id, HttpServletRequest request) {
		System.out.println("主管编号是" + id);
		int pageNo = 1;// 初始为第一页
		int pageSize = 5;// 大小为5
		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		List<TaskPlan> allTask = this.iTaskService.queryPageAllTaskById(id,
				pageNo, pageSize);
		PageBean<TaskPlan> p = new PageBean<TaskPlan>();
		p.setData(allTask);
		p.setPageSize(pageSize);
		p.setPageNo(pageNo);
		p.setTotalRecords(this.iTaskService.queryPageCount(id));
		p.setPageString();
		System.out.println(allTask);
		request.getSession().setAttribute("allTask", p);
		return "manager/taskview";
	}

	@RequestMapping("showPlan.do")
	public String showPlan(Integer taskId, HttpServletRequest request) {
		System.out.println("进来了吗");
		PageBean<TaskPlan> p = (PageBean<TaskPlan>) request.getSession()
				.getAttribute("allTask");
		List<TaskPlan> allTp = p.getData();
		TaskPlan tp = null;
		for (TaskPlan taskPlan : allTp) {
			if (taskPlan.getTask().getTaskId() == taskId) {
				tp = taskPlan;
			}
		}
		// TaskPlan tp = p.getData().get(taskId);
		System.out.println(tp);
		request.setAttribute("tp", tp);
		return "manager/taskparticular";

	}

	@RequestMapping("showPlanInfo.do")
	public String showPlanInfo(Integer planId, Model model) {
		TbPlan plan = this.iTaskService.queryPlanById(planId);
		model.addAttribute("plan", plan);
		return "manager/program";
	}

	@RequestMapping("insertTask.do")
	public String insertTask(HttpServletRequest request) {
		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");

		List<TbEmployee> list = this.iEmployeeService.queryByManagerId(
				employee.getEmployeeId(), 1, 100);
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (TbEmployee tbEmployee : list) {
			map.put(tbEmployee.getEmployeeId(), tbEmployee.getRealName());
		}
		request.getSession().setAttribute("EmpMap", map);
		return "manager/task";
	}

	@RequestMapping("insertTaskInfo.do")
	public String insertTaskInfo(@ModelAttribute("task") TbTask task,
			Integer taskId, HttpServletRequest request) {
		System.out.println("新增任务");
		System.out.println(task.toString());
		TbEmployee employee = new TbEmployee();
		employee.setEmployeeId(taskId);
		task.setTask(employee);
		// 从session中取出主管的id
		TbEmployee tbEmployee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		// 调用业务层新增方法
		task.setAssignerId(tbEmployee.getEmployeeId());
		int count = this.iTaskService.addTask(task);
		if (count > 0) {
			return "manager/welcome";
		} else {

			return null;
		}

	}

	@RequestMapping("queryNotask.do")
	public String queryUnimplementedTask(Integer id, HttpServletRequest request) {
		int pageNo = 1;// 初始为第一页
		int pageSize = 5;// 大小为5
		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		// 调用业务层根据主管编号查询主管所有待实施的任务
		List<TaskPlan> plans = this.iTaskService.queryUnimplementedTask(id,
				pageNo, pageSize);
		PageBean<TaskPlan> p = new PageBean<TaskPlan>();
		p.setData(plans);
		p.setPageSize(pageSize);
		p.setPageNo(pageNo);
		p.setTotalRecords(this.iTaskService.querydPageCount(id));
		p.setPageString();
		// 将待实施任务存入session中
		request.getSession().setAttribute("plans", p);
		return "manager/taskundone";
	};

	// 根据未实施的任务跳转的修改界面修改任务信息
	@RequestMapping("updateTask.do")
	public String updateTaskInfo(Integer id, Model model,
			HttpServletRequest request) {

		// 从session中取出所有待实施的任务
		PageBean<TaskPlan> plans = (PageBean<TaskPlan>) request.getSession()
				.getAttribute("plans");
		// 根据索引取出对应的任务编号
		TaskPlan taskPlan = plans.getData().get(id);
		System.out.println("取出的信息为" + taskPlan.getTask().toString());
		// 存入请求范围中
		model.addAttribute("tsp", taskPlan);
		// 取出主管旗下所有员工
		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		List<TbEmployee> emps = this.iEmployeeService.queryByManagerId(
				employee.getEmployeeId(), 1, 100);
		model.addAttribute("persons", emps);
		return "manager/adjust";
	}

	@RequestMapping("updateTaskInfo.do")
	// 修改任务信息
	public String myupdateTaskInfo(@ModelAttribute("task") TbTask task,
			Integer employeeId) {
		System.out.println(task.getEndDate().toString());
		TbEmployee employee = new TbEmployee();
		employee.setEmployeeId(employeeId);
		task.setTask(employee);
		task.setEndDate(task.getEndDate());
		// 调用修改方法
		int count = this.iTaskService.updateTaskInfo(task);
		if (count > 0) {
			return "redirect:/manager_login_success.do";
		} else {
			return "manager/task";

		}
	}

	@RequestMapping("trackingtask.do")
	public String trackingtask(Integer id, HttpServletRequest request) {
		int pageNo = 1;// 初始为第一页
		int pageSize = 5;// 大小为5
		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		// 根据主管编号查询所有实施中的任务
		List<TaskPlan> list = this.iTaskService.queryAllimplementTask(id,
				pageNo, pageSize);
		PageBean<TaskPlan> p = new PageBean<TaskPlan>();
		p.setData(list);
		p.setPageSize(pageSize);
		p.setPageNo(pageNo);
		p.setTotalRecords(this.iTaskService.queryPageImplCount(id));
		p.setPageString();
		request.getSession().setAttribute("allTask", p);
		// 调用业务层根据主管编号查询所有实施中的任务信息
		return "manager/intendance";

	}

	@RequestMapping("show.do")
	public String queryImplmentTaskInfo(int taskId, HttpServletRequest request) {
		System.out.println(taskId);
		PageBean<TaskPlan> p = (PageBean<TaskPlan>) request.getSession()
				.getAttribute("allTask");
		TaskPlan tp = p.getData().get(taskId);
		System.out.println(tp);
		request.setAttribute("p", tp);
		return "manager/programinten";
	}

	@RequestMapping("submit.do")
	public String submitTask(Integer id, HttpServletRequest request) {
		int count = this.iTaskService.updateTaskInfos(id);
		// 从session中取出管理员id
		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		System.out.println(employee);
		if (count > 0) {
			System.out.println("修改成功");
			return this.trackingtask(employee.getEmployeeId(), request);
		} else {
			System.out.println("修改失败");
			return this.trackingtask(employee.getEmployeeId(), request);
		}

	}

	@RequestMapping("delete1.do")
	public String deleteTask(Integer taskId, HttpServletRequest request) {
		// 从session中取出管理员id
		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		int count = this.iTaskService.deleteNoTask(taskId);
		if (count > 0) {
			System.out.println("删除成功");
			return this.queryUnimplementedTask(employee.getEmployeeId(),
					request);
		} else {
			return this.queryUnimplementedTask(employee.getEmployeeId(),
					request);
		}

	}

}
