package com.revature.servlets;

import com.revature.models.Verifyn;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.services.VerifynService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class VerifynServlet extends HttpServlet {

	private VerifynService verifynService = new VerifynService();
	private String verified;
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String address;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}

	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		BufferedReader br = request.getReader();
		String line = "";
		String credentials = "";
		String username = "";
		String password = "";
		String type = "";

		while((line = br.readLine()) != null) {
			credentials = line;
		}

		if(credentials.indexOf(",") > 0) {
			username = credentials.substring(0,credentials.indexOf(","));
			credentials = credentials.substring(credentials.indexOf(",") + 1, credentials.length());
			password = credentials.substring(0,credentials.indexOf(","));
			
			if(credentials.indexOf(",") > 0) {
				type = credentials.substring(credentials.indexOf(",") + 1, credentials.length());
			}
		}
		
		Verifyn verify = new Verifyn(username, password);
		
		if(type.equals("employee")) {
			List<String> employeeInformation = verifynService.verifyEmployee(verify);
			verified = employeeInformation.get(1);
			if(employeeInformation.get(1).equals("true")) {
				Employee employee = verifynService.getEmployeeInformation(username, password);
				id = employee.getEmployeeId();
				firstName = employee.getFirstName();
				lastName = employee.getLastName();
				emailAddress = employee.getEmailAddress();
				address = employee.getAddress();
			}
		} else if(type.equals("manager")){
			List<String> managerInformation = verifynService.verifyManager(verify);
			verified = managerInformation.get(1);
			if(managerInformation.get(1).equals("true")) {
				Manager manager = verifynService.getManagerInformation(username, password);
				id = manager.getManagerId();
				firstName = manager.getFirstName();
				lastName = manager.getLastName();
				emailAddress = manager.getEmailAddress();
				address = manager.getAddress();
			}
		}
		
		PrintWriter output = response.getWriter();
		JSONObject userInformation = new JSONObject();
		userInformation.put("verified", verified);
		userInformation.put("id", id);
		userInformation.put("firstname", firstName);
		userInformation.put("lastname", lastName);
		userInformation.put("emailaddress", emailAddress);
		userInformation.put("address", address);
		output.print(userInformation);
		
		
	}
	

	
}
