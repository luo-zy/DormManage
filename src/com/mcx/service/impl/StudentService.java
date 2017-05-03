package com.mcx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mcx.dao.StudentDao;
import com.mcx.model.Student;
import com.mcx.service.IStudentService;

@Service("studentService")
@Transactional
public class StudentService implements IStudentService{

	@Autowired
	private StudentDao studentDao;

	@Override
	public List<Student> getUserList() {
		List<Student> list = studentDao.getUserList();
		return list;
	}

}
