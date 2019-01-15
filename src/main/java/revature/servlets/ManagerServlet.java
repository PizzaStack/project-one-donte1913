package revature.servlets;/*
*
* Expense Reimbursement System(ERS)
        User stories:
            -An revature.Employee can login
            -An revature.Employee can view the revature.Employee Homepage
            -An revature.Employee can logout
            -An revature.Employee can submit a reimbursement request
            -An revature.Employee can upload an image of his/her receipt as part of the reimbursement request
            -An revature.Employee can view their pending reimbursement requests
            -An revature.Employee can view their resolved reimbursement requests
            -An revature.Employee can view their information
            -An revature.Employee can update their information
            -An revature.Employee receives an email when one of their reimbursement requests is resolved(optional)

            -A revature.Manager can login
            -A revature.Manager can view the revature.Manager Homepage
            -A revature.Manager can logout
            -A revature.Manager can approve/deny pending reimbursement requests
            -A revature.Manager can view all pending requests from all employees
            -A revature.Manager can view images of the receipts from reimbursement requests
            -A revature.Manager can view all resolved requests from all employees and see which manager resolved it
            -A revature.Manager can view all Employees
            -A revature.Manager can view reimbursement requests from a single revature.Employee
*
* */

import revature.services.EmployeeService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        PrintWriter out = response.getWriter();
        out.write(fanficService.getAllFanfics().toString());
        */
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeService employeeService = new EmployeeService();
        String username = "";
        String password = "";

        if (request.getParameter("musername") != null)
            username = request.getParameter("musername");
        if (request.getParameter("mpswrd") != null)
            password = request.getParameter("mpswrd");

        System.out.println(username);
        System.out.println(password);

        if (employeeService.getCheckEmployee(username, password)) {
            //TODO valid manager username and password entered, redirect to manager home page
        }

        /*String title = request.getParameter("title");
        PrintWriter output = response.getWriter();
        output.write("POST " + title);*/
    }
}
