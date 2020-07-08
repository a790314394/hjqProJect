package com.newer.task.service;

import java.util.List;

import com.newer.task.data.pojo.TbPlan;
import com.newer.task.data.pojo.TbTask;
import com.newer.task.web.util.TaskPlan;

public interface ITaskService {
	// 根据主管编号查询主管所管理的任务
	public abstract List<TaskPlan> queryAllTaskById(int id);

	// 根据任务编号查询所有的计划
	public abstract List<TbPlan> queryAllPlanById(Integer id);

	// 根据计划编号查询计划详细信息
	public abstract TbPlan queryPlanById(Integer id);

	// 新增任务

	public abstract int addTask(TbTask task);

	// 根据主管编号查询所有状态为未实施的任务
	public abstract List<TaskPlan> queryUnimplementedTask(Integer taskId,int pageNo, int pageSize);

	// 修改任务信息
	public abstract int updateTaskInfo(TbTask task);

	// 根据主管编号查询所有正在实施中的任务
	public abstract List<TaskPlan> queryAllimplementTask(Integer id,
			int pageNo, int pageSize);

	// 根据编号修改任务信息
	public abstract int updateTaskInfos(Integer id);

	// 根据编号删除未实施的任务

	public abstract int deleteNoTask(Integer id);

	// 根据主管编号查询主管所管理的任务(分页)
	public abstract List<TaskPlan> queryPageAllTaskById(int id, int pageNo,
			int pageSize);

	// 根据主管编号查询主管所管理的任务总数(分页)
	public abstract int queryPageCount(int id);
	
	// 根据主管编号查询主管所管理的待实施任务总数(分页)
		public abstract int querydPageCount(int id);

	// 根据主管编号查询主管所管理实施中的任务总数(分页)
	public abstract int queryPageImplCount(int id);

}
