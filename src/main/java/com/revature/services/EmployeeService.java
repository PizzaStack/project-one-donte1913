package com.revature.services;

import com.revature.connection.DBConnector;
import com.revature.models.Employee;

import java.util.List;

public class EmployeeService {

	private DBConnector conn = null;

	public EmployeeService() {
		conn.createConnection();
	}

	
	public List<Employee> getEmployees(){
		List<Employee> employees = conn.getEmployees();
		return employees;
	}
	
	public boolean updateEmployee(Employee employee) {
		boolean updateStatus = false;
		conn.updateEmployee(employee);
		Employee updatedEmployee = conn.getEmployeeById(employee.getEmployeeId());
		
		if(updatedEmployee.getFirstName().equals(employee.getFirstName()) && updatedEmployee.getLastName().equals(employee.getLastName())
				&& updatedEmployee.getEmailAddress().equals(employee.getEmailAddress()) && updatedEmployee.getAddress().equals(employee.getAddress())) {
			updateStatus = true;
		}
		return updateStatus;
	}
}
