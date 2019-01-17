package com.revature.services;

import com.revature.connection.DBConnector;
import com.revature.models.Reimbursement;

import java.util.ArrayList;
import java.util.List;

public class DownloadAllReimbursementService {
	private Reimbursement reimbursement;
	private List<Reimbursement> reimbursements;
	private DBConnector conn;

	public DownloadAllReimbursementService() {
		reimbursement = new Reimbursement();
		conn = new DBConnector();
		reimbursements = new ArrayList<>();
	}

	
	
	
}
