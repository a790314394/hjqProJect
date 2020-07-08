package com.newer.task.service;

import java.util.List;

import com.newer.task.data.pojo.TbPlan;
import com.newer.task.data.pojo.TbTask;
import com.newer.task.web.dto.PlanDto;
import com.newer.task.web.util.TaskPlan;

public interface IStaffService {

	public abstract List<TaskPlan> queryAllSTaffTaskPlanById(int id);

	// 组合查询/高级查询求出查询结果中记录总数
	public abstract int getTotalCountsByPlanAdvance(PlanDto dto,int id);

	// 高级查询分页
	public abstract List<TaskPlan> selectPageByPlanAdvance(int id,PlanDto dto,
			int pageNo, int pageSize);

	public abstract TbTask selectTask(int id);

	// 根据任务编号查询计划
	public List<TbPlan> selectByTaskId(Integer taskId);

	// 新增计划
	public abstract boolean insertplan(TbPlan tp);

	// 删除计划
	public abstract boolean deleteplan(Integer id);

	// 修改计划
	public abstract boolean updateplan(Integer id, TbPlan tp);

	// 根据计划编号查询计划信息
	public abstract TbPlan selectByPlanId(Integer planId);

	// 根据任务id修改任务状态
	public abstract boolean updateTask(TbTask tk);

	public abstract int selectallcountTaskandPlan(Integer employeeId);

	public abstract List<TaskPlan> selectAllTask(Integer employeeId);

	public abstract List<TaskPlan> selectallTaskPlan(Integer employeeId,
			int pageNo, int pageSize);
}
