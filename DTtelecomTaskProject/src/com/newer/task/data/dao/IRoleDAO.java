package com.newer.task.data.dao;

import java.util.List;

import com.newer.task.data.pojo.TbRole;

public interface IRoleDAO {
	// ���ݱ�Ų�ѯȨ��
	public abstract TbRole selectByRoleId(Integer roleId);

	// ��ѯ����Ȩ��
	public abstract List<TbRole> findAllRoles();
}
