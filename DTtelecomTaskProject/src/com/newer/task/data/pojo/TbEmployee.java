package com.newer.task.data.pojo;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

public class TbEmployee {
	private Integer employeeId;
	private String employeeName;
	private String password;
	private String realName;
	private String sex;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	private String duty;
	// 日期格式化
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date enrollDate;

	private String edcucation;
	private String major;
	private String experience;

	private TbRole role;// 角色
	private TbEmployee parent; // 上司
	private int status;
	public TbEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TbEmployee(Integer employeeId, String employeeName, String password,
			String realName, String sex, Date birthday, String duty,
			Date enrollDate, String edcucation, String major,
			String experience, TbRole role, TbEmployee parent, int status) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.password = password;
		this.realName = realName;
		this.sex = sex;
		this.birthday = birthday;
		this.duty = duty;
		this.enrollDate = enrollDate;
		this.edcucation = edcucation;
		this.major = major;
		this.experience = experience;
		this.role = role;
		this.parent = parent;
		this.status = status;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getEdcucation() {
		return edcucation;
	}
	public void setEdcucation(String edcucation) {
		this.edcucation = edcucation;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public TbRole getRole() {
		return role;
	}
	public void setRole(TbRole role) {
		this.role = role;
	}
	public TbEmployee getParent() {
		return parent;
	}
	public void setParent(TbEmployee parent) {
		this.parent = parent;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TbEmployee [employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", password=" + password + ", realName="
				+ realName + ", sex=" + sex + ", birthday=" + birthday
				+ ", duty=" + duty + ", enrollDate=" + enrollDate
				+ ", edcucation=" + edcucation + ", major=" + major
				+ ", experience=" + experience + ", role=" + role + ", parent="
				+ parent + ", status=" + status + "]";
	}
	
}
