package com.springboot.common;

public class CreateUserPermissionsDto {
	private String userName;
	//private String userPassword;
	private int[] permissionId;
	
	
	
	public CreateUserPermissionsDto(String userName, int[] permissionId) {
		this.userName = userName;
		this.permissionId = permissionId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int[] getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int[] permissionId) {
		this.permissionId = permissionId;
	}

}
