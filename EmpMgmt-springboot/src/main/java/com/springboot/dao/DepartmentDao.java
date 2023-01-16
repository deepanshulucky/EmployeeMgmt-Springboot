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

import com.springboot.modal.Department;

@Repository("departmentDao")
public class DepartmentDao  {
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

	public List<Department> getAllDept() {
		List<Department> list = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs =null;

		try {
			String sql = "select dept_id, dept_name from department ";
			ps=getDataSource().getConnection().prepareStatement(sql);
			rs=ps.executeQuery();
			System.out.println("inside try block of getAlldept()");

			Department dept = null;
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("dept_id");
					String name = rs.getString("dept_name");

					dept = new Department(id, name);
					list.add(dept);
				}
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
		System.out.println("Operation get all department done successfully");
		return list;
	}

}
