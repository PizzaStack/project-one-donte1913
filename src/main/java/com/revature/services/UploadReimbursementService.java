package com.revature.services;

import com.revature.connection.DBConnector;
import com.revature.models.Reimbursement;

public class UploadReimbursementService {
	private DBConnector conn;
	
	public UploadReimbursementService() {
		conn = new DBConnector();
	}

	public boolean addReimbursement(Reimbursement reimbursement) {
		boolean addStatus = false;
		int reimbursementId = conn.addReimbursement(reimbursement);
		if(reimbursementId > 0) {
			addStatus = true;
		}
		return addStatus;
	}

}
