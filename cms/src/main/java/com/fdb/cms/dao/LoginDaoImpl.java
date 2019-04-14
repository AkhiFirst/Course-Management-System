package com.fdb.cms.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcOperations;

import com.fdb.cms.utils.Student;
import com.fdb.cms.utils.StudentRowMapper;

@Repository
public class LoginDaoImpl implements LoginDao {

JdbcTemplate jdbcTemplate;
public LoginDaoImpl(DataSource dataSource) {  
        this.jdbcTemplate = new JdbcTemplate(dataSource);  
} 
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getData() {
		// TODO Auto-generated method stub
		String sql="select * from student";
		return jdbcTemplate.query(sql,new StudentRowMapper());
		/*List<Student> students=new ArrayList<Student>();
		List<Map> rows = jdbcTemplate.query(sql,new StudentRowMapper());
		for (Map row : rows) {
			Student student = new Student();
			student.setId(row.get("ID"));
			student.setName((String)row.get("NAME"));
			student.setAge((Integer)row.get("AGE"));
			students.add(student);
		}
		//return jdbcTemplate.queryForObject(sql, Object[] {id}, new StudentRowMapper(Student.class));
		return students;*/
	}

}
