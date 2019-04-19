package com.unt.coursemanagentsys.dao;

import org.springframework.stereotype.Repository;

import com.unt.coursemanagentsys.util.User;

import java.sql.Types;

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

	@SuppressWarnings("unchecked")
	@Override
	public User userValidate(String username) {
		// TODO Auto-generated method stub
		String sql = "select * from userdetails where username=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { username }, new BeanPropertyRowMapper<>(User.class));
	}

	@Override
	public int userExists(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM userdetails where username=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { username }, Integer.class);
	}

	@Override
	public int userIdProvided(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(*) FROM userdetails where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class);
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		String sql = "UPDATE userdetails SET firstname=?,lastname=?,email=?,dept_name=?,username=?,password=? where id=?";
		Object[] args = { user.getFirstname(), user.getLastname(), user.getEmail().toLowerCase(), user.getDeptName(),
				user.getUsername().toUpperCase(), user.getPassword(), user.getId() };
		int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR };
		jdbcTemplate.update(sql, args, argTypes);
		//return null;
	}

}
