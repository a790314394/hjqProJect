package com.newer.task.data.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.newer.task.data.dao.IEmployeeDAO;
import com.newer.task.data.pojo.TbEmployee;
import com.newer.task.data.util.DBUtil;

@SuppressWarnings("all")
@Component("employeeDao")
public class EmployeeDaoImpl implements IEmployeeDAO {
	JdbcTemplate template = new JdbcTemplate(DBUtil.getDataSource());

	@Override
	public List<TbEmployee> selectEmployees(String sql, Object[] params) {
		return this.template.query(sql, params, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int index) throws SQLException {
				TbEmployee employee = new TbEmployee();
				employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				employee.setEmployeeName(rs.getString("employee_name"));
				employee.setPassword(rs.getString("password"));
				employee.setRealName(rs.getString("real_name"));
				employee.setSex(rs.getString("sex"));
				employee.setBirthday(rs.getTimestamp("birthday"));
				employee.setDuty(rs.getString("duty"));
				employee.setEnrollDate(rs.getTimestamp("enrolldate"));
				employee.setEdcucation(rs.getString("education"));
				employee.setMajor(rs.getString("major"));
				employee.setExperience(rs.getString("experience"));
				employee.setRole(new RoleDaoImpl().selectByRoleId(rs
						.getInt("role_id")));
				// 根据parentId查询关联的上司员工
				System.out.println(rs.getInt("parent_id"));
				int pid = rs.getInt("parent_id");
				if (pid > 0) {
					// 判断是否为管理员 不是则继续查询管理员信息 ，是则停止
					TbEmployee parentemployee = new TbEmployee();
					parentemployee = new EmployeeDaoImpl().selectById(pid);
					employee.setParent(parentemployee);
				}
				return employee;
			}
		});
	}

	@Override
	public TbEmployee selectById(Integer id) {

		return (TbEmployee) template.query(
				"select * from t_employee where EMPLOYEE_ID=?",
				new Object[] { id }, new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int index)
							throws SQLException {
						TbEmployee employee = new TbEmployee();
						employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
						employee.setEmployeeName(rs.getString("employee_name"));
						employee.setPassword(rs.getString("password"));
						employee.setRealName(rs.getString("real_name"));
						employee.setSex(rs.getString("sex"));
						employee.setBirthday(rs.getTimestamp("birthday"));
						employee.setDuty(rs.getString("duty"));
						employee.setEnrollDate(rs.getTimestamp("enrolldate"));
						employee.setEdcucation(rs.getString("education"));
						employee.setMajor(rs.getString("major"));
						employee.setExperience(rs.getString("experience"));
						employee.setRole(new RoleDaoImpl().selectByRoleId(rs
								.getInt("role_id")));
						// 根据parentId查询关联的上司员工
						// TbEmployee parentemployee = new TbEmployee();
						// employee.setParent(parentemployee);
						return employee;
					}
				}).get(0);
	}

	@Override
	public int insert(TbEmployee employee) {
		// TODO Auto-generated method stub
		System.out.println("新增功能");
		String sql = "insert into T_EMPLOYEE (employee_name, password, real_name, sex, birthday, duty, enrolldate, education, major, experience, role_id)values(?,?,?,?,?,?,?,?,?,?,?)";
		int count = this.template.update(
				sql,
				new Object[] { employee.getEmployeeName(),
						employee.getPassword(), employee.getRealName(),
						employee.getSex(), employee.getBirthday(),
						employee.getDuty(), employee.getEnrollDate(),
						employee.getEdcucation(), employee.getMajor(),
						employee.getExperience(),
						employee.getRole().getRoleId() });
		return count;
	}

	@Override
	public int delete(TbEmployee employee) {
		// TODO Auto-generated method stub
		System.out.println("按编号删除员工");
		System.out.println("删除员工" + employee);
		String sql = "update t_employee a set a.status=1 where EMPLOYEE_ID=?";
		int count = this.template.update(sql,
				new Object[] { employee.getEmployeeId() });
		return count;
	}

	@Override
	public List<TbEmployee> selectById(TbEmployee employee) {
		// TODO Auto-generated method stub
		System.out.println("按编号查询");
		String sql = "select * from T_EMPLOYEE where employee_id=?";
		List<TbEmployee> list = this.template.query(sql,
				new Object[] { employee.getEmployeeId() }, new RowMapper() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						TbEmployee employee = new TbEmployee();
						employee.setEmployeeId(rs.getInt("employee_id"));
						employee.setEmployeeName(rs.getString("employee_name"));
						employee.setPassword(rs.getString("password"));
						employee.setRealName(rs.getString("real_name"));
						employee.setSex(rs.getString("sex"));
						employee.setBirthday(rs.getTimestamp("birthday"));
						employee.setDuty(rs.getString("duty"));
						employee.setEnrollDate(rs.getTimestamp("enrolldate"));
						employee.setEdcucation(rs.getString("education"));
						employee.setMajor(rs.getString("major"));
						employee.setExperience(rs.getString("experience"));
						employee.setRole(new RoleDaoImpl().selectByRoleId(rs
								.getInt("role_id")));
						// 根据parentId查询关联的上司员工
						// System.out.println(rs.getInt("parent_id"));
						TbEmployee parentemployee = new TbEmployee();
						// employee.setEmployeeId(rs.getInt("parent_id"));
						employee.setParent(parentemployee);
						return employee;
					}

				});
		return list;
	}

	public List<TbEmployee> selectEmp(int pageNo, int pageSize) {
		System.out.println("全部查询");
		int start = (pageNo - 1) * pageSize + 1;
		int end = pageNo * pageSize;
		String sql = "select * from(select row_number() over(order by e.employee_id ) no, e.* from t_employee e where e.STATUS=0) te where te.no between ? and ?";
		List<TbEmployee> list = this.template.query(sql, new Object[] { start,
				end }, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				TbEmployee employee = new TbEmployee();
				employee.setEmployeeId(rs.getInt("employee_id"));
				employee.setEmployeeName(rs.getString("employee_name"));
				employee.setPassword(rs.getString("password"));
				employee.setRealName(rs.getString("real_name"));
				employee.setSex(rs.getString("sex"));
				employee.setBirthday(rs.getTimestamp("birthday"));
				employee.setDuty(rs.getString("duty"));
				employee.setEnrollDate(rs.getTimestamp("enrolldate"));
				employee.setEdcucation(rs.getString("education"));
				employee.setMajor(rs.getString("major"));
				employee.setExperience(rs.getString("experience"));
				employee.setRole(new RoleDaoImpl().selectByRoleId(rs
						.getInt("role_id")));
				int pid = rs.getInt("parent_id");
				System.out.println("主管编号为" + pid);
				if (pid > 0) {
					// 判断是否为管理员 不是则继续查询管理员信息 ，是则停止
					TbEmployee parentemployee = new TbEmployee();
					parentemployee = new EmployeeDaoImpl().selectById(pid);
					employee.setParent(parentemployee);
				}
				return employee;
			}
		});
		return list;
	}

	@Override
	public int update(TbEmployee employee) {
		// TODO Auto-generated method stub
		System.out.println("按编号修改信息员工上级");
		String sql = "update t_employee set PARENT_ID=? where EMPLOYEE_ID=?";

		return this.template.update(
				sql,
				new Object[] { employee.getParent().getEmployeeId(),
						employee.getEmployeeId() });
	}

	@Override
	public int queryCount(String sql, Integer id) {

		return template.queryForInt(sql, new Object[] { id });
	}

}
