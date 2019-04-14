package com.fdb.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdb.cms.dao.LoginDaoImpl;
import com.fdb.cms.utils.Student;

@Service
public class LoginServiceImpl implements LoginService{
	
@Autowired
LoginDaoImpl loginDao;

public List<Student> getStudentDetails(){
	return loginDao.getData();
}
}
