package com.revature.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/getReceipt"})
public class ReceiptServlet extends HttpServlet {
	private InputStream is;

	public ReceiptServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream os = response.getOutputStream();
		byte[] buf = new byte[1024];
		boolean var5 = false;

		int count;
		while((count = this.is.read(buf)) != -1) {
			os.write(buf, 0, count);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		String line = "";

		String path;
		for(path = ""; (line = br.readLine()) != null; path = line) {
		}

		File file = new File(path);
		this.is = new FileInputStream(file);
	}
}
