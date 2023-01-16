package com.springboot.modal;

import java.util.Map;

import com.springboot.common.PermissionTypeEnum;


public class User {
	private int empId;
	private String empName;
	private String empDeptName;
	private int userId;
	private String userName;
	private Map<Integer, PermissionTypeEnum> permissions;

	public User() {
		super();
	}

	public User(int empId, String empName, String empDeptName) {
		this.empId = empId;
		this.empName = empName;
		this.empDeptName = empDeptName;
	}

	public User(int empId, String userName, Map<Integer, PermissionTypeEnum> permissions) {
		this.empId = empId;
		this.userName = userName;
		this.permissions = permissions;
	}

	public User(int userId) {
		this.userId = userId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDeptName() {
		return empDeptName;
	}

	public void setEmpDeptName(String empDeptName) {
		this.empDeptName = empDeptName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Map<Integer, PermissionTypeEnum> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<Integer, PermissionTypeEnum> permissions) {
		this.permissions = permissions;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}