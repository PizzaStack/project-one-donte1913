package revature.servlets;

import org.json.JSONObject;
import revature.models.*;
import revature.services.VerifyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class VerifyServlet {

    private VerifyService vs = new VerifyService();
    private String verified = "false";
    private String firstName;
    private String lastName;
    private String username;
    private String password;


    private void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        Verify v = new Verify(username, password);
        if(type.equals("employee")) {
            List<String> employeeInformation = VerifyService.verifyEmployee(v);
            verified = employeeInformation.get(1);
            if(employeeInformation.get(1).equals("true")) {
                Employee employee = VerifyService.getEmployeeInformation(username, password);

                firstName = employee.getFirstName();
                lastName = employee.getLastName();

            }
        } else if(type.equals("manager")){
            List<String> managerInformation = VerifyService.verifyManager(v);
            verified = managerInformation.get(1);
            if(managerInformation.get(1).equals("true")) {
                Manager manager = VerifyService.getManagerInformation(username, password);
                firstName = manager.getFirstName();
                lastName = manager.getLastName();

            }
        }
        PrintWriter output = response.getWriter();
        JSONObject userInformation = new JSONObject();
        //userInformation.put("auth", auth);
        userInformation.put("firstname", firstName);
        userInformation.put("lastname", lastName);

        output.print(userInformation);
    }
}//eoc
