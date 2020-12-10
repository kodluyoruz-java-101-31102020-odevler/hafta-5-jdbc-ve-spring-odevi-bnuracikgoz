package db.connection.mysql.connection.dao;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import db.connection.mysql.connection.DbSQLQuery;
import db.connection.mysql.connection.model.Employee;
import db.connection.mysql.connection.model.Manager;

public class ManagerDAO {

	public List<Manager> loadAllActiveManagers() {
		String dpt_name;
		
		List<Manager> managers = new ArrayList<Manager>();
	
		String sql = " SELECT emp.* , dp.dept_name FROM employees emp LEFT JOIN dept_manager dm on dm.emp_no = emp.emp_no " +
		" left join departments dp on dp.dept_no = dm.dept_no where dm.to_date >= " 
		+ new java.sql.Date(new java.util.Date().getTime());

		ResultSet resultSet=DbSQLQuery.select(sql);

		try {
			
			
			if(resultSet==null) {
			return managers;	
			}
			
			while (resultSet.next()) {
				
				Employee employee = new Employee();
				
				employee.setId(resultSet.getLong("emp_no"));
				employee.setName(resultSet.getString("first_name"));
				employee.setLastName(resultSet.getString("last_name"));
				employee.setGender(resultSet.getString("gender"));
				employee.setBirthDate(resultSet.getDate("birth_date"));
				employee.setHireDate(resultSet.getDate("hire_date"));
				
				
				dpt_name = resultSet.getString("dept_name");
				Manager manager = new Manager(employee, dpt_name);
				
				managers.add(manager);
				
			}
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());}
		
		
		return managers;
	}
	
}
