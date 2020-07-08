package com.newer.task.web.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newer.task.data.pojo.TbEmployee;
import com.newer.task.data.pojo.TbPlan;
import com.newer.task.data.pojo.TbTask;
import com.newer.task.data.util.PageBean;
import com.newer.task.data.util.StringUtil;
import com.newer.task.service.IStaffService;
import com.newer.task.web.dto.PlanDto;
import com.newer.task.web.util.TaskPlan;

@Controller
public class StaffController {

	@Resource(name = "staffservice")
	IStaffService staffservice;

	@RequestMapping(value = "/employee_login_success.do")
	public String welcome(Model model) {

		return "/person/welcome";
	}

	@RequestMapping(value = "task.do")
	public String queryTask(HttpServletRequest request) {

		int pageNo = 1;
		int pageSize = 3;

		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));

		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");

		List<TaskPlan> tp = staffservice.selectallTaskPlan(
				employee.getEmployeeId(), pageNo, pageSize);

		PageBean<TaskPlan> pb = new PageBean<TaskPlan>();

		pb.setData(tp);
		pb.setPageSize(pageSize);
		pb.setPageNo(pageNo);

		pb.setTotalRecords(staffservice.selectallcountTaskandPlan(employee
				.getEmployeeId()));

		System.out.println(tp.toString());
		request.getSession().setAttribute("taskplan", pb);

		return "/person/task";
	}

	@RequestMapping(value = "checkpro.do")
	public String querycheckpro(HttpServletRequest request) {
		int pageNo = 1;
		int pageSize = 5;

		PlanDto dto = null;

		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");

		List<TaskPlan> tp = staffservice
				.selectAllTask(employee.getEmployeeId());

		request.getSession().setAttribute("taskplan", tp);

		List<TaskPlan> all = staffservice.selectPageByPlanAdvance(
				employee.getEmployeeId(), dto, pageNo, pageSize);
		PageBean<TaskPlan> pb = new PageBean<TaskPlan>();
		for (TaskPlan taskPlan : all) {
			for (TbPlan p : taskPlan.getPlans()) {
				System.out.println("计划" + p);
			}
		}
		pb.setData(all);
		pb.setPageSize(pageSize);
		pb.setPageNo(pageNo);

		pb.setTotalRecords(staffservice.getTotalCountsByPlanAdvance(dto,
				employee.getEmployeeId()));

		System.out.println(all.toString());
		request.getSession().setAttribute("plan_pb", pb);

		return "/person/checkpro";
	}

	@RequestMapping(value = "pro.do")
	public String query(HttpServletRequest request) throws ParseException {
		int pageNo = 1;
		int pageSize = 5;

		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));

		PlanDto dto = null;

		if ("1".equals(request.getParameter("flag"))) {// 查询按钮，重新封装dto
			dto = new PlanDto();
			if (!StringUtil.isBlank(request.getParameter("search_planName"))) {

				dto.setSearch_planName(request.getParameter("search_planName"));
			}
			if (!StringUtil.isBlank(request.getParameter("select"))) {

				dto.setSearch_taskName(Integer.parseInt(request
						.getParameter("select")));

			}

			if (!StringUtil.isBlank(request.getParameter("search_begin_date1"))
					&& !StringUtil.isBlank(request
							.getParameter("search_begin_date2"))) {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = sdf.parse(request
						.getParameter("search_begin_date1"));
				Date date2 = sdf1.parse(request
						.getParameter("search_begin_date2"));
				dto.setSearch_begin_date1(date1);
				dto.setSearch_begin_date2(date2);

			}
			if (!StringUtil.isBlank(request.getParameter("search_end_date1"))
					&& !StringUtil.isBlank(request
							.getParameter("search_end_date2"))) {

				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = sdf1.parse(request
						.getParameter("search_end_date1"));
				Date date2 = sdf2.parse(request
						.getParameter("search_end_date2"));
				dto.setSearch_end_date1(date1);
				dto.setSearch_end_date2(date2);

			}
			if (!StringUtil.isBlank(request.getParameter("search_info"))) {
				dto.setFeedback_info(request.getParameter("search_info"));
			}

			request.getSession().setAttribute("plan_dto", dto);
		} else {// 超链接，从session中取
			dto = (PlanDto) request.getSession().getAttribute("plan_dto");
		}
		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");

		List<TaskPlan> tp = staffservice
				.selectAllTask(employee.getEmployeeId());

		request.getSession().setAttribute("taskplan", tp);

		List<TaskPlan> all = staffservice.selectPageByPlanAdvance(
				employee.getEmployeeId(), dto, pageNo, pageSize);
		PageBean<TaskPlan> pb = new PageBean<TaskPlan>();
		for (TaskPlan taskPlan : all) {
			for (TbPlan p : taskPlan.getPlans()) {
				System.out.println("计划" + p);
			}
		}
		pb.setData(all);
		pb.setPageSize(pageSize);
		pb.setPageNo(pageNo);

		pb.setTotalRecords(staffservice.getTotalCountsByPlanAdvance(dto,
				employee.getEmployeeId()));

		System.out.println(all.toString());
		request.getSession().setAttribute("plan_pb", pb);
		return "/person/checkpro";
	}

	@RequestMapping(value = "taskview.do")
	public String queryTaskview(HttpServletRequest request) {

		int pageNo = 1;
		int pageSize = 2;

		PlanDto dto = new PlanDto();
		;

		if (!StringUtil.isBlank(request.getParameter("pageNo")))
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		if (!StringUtil.isBlank(request.getParameter("pageSize")))
			pageSize = Integer.parseInt(request.getParameter("pageSize"));

		if (!StringUtil.isBlank(request.getParameter("radiobutton"))) {

			TbEmployee employee = (TbEmployee) request.getSession()
					.getAttribute("employee");

			int id = Integer.parseInt(request.getParameter("radiobutton"));

			System.out.println("任务id是:" + id);
			dto.setSearch_taskName(id);

			TbTask ta = staffservice.selectTask(id);

			request.getSession().setAttribute("taskp", ta);

			List<TaskPlan> all = staffservice.selectPageByPlanAdvance(
					employee.getEmployeeId(), dto, pageNo, pageSize);

			PageBean<TaskPlan> pd = new PageBean<TaskPlan>();

			pd.setData(all);
			pd.setPageNo(pageNo);
			pd.setPageSize(pageSize);
			pd.setTotalRecords(staffservice.getTotalCountsByPlanAdvance(dto,
					employee.getEmployeeId()));

			request.getSession().setAttribute("plan", pd);

		} else {

			TbEmployee employee = (TbEmployee) request.getSession()
					.getAttribute("employee");

			TbTask tk = (TbTask) request.getSession().getAttribute("taskp");
			dto.setSearch_taskName(tk.getTaskId());

			List<TaskPlan> all = staffservice.selectPageByPlanAdvance(
					employee.getEmployeeId(), dto, pageNo, pageSize);

			PageBean<TaskPlan> pd = new PageBean<TaskPlan>();
			pd.setData(all);
			pd.setPageNo(pageNo);
			pd.setPageSize(pageSize);
			pd.setTotalRecords(staffservice.getTotalCountsByPlanAdvance(dto,
					employee.getEmployeeId()));
			request.getSession().setAttribute("plan", pd);
		}

		return "/person/taskview";
	}

	@RequestMapping(value = "newpro.do")
	public String insertplan(HttpServletRequest request) throws ParseException {

		int pageNo = 1;
		int pageSize = 2;

		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		TbPlan tp = new TbPlan();
		String id = request.getParameter("taskid");
		String name = request.getParameter("planName");
		String begindate = request.getParameter("beginDate");
		String enddate = request.getParameter("endDate");
		String desc = request.getParameter("planDesc");
		tp.setTaskId(Integer.parseInt(id));
		tp.setPlanName(name);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(begindate);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf1.parse(enddate);
		tp.setBeginDate(date);
		tp.setEndDate(date1);
		tp.setPlanDesc(desc);

		boolean issucc = staffservice.insertplan(tp);

		if (issucc) {

			PlanDto dto = new PlanDto();
			dto.setSearch_taskName(Integer.parseInt(id));

			List<TaskPlan> all = staffservice.selectPageByPlanAdvance(
					employee.getEmployeeId(), dto, pageNo, pageSize);

			PageBean<TaskPlan> pd = new PageBean<TaskPlan>();

			pd.setData(all);
			pd.setPageNo(pageNo);
			pd.setPageSize(pageSize);
			pd.setTotalRecords(staffservice.getTotalCountsByPlanAdvance(dto,
					employee.getEmployeeId()));

			request.getSession().setAttribute("plan", pd);

			return "/person/taskview";

		} else {

			return null;
		}

	}

	@RequestMapping(value = "delete2.do")
	public String deleteplan(HttpServletRequest request) throws ParseException {
		int pageNo = 1;
		int pageSize = 2;

		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");

		String id = request.getParameter("taskid");
		String planid = request.getParameter("radiobutton");

		boolean issucc = staffservice.deleteplan(Integer.parseInt(planid));

		if (issucc) {

			PlanDto dto = new PlanDto();
			dto.setSearch_taskName(Integer.parseInt(id));

			List<TaskPlan> all = staffservice.selectPageByPlanAdvance(
					employee.getEmployeeId(), dto, pageNo, pageSize);

			PageBean<TaskPlan> pd = new PageBean<TaskPlan>();
			pd.setData(all);
			pd.setPageNo(pageNo);
			pd.setPageSize(pageSize);
			pd.setTotalRecords(staffservice.getTotalCountsByPlanAdvance(dto,
					employee.getEmployeeId()));

			request.getSession().setAttribute("plan", pd);

			return "/person/taskview";

		} else {

			return null;

		}

	}

	@RequestMapping(value = "selectplan.do")
	public String selectplan(HttpServletRequest request) {

		if (!StringUtil.isBlank(request.getParameter("taskid"))) {

			String taskid = request.getParameter("taskid");

			TbTask ta = staffservice.selectTask(Integer.parseInt(taskid));

			request.getSession().setAttribute("taskp", ta);
		}

		String planid = request.getParameter("planid");

		TbPlan tp = staffservice.selectByPlanId(Integer.parseInt(planid));

		request.getSession().setAttribute("pl", tp);

		return "/person/feedback";
	}

	@RequestMapping(value = "update1.do")
	public String updateplan(HttpServletRequest request) throws ParseException {

		int pageNo = 1;
		int pageSize = 2;
		TbEmployee employee = (TbEmployee) request.getSession().getAttribute(
				"employee");
		System.out.println("员工编号" + employee.getEmployeeId());
		System.out.println("进来了吗update1.do");
		TbPlan tp = new TbPlan();

		String id = request.getParameter("planid");
		String taskid = request.getParameter("taskid");
		System.out.println(id);
		String status = request.getParameter("select");
		String isFeedBack = request.getParameter("select2");
		String feedBackInfo = request.getParameter("textarea");

		tp.setStatus(status);
		tp.setIsFeedBack(isFeedBack);
		tp.setFeedBackInfo(feedBackInfo);

		boolean issucc = staffservice.updateplan(Integer.parseInt(id), tp);

		if (issucc) {

			PlanDto dto = new PlanDto();
			dto.setSearch_taskName(Integer.parseInt(taskid));

			List<TaskPlan> all = staffservice.selectPageByPlanAdvance(
					employee.getEmployeeId(), dto, pageNo, pageSize);
			System.out.println("all里面有只吗" + all);
			PageBean<TaskPlan> pd = new PageBean<TaskPlan>();

			pd.setData(all);
			pd.setPageNo(pageNo);
			pd.setPageSize(pageSize);
			pd.setTotalRecords(staffservice.getTotalCountsByPlanAdvance(dto,
					employee.getEmployeeId()));

			request.getSession().setAttribute("plan", pd);

			return "/person/taskview";

		} else {

			return null;

		}

	}

	@RequestMapping(value = "updatetask.do")
	public String updatetask(HttpServletRequest request) throws ParseException {

		TbTask tk = new TbTask();

		String id = request.getParameter("taskid");
		String status = request.getParameter("select");

		tk.setStatus(status);
		tk.setTaskId(Integer.parseInt(id));

		boolean issucc = staffservice.updateTask(tk);

		if (issucc) {

			TbTask ta = staffservice.selectTask(Integer.parseInt(id));

			request.getSession().setAttribute("taskp", ta);
			return "/person/taskview";

		} else {

			return null;

		}

	}
}
