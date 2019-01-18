
package com.revature.servlets;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Verifyn;
import com.revature.services.VerifynService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class VerifynServlet extends HttpServlet {
	private VerifynService verifynService = new VerifynService();
	private String verified;
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String address;

	public VerifynServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		String line = "";
		String credentials = "";
		String username = "";
		String password = "";

		String type;
		for(type = ""; (line = br.readLine()) != null; credentials = line) {
		}

		if (credentials.indexOf(",") > 0) {
			username = credentials.substring(0, credentials.indexOf(","));
			credentials = credentials.substring(credentials.indexOf(",") + 1, credentials.length());
			password = credentials.substring(0, credentials.indexOf(","));
			if (credentials.indexOf(",") > 0) {
				type = credentials.substring(credentials.indexOf(",") + 1, credentials.length());
			}
		}

		Verifyn verify = new Verifyn(username, password);
		List managerInformation;
		if (type.equals("employee")) {
			managerInformation = this.verifynService.verifyEmployee(verify);
			this.verified = (String)managerInformation.get(1);
			if (((String)managerInformation.get(1)).equals("true")) {
				Employee employee = this.verifynService.getEmployeeInformation(username, password);
				this.id = employee.getEmployeeId();
				this.firstName = employee.getFirstName();
				this.lastName = employee.getLastName();
				this.emailAddress = employee.getEmailAddress();
				this.address = employee.getAddress();
			}
		} else if (type.equals("manager")) {
			managerInformation = this.verifynService.verifyManager(verify);
			this.verified = (String)managerInformation.get(1);
			if (((String)managerInformation.get(1)).equals("true")) {
				Manager manager = this.verifynService.getManagerInformation(username, password);
				this.id = manager.getManagerId();
				this.firstName = manager.getFirstName();
				this.lastName = manager.getLastName();
				this.emailAddress = manager.getEmailAddress();
				this.address = manager.getAddress();
			}
		}

		PrintWriter output = response.getWriter();
		JSONObject userInformation = new JSONObject();
		userInformation.put("verified", this.verified);
		userInformation.put("id", this.id);
		userInformation.put("firstname", this.firstName);
		userInformation.put("lastname", this.lastName);
		userInformation.put("emailaddress", this.emailAddress);
		userInformation.put("address", this.address);
		output.print(userInformation);
	}
}
