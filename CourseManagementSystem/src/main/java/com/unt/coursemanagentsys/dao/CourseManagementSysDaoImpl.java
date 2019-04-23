package com.unt.coursemanagentsys.dao;

import org.springframework.stereotype.Repository;

import com.unt.coursemanagentsys.util.Assignment;
import com.unt.coursemanagentsys.util.Course;
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
		Object[] args= {user.getUsername().toUpperCase(), user.getPassword(), user.getId()};
		int[] argTypes= {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
		if(jdbcTemplate.update(sql, args, argTypes)==1)
			return true;
		else
		return false;
	}
	@Override
	public List<Course> getCourses(User user) {
		String query = "";
		if(user.getRole().equalsIgnoreCase("student")) {
			query = "SELECT TK.ISTA,CR.TITLE,TK.SEMESTER, TC.ID AS INSTRUCTORID FROM COURSES CR, TAKES TK, TEACHES TC WHERE CR.COURSE_ID = TK.COURSE_ID AND TK.ID = ? AND TK.YEAR = date_part('year', CURRENT_DATE) AND CR.SEMESTER = TK.SEMESTER\r\n" + 
					"AND TK.COURSE_ID = TC.COURSE_ID AND TK.SEMESTER= TC.SEMESTER AND TC.YEAR = TK.YEAR;";
		} else if(user.getRole().equalsIgnoreCase("instructor")) {
			query = "SELECT DISTINCT CR.TITLE, TC.SEMESTER,TC.ID AS INSTRUCTORID FROM TEACHES TC,COURSES CR WHERE TC.COURSE_ID = CR.COURSE_ID "
					+ "AND TC.ID = ? AND CR.YEAR = TC.YEAR AND CR.SEMESTER = TC.SEMESTER AND TC.YEAR = date_part('year', CURRENT_DATE)";
		}
		return jdbcTemplate.query(query, new Object[] { user.getId() }, new BeanPropertyRowMapper<>(Course.class));
	}

	@Override
	public List<Assignment> getAssignementFilesForInstructor(Course course) {
		String query = "SELECT  CR.TITLE AS,TK.ID FROM TEACHES TC, TAKES TK, COURSES CR WHERE TC.COURSE_ID = TK.COURSE_ID AND TC.ID = ? AND TK.YEAR = TC.YEAR AND TK.SEMESTER = TC.SEMESTER AND TC.YEAR = date_part('year', CURRENT_DATE)\r\n" + 
				"AND CR.COURSE_ID = TC.COURSE_ID AND CR.SEMESTER = TC.SEMESTER AND CR.YEAR=TC.YEAR";
		return jdbcTemplate.query(query, new Object[] { course.getInstructorId() }, new BeanPropertyRowMapper<>(Assignment.class));
	}

	
	
	
	
	
	
	
	
	

}
