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
		if(getRoleId(user.getId())==2) {
			query = "select distinct takes.course_id,courses.title,takes.semester,takes.year from takes,courses where takes.id=? and takes.semester=? and takes.year=? and takes.course_id=courses.course_id";
		} else if(getRoleId(user.getId())==1) {
			query = "select distinct teaches.course_id,courses.title,teaches.semester,teaches.year from teaches,courses where teaches.id=? and teaches.semester=? and teaches.year=? and teaches.course_id=courses.course_id";
		}
		return jdbcTemplate.query(query, new Object[] { user.getId(),user.getSemester(),user.getYear() }, new BeanPropertyRowMapper<>(Course.class));
	}
	@Override
	public List<Assignment> getAssignementFilesForInstructor(Course course) {
		String query = "SELECT  CR.TITLE AS,TK.ID FROM TEACHES TC, TAKES TK, COURSES CR WHERE TC.COURSE_ID = TK.COURSE_ID AND TC.ID = ? AND TK.YEAR = TC.YEAR AND TK.SEMESTER = TC.SEMESTER AND TC.YEAR = date_part('year', CURRENT_DATE)\r\n" + 
				"AND CR.COURSE_ID = TC.COURSE_ID AND CR.SEMESTER = TC.SEMESTER AND CR.YEAR=TC.YEAR";
		return jdbcTemplate.query(query, new Object[] { course.getInstructorId() }, new BeanPropertyRowMapper<>(Assignment.class));
	}
	@Override
	public int getRoleId(String id) {
		String sql="select role_id from university where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, Integer.class );
	}
	
	@Override
	public List<Course> getAddCoursesList(User user){
		String sql="";
		if(getRoleId(user.getId())==1) 
			sql="select * from courses where dept_name=? and year = ? and semester=? and isactive=false";
			else if(getRoleId(user.getId())==2)
				sql="select * from courses where dept_name=? and year = ? and semester=? and isactive=true";
	return jdbcTemplate.query(sql, new Object[] { user.getDeptName(),user.getYear(),user.getSemester() }, new BeanPropertyRowMapper<>(Course.class));
	}
	
	
	public int countUserCourses(User user) {
		String sql="select count(id) from takes where semester=? and year=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { user.getSemester(), user.getYear() }, Integer.class );
	}

	public int studentAlreadyHasACourse(User user,Course course) {
		String sql="select count(id) from takes where course_id=? and semester=? and year=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { course.getCourse_id(),user.getSemester(),user.getYear() }, Integer.class );
	}
	@Override
	public Boolean registerInstructorCourse(User user, Course course) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO teaches values(?,?,?,?)";
		Object[] args = { user.getId(), course.getCourse_id(), user.getSemester(), user.getYear() };
		int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		if(jdbcTemplate.update(sql, args, argTypes) == 1)
		{
			String inner="UPDATE courses SET isactive=true where course_id=? and semester=? and year=?";
		Object[] innerargs= {course.getCourse_id(), user.getSemester(), user.getYear()};
		int[] innerargTypes= {Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
		jdbcTemplate.update(inner, innerargs, innerargTypes);
			return true;
		}
		else
			return false;
	}

	@Override
	public Boolean registerStudentCourse(User user, Course course) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO takes values(?,?,?,?,?,?)";
		Object[] args = { user.getId(), course.getCourse_id(), user.getSemester(), user.getYear(),"NA",false };
		int[] argTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,Types.VARCHAR,Types.BOOLEAN};
		if(jdbcTemplate.update(sql, args, argTypes) == 1)
		return true;
		else
			return false;
	}
	
	
	
	
	
	
	
	
	

}
