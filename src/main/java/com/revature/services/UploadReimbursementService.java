package com.revature.services;

import com.revature.connection.DBConnector;
import com.revature.models.Reimbursement;

public class UploadReimbursementService {
	private DBConnector conn = new DBConnector();

	public UploadReimbursementService() {
	}

	public boolean addReimbursement(Reimbursement reimbursement) {
		boolean addStatus = false;
		int reimbursementId = this.conn.addReimbursement(reimbursement);
		if (reimbursementId > 0) {
			addStatus = true;
		}

		return addStatus;
	}
}
