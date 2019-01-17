package com.revature.services;

import com.revature.connection.DBConnector;
import com.revature.models.Verifyn;
import com.revature.models.Employee;
import com.revature.models.Manager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VerifynService {
	private List<Verifyn> employeeAccounts;
	private List<Verifyn> managerAccounts;
	private List<String> employeeInfo;
	private List<String> managerInfo;
	private DBConnector conn;

	public VerifynService() {
	}

	public List<String> verifyEmployee(Verifyn verify) {
		this.conn = new DBConnector();
		this.conn.createConnection();
		this.employeeInfo = new ArrayList();
		boolean verified = false;
		boolean usernameAuthenticated = false;
		boolean passwordAuthenticated = false;
		int employeeId = 0;
		int status = 0;
		String password = "";
		this.employeeAccounts = new ArrayList();
		this.employeeAccounts = this.conn.getEmployeeAccounts();
		Iterator it = this.employeeAccounts.iterator();

		while(it.hasNext()) {
			Verifyn employeeAccount = (Verifyn)it.next();
			if (verify.getUsername().equals(employeeAccount.getUsername())) {
				usernameAuthenticated = true;
				password = employeeAccount.getPassword();
				if (password.equals(verify.getPassword())) {
					passwordAuthenticated = true;
					status = employeeAccount.getStatus();
					employeeId = employeeAccount.getReferenceId();
				}
			}
		}

		if (usernameAuthenticated && passwordAuthenticated && status == 1) {
			verified = true;
		}

		this.employeeInfo.add(String.valueOf(employeeId));
		this.employeeInfo.add(String.valueOf(verified));
		return this.employeeInfo;
	}

	public Employee getEmployeeInformation(String username, String password) {
		Verifyn verify = new Verifyn(username, password);
		VerifynService verifynService = new VerifynService();
		new ArrayList();
		List<String> employeeInfo = verifynService.verifyEmployee(verify);
		Employee employee = conn.getEmployeeById(Integer.parseInt((String)employeeInfo.get(0)));
		return employee;
	}

	public List<String> verifyManager(Verifyn verify) {
		this.conn = new DBConnector();
		this.conn.createConnection();
		this.managerInfo = new ArrayList();
		boolean verified = false;
		boolean usernameAuthenticated = false;
		boolean passwordAuthenticated = false;
		int managerId = 0;
		int status = 0;
		String password = "";
		this.managerAccounts = new ArrayList();
		this.managerAccounts = this.conn.getManagerAccounts();
		Iterator it = this.managerAccounts.iterator();

		while(it.hasNext()) {
			Verifyn managerAccount = (Verifyn)it.next();
			if (verify.getUsername().equals(managerAccount.getUsername())) {
				usernameAuthenticated = true;
				password = managerAccount.getPassword();
				if (password.equals(verify.getPassword())) {
					passwordAuthenticated = true;
					status = managerAccount.getStatus();
					managerId = managerAccount.getReferenceId();
				}
			}
		}

		if (usernameAuthenticated && passwordAuthenticated && status == 1) {
			verified = true;
		}

		this.managerInfo.add(String.valueOf(managerId));
		this.managerInfo.add(String.valueOf(verified));
		return this.managerInfo;
	}

	public Manager getManagerInformation(String username, String password) {
		Verifyn verify = new Verifyn(username, password);
		VerifynService verifynService = new VerifynService();
		new ArrayList();
		List<String> managerInfo = verifynService.verifyManager(verify);
		Manager manager = conn.getManagerById(Integer.parseInt((String)managerInfo.get(0)));
		return manager;
	}
}
