package com.springboot.common;

public class EmployeeDto {

	//private int id;
	private String name;
	private double salary;
	private int age;
	private String email;
	//private Department department;
	private int departmentId;
	//private boolean userCreated;
	
	//primitive int departmentId;

	public EmployeeDto(String name, double salary, int age, String emailId,
			int departmentId, boolean hasUser) {
		//this.id = id;
		this.name = name;
		this.salary = salary;
		this.age = age;
		this.email =email;
		this.departmentId = departmentId;
		//this.userCreated = hasUser;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}

//	public Department getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(Department department) {
//		this.department = department;
//	}

//	public boolean isUserCreated() {
//		return userCreated;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public void setUserCreated(boolean userCreated) {
//		this.userCreated = userCreated;
//	}

	@Override
	public String toString() {
		return "EmployeeDto [ name=" + name + ", salary=" + salary + ", age=" + age + ", emailId="
				+ email + ", departmentId=" + departmentId + "]";
	}//id=" + id + ",, userCreated=" + userCreated + "

}
