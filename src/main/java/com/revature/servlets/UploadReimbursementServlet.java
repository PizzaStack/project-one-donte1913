package com.revature.servlets;

import com.revature.models.Reimbursement;
import com.revature.services.UploadReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;

@MultipartConfig
public class UploadReimbursementServlet extends HttpServlet {
	private boolean addStatus;
		
		protected void doGet(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {

		}
	
	
	    protected void doPost(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {

	    	
	        String title = request.getParameter("titleBox"); 
	        String description = request.getParameter("description");
	        Double amount = Double.parseDouble(request.getParameter("amount"));
	        int employeeId = Integer.parseInt(request.getParameter("employeeid"));
	        Part filePart = request.getPart("file");
	        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	        InputStream fileContent = filePart.getInputStream();

	        	new File("C:\\Users\\Owner\\Desktop\\Project-1-Servlet\\Reimbursements\\EmployeeId" + employeeId + "Receipts").mkdir();
	    		FileOutputStream fos = null;
	    		
	        	try {
	    		fos = new FileOutputStream("C:\\Users\\Owner\\Desktop\\Project-1-Servlet\\Reimbursements\\EmployeeId" + employeeId + "Receipts\\" + fileName);
	        	} catch(FileNotFoundException e) {
	        	
	        	}
	    		byte[] buf = new byte[512];
	    		int num;
	    		
	    		while ((num = fileContent.read(buf)) != -1) {
	    			fos.write(buf, 0, num);
	    		}
	    		
	    		UploadReimbursementService uploadReimbursementService = new UploadReimbursementService();
	    		Reimbursement reimbursement = new Reimbursement(employeeId,title,description,amount,"C:\\Users\\Owner\\Desktop\\Project-1-Servlet\\Reimbursements\\EmployeeId" + employeeId + "Receipts\\" + fileName);
	    		addStatus = uploadReimbursementService.addReimbursement(reimbursement);	
	    		
	    		PrintWriter output = response.getWriter();
	    		output.write(String.valueOf(addStatus));
	    
	    }
	    
}
