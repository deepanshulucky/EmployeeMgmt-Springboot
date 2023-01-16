package com.springboot.handler;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.common.EmployeeDto;
import com.springboot.modal.Employee;
import com.springboot.service.EmployeeService;

@RestController
public class EmployeeHandler {
	@Autowired
	EmployeeService employeeService;
	
	@CrossOrigin
	@RequestMapping(value="/employees", method=RequestMethod.GET)
	public List<Employee> getAllEmp(){
		
		return employeeService.getAllEmp();
	}
	
	@CrossOrigin
	@GetMapping("/employees/{id}")
	public Employee getEmployee(@PathVariable ( value = "id") int id) {
		
		Employee employee = employeeService.getEmpById(id);
		return employee;
//		return employeeService.getEmpById(id);
	}
	
	@CrossOrigin
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable (value = "id") int id) {
		
		this.employeeService.deleteEmployeeById(id);
	}
	
	@CrossOrigin
	@PostMapping("/employees")
	public void saveEmployee(@RequestBody EmployeeDto employeeDto) {
		
		// save employee to database
		employeeService.newEmployee(employeeDto);
		//return employee;
	}
	
	@CrossOrigin
	@PutMapping("/employees/{id}")
	public void updateEmployee(@PathVariable (value = "id") int id, @RequestBody EmployeeDto employeeDto) {
		String name=employeeDto.getName();
		double sal=employeeDto.getSalary();
		int age=employeeDto.getAge();
		String email=employeeDto.getEmail();
		int dept=employeeDto.getDepartmentId();
		System.out.println(name+sal+age+dept+email+"tooooooooooooooooooooo");
		employeeService.updateEmployee(id, name, sal, age, email, dept);
	}
	
		

}
