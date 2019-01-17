package com.revature.servlets;

import com.revature.models.Reimbursement;
import com.revature.services.DownloadReimbursementService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/downloadReimbursementServlet")
public class DownloadReimbursementServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader br = request.getReader();

		String line = "";
		int employeeId = 0;
		String type = "";
		int count = 0;

		while ((line = br.readLine()) != null) {
			employeeId = Integer.parseInt(line.substring(0, line.indexOf(",")));
			type = line.substring(line.indexOf(",") + 1, line.length());
		}

}}
