package com.springboot.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.common.CommonDto;
import com.springboot.common.CreateUserPermissionsDto;
import com.springboot.common.EmployeeDto;
import com.springboot.common.LoginDto;
import com.springboot.common.PermissionTypeEnum;
import com.springboot.modal.Employee;
import com.springboot.modal.User;
import com.springboot.service.LoginService;

@RestController
public class LoginHandler {
	@Autowired
	LoginService loginService;


	@CrossOrigin
	@PostMapping("/userLogin")
	public User listPage(@RequestBody LoginDto loginDto) {
		String userName = loginDto.getUserName();
		String password = loginDto.getPassword();
		System.out.println(userName);
		System.out.println(password);
		User user = loginService.authenticate(userName, password);
		return user;
	}

	@CrossOrigin
	@GetMapping("/permissions")
	public List<CommonDto> getPermissions() {
		Map<Integer, PermissionTypeEnum> permissions = loginService.getPermissions();
		return convertToCommonDto(permissions);
	}
	
	private List<CommonDto> convertToCommonDto(Map<Integer, PermissionTypeEnum> permissions) {
		List<CommonDto> list=new ArrayList<>();
		permissions.forEach((k,v)->{
			list.add(new CommonDto(k.toString(), v.name()));
		});
		return list;
	}
	
	@CrossOrigin
	@PostMapping("/createUser")
	public void createUser(@RequestBody User user) {
		String userName=user.getUserName();
		System.out.println(userName+">>>>>>>>>>>>>");
		int empId=user.getEmpId();
		System.out.println(empId+">>>>>>>>>>>");
		String userPass = "PASS";
		loginService.createUser(userName, userPass, empId);
	}
	
	@CrossOrigin
	@PostMapping("/createUserPermissions")
	public void createUserPermissions(@RequestBody CreateUserPermissionsDto createUserPermissionsDto) {
		String userName=createUserPermissionsDto.getUserName();
			                                  System.out.println(userName);
		String userPass = "PASS";
		int[] permissionId=createUserPermissionsDto.getPermissionId();
			                                  System.out.println(permissionId);
		int userId = loginService.getUserId(userName, userPass);
			                                  System.out.println(userId);
        loginService.deleteUser(userId);                                 
		for(int i=0; i<permissionId.length; i++) {
			loginService.createUserPermissions(userId, permissionId[i]);
		}
	}

	
	@CrossOrigin
	@GetMapping("/permissions/{empId}")
	public List<CommonDto> getPermissionsByEmpId(@PathVariable ( value = "empId") int empId) {
		User user = loginService.getPermissionsByEmpId(empId);
		
		return convertToCommonDto(user.getPermissions());
		//return 
	}
	
	

}
