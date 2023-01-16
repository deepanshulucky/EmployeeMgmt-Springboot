package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springboot.dao.DepartmentDao;
import com.springboot.modal.Department;

@Service
public class DepartmentService {
	
	@Autowired
	@Qualifier("departmentDao")
	private DepartmentDao departmentDao;

	public List<Department> getAllDept() {
		return departmentDao.getAllDept();
	}

}
