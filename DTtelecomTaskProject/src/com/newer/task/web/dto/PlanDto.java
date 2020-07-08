package com.newer.task.web.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PlanDto {
	
	private int search_planId;
	//计划名字
	private String search_planName;
	//任务id
	private int search_taskName;
	//开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date search_begin_date1;
	//开始时间结束
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date search_begin_date2;
	//结束时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date search_end_date1;
	//结束时间结束
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date search_end_date2;
	//反馈
	private String feedback_info;
	public PlanDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlanDto(int search_planId, String search_planName, int search_taskName, Date search_begin_date1,
			Date search_begin_date2, Date search_end_date1, Date search_end_date2, String feedback_info) {
		super();
		this.search_planId = search_planId;
		this.search_planName = search_planName;
		this.search_taskName = search_taskName;
		this.search_begin_date1 = search_begin_date1;
		this.search_begin_date2 = search_begin_date2;
		this.search_end_date1 = search_end_date1;
		this.search_end_date2 = search_end_date2;
		this.feedback_info = feedback_info;
	}
	public int getSearch_planId() {
		return search_planId;
	}
	public void setSearch_planId(int search_planId) {
		this.search_planId = search_planId;
	}
	public String getSearch_planName() {
		return search_planName;
	}
	public void setSearch_planName(String search_planName) {
		this.search_planName = search_planName;
	}
	public int getSearch_taskName() {
		return search_taskName;
	}
	public void setSearch_taskName(int search_taskName) {
		this.search_taskName = search_taskName;
	}
	public Date getSearch_begin_date1() {
		return search_begin_date1;
	}
	public void setSearch_begin_date1(Date search_begin_date1) {
		this.search_begin_date1 = search_begin_date1;
	}
	public Date getSearch_begin_date2() {
		return search_begin_date2;
	}
	public void setSearch_begin_date2(Date search_begin_date2) {
		this.search_begin_date2 = search_begin_date2;
	}
	public Date getSearch_end_date1() {
		return search_end_date1;
	}
	public void setSearch_end_date1(Date search_end_date1) {
		this.search_end_date1 = search_end_date1;
	}
	public Date getSearch_end_date2() {
		return search_end_date2;
	}
	public void setSearch_end_date2(Date search_end_date2) {
		this.search_end_date2 = search_end_date2;
	}
	public String getFeedback_info() {
		return feedback_info;
	}
	public void setFeedback_info(String feedback_info) {
		this.feedback_info = feedback_info;
	}
	@Override
	public String toString() {
		return "PlanDto [search_planId=" + search_planId + ", search_planName=" + search_planName + ", search_taskName="
				+ search_taskName + ", search_begin_date1=" + search_begin_date1 + ", search_begin_date2="
				+ search_begin_date2 + ", search_end_date1=" + search_end_date1 + ", search_end_date2="
				+ search_end_date2 + ", feedback_info=" + feedback_info + "]";
	}
	
	

}
