
package com.revature.servlets;

import com.revature.models.Reimbursement;
import com.revature.services.DownloadAllReimbursementService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet({"/allReimbursement"})
public class DownloadAllReimbursementServlet extends HttpServlet {
    public DownloadAllReimbursementServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String type = "";

        for(String line = ""; (line = br.readLine()) != null; type = line) {
        }

        JSONArray jsonArray = new JSONArray();
        new JSONObject();
        JSONObject json;
        DownloadAllReimbursementService downloadAllReimbursementService;
        List resolvedReimbursements;
        Iterator it;
        Reimbursement newReimbursement;
        if (type.equals("pending")) {
            downloadAllReimbursementService = new DownloadAllReimbursementService();
            resolvedReimbursements = downloadAllReimbursementService.getAllPendingReimbursements();
            it = resolvedReimbursements.iterator();

            while(it.hasNext()) {
                newReimbursement = (Reimbursement)it.next();
                json = new JSONObject();
                json.put("reimbursementid", newReimbursement.getReimbursementId());
                json.put("employeeid", newReimbursement.getEmployeeId());
                json.put("managerid", newReimbursement.getManagerId());
                json.put("status", newReimbursement.getStatus());
                json.put("title", newReimbursement.getTitle());
                json.put("description", newReimbursement.getDescription());
                json.put("amount", newReimbursement.getAmount());
                json.put("date", newReimbursement.getReimbursementDate());
                json.put("location", newReimbursement.getReceiptLocation());
                jsonArray.put(json);
            }
        } else if (type.equals("resolved")) {
            downloadAllReimbursementService = new DownloadAllReimbursementService();
            resolvedReimbursements = downloadAllReimbursementService.getAllResolvedReimbursements();
            it = resolvedReimbursements.iterator();

            while(it.hasNext()) {
                newReimbursement = (Reimbursement)it.next();
                json = new JSONObject();
                json.put("reimbursementid", newReimbursement.getReimbursementId());
                json.put("employeeid", newReimbursement.getEmployeeId());
                json.put("managerid", newReimbursement.getManagerId());
                json.put("status", newReimbursement.getStatus());
                json.put("title", newReimbursement.getTitle());
                json.put("description", newReimbursement.getDescription());
                json.put("amount", newReimbursement.getAmount());
                json.put("date", newReimbursement.getReimbursementDate());
                json.put("location", newReimbursement.getReceiptLocation());
                jsonArray.put(json);
            }
        }

        PrintWriter output = response.getWriter();
        output.print(jsonArray);
    }
}
