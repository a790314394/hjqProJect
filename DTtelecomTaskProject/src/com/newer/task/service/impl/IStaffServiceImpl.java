package com.newer.task.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newer.task.data.dao.IPlanDAO;
import com.newer.task.data.dao.ITaskDAO;
import com.newer.task.data.pojo.TbPlan;
import com.newer.task.data.pojo.TbTask;
import com.newer.task.service.IStaffService;
import com.newer.task.web.dto.PlanDto;
import com.newer.task.web.util.TaskPlan;

@Service("staffservice")
public class IStaffServiceImpl implements IStaffService {

	@Resource(name = "taskDao")
	ITaskDAO iTaskDAO;

	@Resource(name = "planDao")
	IPlanDAO iPlanDAO;

	public List<TaskPlan> queryAllSTaffTaskPlanById(int id) {

		List<TaskPlan> all = iTaskDAO.selectallTaskandPlan(id);

		return all;
	}

	public int getTotalCountsByPlanAdvance(PlanDto dto, int id) {

		int count = iPlanDAO.getTotalCountsByPlanAdvance(dto, id);

		return count;
	}

	public List<TaskPlan> selectPageByPlanAdvance(int id, PlanDto dto,
			int pageNo, int pageSize) {

		List<TaskPlan> all = iPlanDAO.selectPageByPlanAdvance(id, dto, pageNo,
				pageSize);

		return all;
	}

	public TbTask selectTask(int id) {

		return iTaskDAO.selectTask(id);

	}

	public List<TbPlan> selectByTaskId(Integer taskId) {

		return iPlanDAO.selectByTaskId(taskId);

	}

	public boolean insertplan(TbPlan tp) {

		return iPlanDAO.insertplan(tp);

	}

	public boolean deleteplan(Integer id) {

		return iPlanDAO.deleteplan(id);
	}

	public TbPlan selectByPlanId(Integer planId) {
		return iPlanDAO.selectByPlanId(planId);

	}

	public boolean updateplan(Integer id, TbPlan tp) {

		return iPlanDAO.updateplan(id, tp);
	}

	public boolean updateTask(TbTask tk) {

		return iTaskDAO.updatetask(tk);
	}

	public int selectallcountTaskandPlan(Integer id) {

		return iTaskDAO.selectallcountTaskandPlan(id);
	}

	public List<TaskPlan> selectallTaskPlan(Integer id, Integer pageNo,
			Integer pageSize) {

		return iTaskDAO.selectallTaskandPlan(id, pageNo, pageSize);
	}

	public List<TaskPlan> selectAllTask(Integer id) {
		String sql = "select*from t_task where IMPLEMENTOR_ID=?";

		return iTaskDAO.selectAllTask(sql, new Object[] { id });
	}

	public List<TaskPlan> selectallTaskPlan(Integer employeeId, int pageNo,
			int pageSize) {
		return iTaskDAO.selectallTaskandPlan(employeeId, pageNo, pageSize);
	}

}
