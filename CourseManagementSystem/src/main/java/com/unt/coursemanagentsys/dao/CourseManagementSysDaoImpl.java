package com.unt.coursemanagentsys.dao;

import org.springframework.stereotype.Repository;

import com.unt.coursemanagentsys.util.Department;
import com.unt.coursemanagentsys.util.User;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class CourseManagementSysDaoImpl implements CourseManagementSysDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	// public CourseManagementSysDaoImpl(DataSource dataSource) {
	// this.jdbcTemplate = new JdbcTemplate(dataSource);
//} 

	@Override
	public User userValidate(String username) {
		// TODO Auto-generated method stub
		String sql = "select * from userdetails where username=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { username.toUpperCase() }, new BeanPropertyRowMapper<>(User.class));
	}

	@Override
	public int userExists(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM userdetails where username=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { username.toUpperCase() }, Integer.class);
	}

	@Override
	public int userIdProvided(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM university where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);
	}
	public int userIdExists(String id) {
		String sql = "SELECT COUNT(*) FROM userdetails where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);
	}

	@Override
	public Boolean register(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO userdetails values(?,?,?,?,?,?,?)";
		Object[] args = { user.getId(), user.getFirstName(), user.getLastName(), user.getEmail().toLowerCase(),
				user.getDeptName(), user.getUsername().toUpperCase(), user.getPassword() };
		int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR };
		if (jdbcTemplate.update(sql, args, argTypes) == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<Department> fetchAllDepartments() {
		// TODO Auto-generated method stub
		String sql= "SELECT * FROM departments";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Department>(Department.class));
	}
	
	public String getEmail(String id) {
		String sql="SELECT email FROM userdetails where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, String.class);
	}

	@Override
	public Boolean resetPassword(User user) {
		// TODO Auto-generated method stub
		String sql="UPDATE userdetails SET username=?, password=? where id=?";
		Object[] args= {user.getUsername(), user.getPassword(), user.getId()};
		int[] argTypes= {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		if(jdbcTemplate.update(sql, args, argTypes)==1)
			return true;
		else
		return false;
	}
	
	
	
	
	
	
	
	
	
	

}
