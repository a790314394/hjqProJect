package com.newer.task.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.newer.task.data.dao.ITaskDAO;
import com.newer.task.data.pojo.TbEmployee;
import com.newer.task.data.pojo.TbPlan;
import com.newer.task.service.IEmployeeService;
import com.newer.task.service.IStaffService;
import com.newer.task.service.ITaskService;
import com.newer.task.web.util.TaskPlan;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test {

	@Resource(name = "staffservice")
	IStaffService service;
  @org.junit.Test
  public void test(){
	 List<TaskPlan> list=  service.selectPageByPlanAdvance(7, null, 1, 50);
	 for (TaskPlan taskPlan : list) {
		System.out.println(taskPlan.getTask());
	}
  }
}
