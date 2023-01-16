package com.springboot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.springboot.common.EmployeeDto;
import com.springboot.modal.Department;
import com.springboot.modal.Employee;

@Repository("employeeDao")
public class EmployeeDao {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);
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

	public List<Employee> list() {
		logger.info("inside list()");
		List<Employee> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			
			String sql = "select e.id, e.name, e.sal, e.age, e.email_id, d.dept_id, d.dept_name, ud.user_id user_id from employee e "
					+ "left join department d on e.dept_id=d.dept_id left join user_data ud on ud.emp_id = e.id ";
			ps=getDataSource().getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					double sal = rs.getDouble("sal");
					int age = rs.getInt("age");
					String emailId = rs.getString("email_id");
					int deptId = rs.getInt("dept_id");
					Department dept = null;
					if (deptId > 0) {
						String deptName = rs.getString("dept_name");
						dept = new Department(deptId, deptName);
					}
					int userId = rs.getInt("user_id");
					boolean userCreated = false;
					if (userId > 0) {
						userCreated = true;
					}
					list.add(new Employee(id, name, sal, age, emailId, dept, userCreated));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
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
        return list;
	}
	
	public Employee getEmpById(int id) {

		logger.info("inside getEmpById()");
		System.out.println("inside try block of getEmpById()");
		Employee emp = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			System.out.println("inside try block of getEmpById()");

			String sql = "select e.id, e.name, e.sal, e.age,e.email_id, d.dept_id, d.dept_name from employee e "
					+ "left join department d on e.dept_id=d.dept_id where e.id=? ";
			ps=getDataSource().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			Department dept = null;
			if (rs.next()) {
				// int idd = rs.getInt("id");
				String name = rs.getString("name");
				double sal = rs.getDouble("sal");
				int age = rs.getInt("age");
				String emailId = rs.getString("email_id");

				int deptId = rs.getInt("dept_id");
				if (deptId > 0) {
					String deptName = rs.getString("dept_name");
					dept = new Department(deptId, deptName);
				}
				System.out.println(name + " " + sal + " " + age + " " + deptId+" "+emailId);

				emp = new Employee(id, name, sal, age, emailId, dept, false);
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
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
        return emp;

	}
	
	public void deleteEmployeeById(int id) {

		logger.info("inside list()");
		PreparedStatement ps = null;

		try {

			String sql = "delete from employee  where id=?";
			ps=getDataSource().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		logger.info("delete Operation done successfully");

	}
	
//	public void add(String name, double sal, int age, String email, int dept) {
	public void add(EmployeeDto employeeDto) {
		String name=employeeDto.getName();
		double sal=employeeDto.getSalary();
		int age=employeeDto.getAge();
		String email=employeeDto.getEmail();
		int dept=employeeDto.getDepartmentId();

		PreparedStatement ps = null;
		try {
			System.out.println("inside try block of addEmp()");

			String sql = "insert into employee(name, sal, age, dept_id,email_id) values(?,?,?,?,?);";
			ps=getDataSource().getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.setDouble(2, sal);
			ps.setInt(3, age);
			if (dept > 0) {
				ps.setInt(4, dept);
			} else {
				ps.setObject(4, null);
			}
			ps.setString(5, email);

			int updated = ps.executeUpdate();
			System.out.println("inside try block of addEmp()");
			System.out.println("updated: " + updated);

		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update(int id, String name, double sal, int age, String email, int dept) {

		PreparedStatement ps = null;
		try {
			String sql = ("update employee set name=?, sal=?,age=?,dept_id=?,email_id=? where id=?");
			ps=getDataSource().getConnection().prepareStatement(sql);
			ps.setString(1, name);
			ps.setDouble(2, sal);
			ps.setInt(3, age);
			if (dept > 0) {
				ps.setInt(4, dept);
			} else {
				ps.setObject(4, null);
			}
			if (email!=null) {
				System.out.println("not null email");
			ps.setString(5, email);
			}else {
				System.out.println("null email");
				ps.setObject(5, null);
			}
			ps.setInt(6, id);
			
			ps.executeUpdate();


		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null) {
					ps.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("update operation was successful");
	}

}
