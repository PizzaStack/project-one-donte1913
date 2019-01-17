package com.revature.servlets;

import com.revature.connection.DBConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class ConnectionServlet extends HttpServlet {
	private DBConnector conn = new DBConnector();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		
		String command = "";
		String line = "";
		
		while((command = br.readLine()) != null) {
			line = command;
		}
		
		
		
		if(line.equals("OFF")) {
			if(DBConnector.db != null) {
				conn.closeConnection();
				System.out.println(line);
				response.sendRedirect("http://localhost:8080/Project_1_Servlet_war/index.html");
			}
		}
		

		

	}
}
