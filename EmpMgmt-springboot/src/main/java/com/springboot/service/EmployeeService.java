package com.springboot.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springboot.common.EmployeeDto;
import com.springboot.dao.EmployeeDao;
import com.springboot.modal.Department;
import com.springboot.modal.Employee;

@Service
public class EmployeeService {
	@Autowired
	@Qualifier("employeeDao")
	EmployeeDao employeeDao;
	
	public List<Employee> getAllEmp() {
		return employeeDao.list();
	}
	
	public Employee getEmpById(int id) {
		return employeeDao.getEmpById(id);
	}
	
	public void deleteEmployeeById(int id) {
		employeeDao.deleteEmployeeById(id);

	}
	
	public void newEmployee(EmployeeDto employeeDto) {
		//employeeDao.add(name, sal, age,email, dept);
		employeeDao.add(employeeDto);
		//return employee;

	}
	
	public void updateEmployee(int id, String name, double sal, int age, String email, int dept) {
		employeeDao.update(id, name, sal, age,email, dept);

	}


}
