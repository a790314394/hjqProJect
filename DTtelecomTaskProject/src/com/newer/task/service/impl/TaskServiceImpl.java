package com.newer.task.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.newer.task.data.dao.IPlanDAO;
import com.newer.task.data.dao.ITaskDAO;
import com.newer.task.data.pojo.TbPlan;
import com.newer.task.data.pojo.TbTask;
import com.newer.task.service.ITaskService;
import com.newer.task.web.util.TaskPlan;

@Service("taskService")
public class TaskServiceImpl implements ITaskService {
	@Resource(name = "taskDao")
	ITaskDAO iTaskDAO;

	@Resource(name = "planDao")
	IPlanDAO iPlanDAO;

	@Override
	public List<TaskPlan> queryAllTaskById(int id) {
		String sql = "select * from t_task where ASSIGNER_ID=?";
		List<TaskPlan> list = this.iTaskDAO.selectAllTask(sql,
				new Object[] { id });
		System.out.println(list);
		return list;
	}

	@Override
	public List<TbPlan> queryAllPlanById(Integer id) {
		// TODO Auto-generated method stub
		return iPlanDAO.selectByTaskId(id);
	}

	@Override
	public TbPlan queryPlanById(Integer id) {

		return this.iPlanDAO.selectByPlanId(id);
	}

	@Override
	public int addTask(TbTask task) {

		return this.iTaskDAO.insertTask(task);
	}

	@Override
	public List<TaskPlan> queryUnimplementedTask(Integer taskId, int pageNo,
			int pageSize) {
		String sql = "select * from (select row_number() over(order by TASK_ID) no,t.* from t_task t where t.ASSIGNER_ID=? and STATUS='待实施' ) temp where temp.no between ? and ?";
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;
		return this.iTaskDAO.selectAllTask(sql, new Object[] { taskId, start,
				end });
	}

	@Override
	public int updateTaskInfo(TbTask task) {
		// 修改任务信息
		return this.iTaskDAO.updateTask(task);
	}

	@Override
	public List<TaskPlan> queryAllimplementTask(Integer id, int pageNo,
			int pageSize) {
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;
		return this.iTaskDAO
				.selectAllTask(
						"select * from (select row_number() over(order by TASK_ID) no,t.* from t_task t where t.ASSIGNER_ID=? and STATUS='实施中' ) temp where temp.no between ? and ?",
						new Object[] { id, start, end });
	}

	@Override
	public int updateTaskInfos(Integer id) {

		return this.iTaskDAO.updateTaskInfo(id);
	}

	@Override
	public int deleteNoTask(Integer id) {

		return this.iTaskDAO.deleteTask(id);
	}

	@Override
	public List<TaskPlan> queryPageAllTaskById(int id, int pageNo, int pageSize) {
		String sql = "select * from (select row_number() over(order by TASK_ID) no,t.* from t_task t where t.ASSIGNER_ID=? ) temp where temp.no between ? and ?";
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;
		List<TaskPlan> list = this.iTaskDAO.selectAllTask(sql, new Object[] {
				id, start, end });
		return list;
	}

	@Override
	public int queryPageCount(int id) {
		String sql = "select * from t_task where ASSIGNER_ID=?";
		List<TaskPlan> list = this.iTaskDAO.selectAllTask(sql,
				new Object[] { id });
		System.out.println(list);
		return list.size();
	}

	@Override
	public int querydPageCount(int id) {
		String sql = "select * from t_task where ASSIGNER_ID=? and STATUS='待实施'";
		List<TaskPlan> list = this.iTaskDAO.selectAllTask(sql,
				new Object[] { id });
		System.out.println(list);
		return list.size();
	}

	@Override
	public int queryPageImplCount(int id) {
		String sql = "select * from t_task where ASSIGNER_ID=? and STATUS='实施中'";
		List<TaskPlan> list = this.iTaskDAO.selectAllTask(sql,
				new Object[] { id });
		System.out.println(list);
		return list.size();
	}
}
