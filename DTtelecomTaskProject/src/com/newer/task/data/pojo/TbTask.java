package com.newer.task.data.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TbTask {
	private Integer taskId;// 任务编号
	private String taskName;// 任务名称
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate;// 开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;// 结束时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date realBeginDate;// 实际开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date realEndDate;// 实际结束时间

	private String status;// 任务状态

	private TbEmployee task;// 实施人
	private Integer assignerId;// 分配人编号
	private String taskDesc;// 任务描述

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getRealBeginDate() {
		return realBeginDate;
	}

	public void setRealBeginDate(Date realBeginDate) {
		this.realBeginDate = realBeginDate;
	}

	public Date getRealEndDate() {
		return realEndDate;
	}

	public void setRealEndDate(Date realEndDate) {
		this.realEndDate = realEndDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TbEmployee getTask() {
		return task;
	}

	public void setTask(TbEmployee task) {
		this.task = task;
	}

	public Integer getAssignerId() {
		return assignerId;
	}

	public void setAssignerId(Integer assignerId) {
		this.assignerId = assignerId;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	@Override
	public String toString() {
		return "TbTask [taskId=" + taskId + ", taskName=" + taskName
				+ ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", realBeginDate=" + realBeginDate + ", realEndDate="
				+ realEndDate + ", status=" + status + ", task=" + task
				+ ", assignerId=" + assignerId + ", taskDesc=" + taskDesc + "]";
	}

	public TbTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TbTask(Integer taskId, String taskName, Date beginDate,
			Date endDate, Date realBeginDate, Date realEndDate, String status,
			TbEmployee task, Integer assignerId, String taskDesc) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.realBeginDate = realBeginDate;
		this.realEndDate = realEndDate;
		this.status = status;
		this.task = task;
		this.assignerId = assignerId;
		this.taskDesc = taskDesc;
	}

}
