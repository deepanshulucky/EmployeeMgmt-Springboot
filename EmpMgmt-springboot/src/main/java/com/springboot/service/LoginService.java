package com.springboot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springboot.common.PermissionTypeEnum;
import com.springboot.dao.LoginDao;
import com.springboot.modal.User;

@Service
public class LoginService {
	@Autowired
	@Qualifier("loginDao")
    LoginDao loginDao;
	
	public User authenticate(String userName, String password) {
		return loginDao.isValidUser(userName, password);
	}

	public Map<Integer, PermissionTypeEnum> getPermissions() {
		return loginDao.getPermissions();
	}
	
	public String getUserDetails(int empId) {
		return loginDao.getUserDetailsById(empId);
	}
	
	public User getPermissionsByEmpId(int empId) {
		return loginDao.getPermissionsByEmpId(empId);
		
	}
	
	public void createUser(String userName, String userPass, int empId) {
		loginDao.createUser(userName, userPass, empId);

	}
	
	public void createUserPermissions(int userId, int permissionId) {
		loginDao.createUserPermissions(userId, permissionId);

	}
	
	public void deleteUser(int userId) {
		loginDao.deleteUser(userId);
	}
	
	public int getUserId(String userName, String userPass) {
		return loginDao.getUserId(userName, userPass);

	}

}
