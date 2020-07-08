package com.newer.task.web.util;

import java.util.List;

import com.newer.task.data.pojo.TbPlan;
import com.newer.task.data.pojo.TbTask;

public class TaskPlan {
	private TbTask task;// 任务信息
	private List<TbPlan> plans;// 计划信息

	public TbTask getTask() {
		return task;
	}

	public void setTask(TbTask task) {
		this.task = task;
	}

	public List<TbPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<TbPlan> plans) {
		this.plans = plans;
	}

	@Override
	public String toString() {
		return "TaskPlan [task=" + task + ", plans=" + plans + "]";
	}

	public TaskPlan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskPlan(TbTask task, List<TbPlan> plans) {
		super();
		this.task = task;
		this.plans = plans;
	}

}
