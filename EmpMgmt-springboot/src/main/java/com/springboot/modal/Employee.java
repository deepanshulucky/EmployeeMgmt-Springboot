package com.springboot.modal;


public class Employee {

	private int id;
	private String name;
	private double salary;
	private int age;
	private String emailId;
	private Department department;
	private boolean userCreated;
	
	//primitive int departmentId;

	public Employee(int id, String name, double salary, int age, String emailId,
			Department department, boolean hasUser) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.age = age;
		this.emailId =emailId;
		this.department = department;
		this.userCreated = hasUser;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public boolean isUserCreated() {
		return userCreated;
	}

	public void setUserCreated(boolean userCreated) {
		this.userCreated = userCreated;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", age=" + age + ", department="
				+ (department != null ? department.toString() : "") + "]";
	}

}
