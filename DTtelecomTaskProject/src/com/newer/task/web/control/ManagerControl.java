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
	// ע��Ա��ҵ���
	IEmployeeService iEmployeeService;
	// ע������ҵ���
	@Resource(name = "taskService")
	ITaskService iTaskService;

	@RequestMapping("queryEmp.do")
	// �鿴������������Ա��
	public String queryEmpByManagerId(int id, HttpServletRequest request) {
		int pageNo = 1;// ��ʼΪ��һҳ
		int pageSize = 5;// ��СΪ5
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

	// ���ݱ�Ų�ѯԱ����Ϣ
	@RequestMapping("queryEmpInfo.do")
	public String queryEmpByEmpId(int employeeId, Model model) {
		// ����ҵ������Ա����Ų�ѯԱ����Ϣ�ķ���
		TbEmployee employee = iEmployeeService.queryEmpInfoByEmpId(employeeId);
		model.addAttribute("emp", employee);
		return "manager/personinfo";

	}

	// �������ܱ�Ų�ѯ���������������
	@RequestMapping("querytask.do")
	public String queryTask(int id, HttpServletRequest request) {
		System.out.println("���ܱ����" + id);
		int pageNo = 1;// ��ʼΪ��һҳ
		int pageSize = 5;// ��СΪ5
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
		System.out.println("��������");
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
		System.out.println("��������");
		System.out.println(task.toString());
		TbEmployee employee = new TbEmployee();
		employee.setEmployeeId(taskId);
		task.setTask(employee);
		// ��session��ȡ�����ܵ�id
		TbEmployee tbEmployee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		// ����ҵ�����������
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
		int pageNo = 1;// ��ʼΪ��һҳ
		int pageSize = 5;// ��СΪ5
		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		// ����ҵ���������ܱ�Ų�ѯ�������д�ʵʩ������
		List<TaskPlan> plans = this.iTaskService.queryUnimplementedTask(id,
				pageNo, pageSize);
		PageBean<TaskPlan> p = new PageBean<TaskPlan>();
		p.setData(plans);
		p.setPageSize(pageSize);
		p.setPageNo(pageNo);
		p.setTotalRecords(this.iTaskService.querydPageCount(id));
		p.setPageString();
		// ����ʵʩ�������session��
		request.getSession().setAttribute("plans", p);
		return "manager/taskundone";
	};

	// ����δʵʩ��������ת���޸Ľ����޸�������Ϣ
	@RequestMapping("updateTask.do")
	public String updateTaskInfo(Integer id, Model model,
			HttpServletRequest request) {

		// ��session��ȡ�����д�ʵʩ������
		PageBean<TaskPlan> plans = (PageBean<TaskPlan>) request.getSession()
				.getAttribute("plans");
		// ��������ȡ����Ӧ��������
		TaskPlan taskPlan = plans.getData().get(id);
		System.out.println("ȡ������ϢΪ" + taskPlan.getTask().toString());
		// ��������Χ��
		model.addAttribute("tsp", taskPlan);
		// ȡ��������������Ա��
		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		List<TbEmployee> emps = this.iEmployeeService.queryByManagerId(
				employee.getEmployeeId(), 1, 100);
		model.addAttribute("persons", emps);
		return "manager/adjust";
	}

	@RequestMapping("updateTaskInfo.do")
	// �޸�������Ϣ
	public String myupdateTaskInfo(@ModelAttribute("task") TbTask task,
			Integer employeeId) {
		System.out.println(task.getEndDate().toString());
		TbEmployee employee = new TbEmployee();
		employee.setEmployeeId(employeeId);
		task.setTask(employee);
		task.setEndDate(task.getEndDate());
		// �����޸ķ���
		int count = this.iTaskService.updateTaskInfo(task);
		if (count > 0) {
			return "redirect:/manager_login_success.do";
		} else {
			return "manager/task";

		}
	}

	@RequestMapping("trackingtask.do")
	public String trackingtask(Integer id, HttpServletRequest request) {
		int pageNo = 1;// ��ʼΪ��һҳ
		int pageSize = 5;// ��СΪ5
		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		// �������ܱ�Ų�ѯ����ʵʩ�е�����
		List<TaskPlan> list = this.iTaskService.queryAllimplementTask(id,
				pageNo, pageSize);
		PageBean<TaskPlan> p = new PageBean<TaskPlan>();
		p.setData(list);
		p.setPageSize(pageSize);
		p.setPageNo(pageNo);
		p.setTotalRecords(this.iTaskService.queryPageImplCount(id));
		p.setPageString();
		request.getSession().setAttribute("allTask", p);
		// ����ҵ���������ܱ�Ų�ѯ����ʵʩ�е�������Ϣ
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
		// ��session��ȡ������Աid
		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		System.out.println(employee);
		if (count > 0) {
			System.out.println("�޸ĳɹ�");
			return this.trackingtask(employee.getEmployeeId(), request);
		} else {
			System.out.println("�޸�ʧ��");
			return this.trackingtask(employee.getEmployeeId(), request);
		}

	}

	@RequestMapping("delete1.do")
	public String deleteTask(Integer taskId, HttpServletRequest request) {
		// ��session��ȡ������Աid
		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		int count = this.iTaskService.deleteNoTask(taskId);
		if (count > 0) {
			System.out.println("ɾ���ɹ�");
			return this.queryUnimplementedTask(employee.getEmployeeId(),
					request);
		} else {
			return this.queryUnimplementedTask(employee.getEmployeeId(),
					request);
		}

	}

}
