package com.newer.task.data.dao;

import java.util.List;

import com.newer.task.data.pojo.TbTask;
import com.newer.task.web.util.TaskPlan;

//任务dao
public interface ITaskDAO {
	// 查询任务
	public abstract List<TaskPlan> selectAllTask(String sql, Object[] prams);

	// 新增任务
	public abstract int insertTask(TbTask task);

	// 修改任务
	public abstract int updateTask(TbTask task);

	// 修改任务状态
	public abstract int updateTaskInfo(Integer id);

	// 删除未实施任务信息
	public abstract int deleteTask(Integer id);

	// 多表连接查询
	public abstract List<TaskPlan> selectallTaskandPlan(int id);

	public abstract TbTask selectTask(int id);
	
	public abstract boolean updatetask(TbTask tk);

	public abstract int selectallcountTaskandPlan(Integer id);

	public abstract List<TaskPlan> selectallTaskandPlan(Integer id,
			Integer pageNo, Integer pageSize);

}
