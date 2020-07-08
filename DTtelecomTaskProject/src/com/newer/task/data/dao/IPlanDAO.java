package com.newer.task.data.dao;

import java.util.List;

import com.newer.task.data.pojo.TbPlan;
import com.newer.task.web.dto.PlanDto;
import com.newer.task.web.util.TaskPlan;

public interface IPlanDAO {
	// 查询所有计划
	public List<TbPlan> selectAll(String sql, Object[] parms);

	// 根据任务编号查询计划
	public List<TbPlan> selectByTaskId(Integer taskId);

	// 根据计划编号查询计划信息 ***
	public TbPlan selectByPlanId(Integer planId);

	// 新增计划
	public abstract boolean insertplan(TbPlan tp);

	// 删除计划
	public abstract boolean deleteplan(Integer id);

	// 修改计划
	public abstract boolean updateplan(Integer id, TbPlan tp);

	// 组合查询/高级查询求出查询结果中记录总数
	public abstract int getTotalCountsByPlanAdvance(PlanDto dto);

	// 高级查询分页
	public abstract List<TaskPlan> selectPageByPlanAdvance(int id, PlanDto dto,
			int pageNo, int pageSize);

	public int getCountsPageBenplan(Integer id);

	public List<TbPlan> selectPageBenplan(Integer id, int pageNo, int pageSize);

	// 组合查询/高级查询求出查询结果中记录总数
	public abstract int getTotalCountsByPlanAdvance(PlanDto dto, int id);

}
