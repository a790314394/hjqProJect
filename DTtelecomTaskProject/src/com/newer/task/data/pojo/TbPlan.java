package com.newer.task.data.pojo;

import java.util.Date;

public class TbPlan {
	private Integer planId;// 计划编号
	private String planName;// 计划名称
	private String status;// 计划状态
	private String isFeedBack;// 是否反馈
	private Date beginDate;// 开始时间
	private Date endDate;// 结束时间
	private Integer taskId;// 所属任务
	private String feedBackInfo;// 反馈信息
	private String planDesc;// 计划描述

	public TbPlan(Integer planId, String planName, String status,
			String isFeedBack, Date beginDate, Date endDate, Integer taskId,
			String feedBackInfo, String planDesc) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.status = status;
		this.isFeedBack = isFeedBack;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.taskId = taskId;
		this.feedBackInfo = feedBackInfo;
		this.planDesc = planDesc;
	}

	public TbPlan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsFeedBack() {
		return isFeedBack;
	}

	public void setIsFeedBack(String isFeedBack) {
		this.isFeedBack = isFeedBack;
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

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getFeedBackInfo() {
		return feedBackInfo;
	}

	public void setFeedBackInfo(String feedBackInfo) {
		this.feedBackInfo = feedBackInfo;
	}

	public String getPlanDesc() {
		return planDesc;
	}

	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
	}

	@Override
	public String toString() {
		return "TbPlan [planId=" + planId + ", planName=" + planName
				+ ", status=" + status + ", isFeedBack=" + isFeedBack
				+ ", beginDate=" + beginDate + ", endDate=" + endDate
				+ ", taskId=" + taskId + ", feedBackInfo=" + feedBackInfo
				+ ", planDesc=" + planDesc + "]";
	}

}
