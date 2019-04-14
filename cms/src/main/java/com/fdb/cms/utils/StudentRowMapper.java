package com.fdb.cms.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper {

	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Student student=new Student();
		student.setId(rs.getString("ID"));
		student.setFirstName(rs.getString("firstname"));
		student.setLastName(rs.getString("lastname"));
		student.setUserName(rs.getString("username"));
		student.setPassword(rs.getString("password"));
		student.setDeptName(rs.getString("dept_name"));
		student.setTotalCredits(rs.getInt("tot_cred"));
		return student;
	}

}
