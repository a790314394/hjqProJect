package com.newer.task.data.dao;

import java.util.List;

import com.newer.task.data.pojo.TbRole;

public interface IRoleDAO {
	// 根据编号查询权限
	public abstract TbRole selectByRoleId(Integer roleId);

	// 查询所有权限
	public abstract List<TbRole> findAllRoles();
}
