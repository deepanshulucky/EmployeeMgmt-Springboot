package com.springboot.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.modal.Department;
import com.springboot.modal.Employee;
import com.springboot.service.DepartmentService;

@RestController
public class DepartmentHandler {
	@Autowired
	DepartmentService departmentService;
	
	@CrossOrigin
	@RequestMapping(value="/department", method=RequestMethod.GET)
	public List<Department> getAllEmp(){		
		return departmentService.getAllDept();
	}
	


}
