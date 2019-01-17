package com.revature.servlets;

import com.revature.models.Reimbursement;
import com.revature.services.DownloadAllReimbursementService;
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

@WebServlet("/allReimbursement")
public class DownloadAllReimbursementServlet extends HttpServlet {

}
