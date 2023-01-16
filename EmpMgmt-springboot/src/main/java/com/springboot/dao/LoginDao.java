package com.springboot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.springboot.common.PermissionTypeEnum;
import com.springboot.modal.User;


@Repository("loginDao")
public class LoginDao {
	private static final Logger logger = LoggerFactory.getLogger(LoginDao.class);
	static {
		try {
			logger.info("Loading Driver");
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	DataSource dataSource = null;

	@Autowired
	ApplicationContext context;

	private DataSource getDataSource() {
		System.out.println("Getting connecting from database");
		logger.info("getting datasource");
		if (dataSource == null) {
			dataSource = context.getBean("postgresqlDataSource", DataSource.class);
		}
		return dataSource;
	}
	
	@SuppressWarnings("removal")
	public User isValidUser(String userName, String password) {
		System.out.println("LoginDao.isValidU>>>>>>>>");
		User user = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			logger.info("inside try block of isValidUser()");
			
String sql = "select e.id, e.name, d.dept_name, p.permission_id p_id, p.permission " + "from user_data ud "+ "inner join employee e on e.id = ud.emp_id " + "left join department d on d.dept_id = e.dept_id "+ "inner join user_permission up on up.user_id = ud.user_id "
+ "inner join permission p on p.permission_id = up.permission_id where ud.user_name = ? and ud.pass = ? ";

			ps=getDataSource().getConnection().prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs=ps.executeQuery();

			Map<Integer, PermissionTypeEnum> permissions = null;
			if (rs != null) {
				while (rs.next()) {
					if (user == null) {
						user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("dept_name"));
						permissions = new HashMap<>(5);
					}
					permissions.put(new Integer(rs.getInt("p_id")),
							PermissionTypeEnum.valueOf(rs.getString("permission")));
				}
			}

			if (user != null) {
				user.setPermissions(permissions);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("isValidUser() operation was successful");
		return user;

	}
	
	public String getUserDetailsById(int empId) {
//		String[] details = null;
		System.out.println(empId);
		String details = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			System.out.println("inside try block of getUserDetails()");
			String sql = ("select user_name,pass from user_data where user_data.emp_id=?");

			ps=getDataSource().getConnection().prepareStatement(sql);
			ps.setInt(1, empId);

			rs = ps.executeQuery();
			if (rs.next()) {
//				details[0] = rs.getString("user_name");
				details = rs.getString("user_name");
//				details[1] = rs.getString("pass");
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("getUserById() operation was successful");
		System.out.println(details);

		return details;
	}
	
	@SuppressWarnings("removal")
	public Map<Integer, PermissionTypeEnum> getPermissions() {
		Map<Integer, PermissionTypeEnum> permissions = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			System.out.println("getPermissions()");
			String sql = ("select permission_id, permission from permission");
			ps=getDataSource().getConnection().prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs != null) {
				permissions = new HashMap<>(5);
				while (rs.next()) {
					permissions.put(new Integer(rs.getInt("permission_id")),
							PermissionTypeEnum.valueOf(rs.getString("permission")));
				}
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return permissions;
	}
	
	@SuppressWarnings("removal")
	public User getPermissionsByEmpId(int empId) {
		User user = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			System.out.println("inside getPermissionsByEmpId()");

			String sql = "select up.user_id, p.permission_id p_id, p.permission from user_data ud "
					+ "inner join user_permission up on up.user_id = ud.user_id "
					+ "inner join permission p on p.permission_id = up.permission_id " + "where ud.emp_id = ? ";

			ps=getDataSource().getConnection().prepareStatement(sql);
			ps.setInt(1, empId);

			rs = ps.executeQuery();
			Map<Integer, PermissionTypeEnum> permissions = null;
			if (rs != null) {
				while (rs.next()) {
					if (user == null) {
						user = new User(rs.getInt("user_id"));
						permissions = new HashMap<>(5);
					}
					permissions.put(new Integer(rs.getInt("p_id")),
							PermissionTypeEnum.valueOf(rs.getString("permission")));
				}
			}
			
			if (user != null) {
				user.setPermissions(permissions);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("getPermissionsByEmpId() operation was successful");
		return user;
	}
	
	public void createUser(String userName, String userPass, int empId) {
		PreparedStatement ps = null;
		try {
			System.out.println("inside try block of createUser()");
			String sql = ("insert into user_data(user_name, pass, emp_id) values(?,?,?)");
			ps=getDataSource().getConnection().prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, userPass);
			ps.setInt(3, empId);

			ps.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("create user operation was successful");

	}
	
	public void createUserPermissions(int userId, int permissionId) {
		
		PreparedStatement ps = null;
		try {
			System.out.println("inside try block of setUserPermissions()");
			String sql = ("insert into user_permission(user_id, permission_id) values(?,?)");
			ps=getDataSource().getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, permissionId);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("setUserPermissions operation was successful");

	}
	
	public int getUserId(String userName, String userPass) {

		int userId = 0;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			System.out.println("inside try block of getUserId()");
			String sql = ("select user_data.user_id from user_data where user_data.user_name=? and user_data.pass=?");

			ps=getDataSource().getConnection().prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, userPass);

			rs = ps.executeQuery();
			if (rs.next()) {
				userId = rs.getInt("user_id");
			}


		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("getUserId() operation was successful");
		return userId;
	}
	
	public void deleteUser(int userId) {
		PreparedStatement ps = null;
		try {
			System.out.println("inside try block of deleteUser()");
			String sql = "delete from user_permission where user_id=?";
			ps=getDataSource().getConnection().prepareStatement(sql);
			ps.setInt(1, userId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("deletUser operation was successful");
	}
	
//	public boolean isAlreadyUser(int id) {
//		PreparedStatement ps = null;
//		boolean already = false;
//		try {
//			connect();
//
//			String sql = "select id from user_data where emp_id=?";
//			ps = connection.prepareStatement(sql);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				already = true;
//
//			}
//			ps.close();
//			connection.close();
//		} 
//		catch (Exception e) {
//			System.out.println(e.getClass().getName() + ": " + e.getMessage());
//		}
//		System.out.println("Operation done successfully");
//		return already;
//	}

}
