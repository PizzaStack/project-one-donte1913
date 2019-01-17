package com.revature.services;

import com.revature.connection.DBConnector;
import com.revature.models.Reimbursement;

public class ReimbursementApprovalService {
	private DBConnector conn;
	Reimbursement reimbursement;
	
	public ReimbursementApprovalService() {
		conn = new DBConnector();
		reimbursement = new Reimbursement();
	}

	public boolean approveReimbursementRequest(int reimbursementId, int managerId) {
		boolean approvalStatus = false;
		reimbursement = conn.getReimbursementById(reimbursementId);
		reimbursement.setStatus(1);
		reimbursement.setManagerId(managerId);
		conn.updateReimbursement(reimbursement);
		Reimbursement changedReimbursement = conn.getReimbursementById(reimbursementId);
		if(reimbursement.getStatus() == changedReimbursement.getStatus() && reimbursement.getManagerId() == changedReimbursement.getManagerId()) {
			approvalStatus = true;
		}
		return approvalStatus;
	}

	public boolean denyReimbursementRequest(int reimbursementId) {
		boolean approvalStatus = false;
		reimbursement = conn.getReimbursementById(reimbursementId);
		conn.deleteReimbursement(reimbursement);
		Reimbursement changedReimbursement = conn.getReimbursementById(reimbursementId);
		if(changedReimbursement == null) {
			approvalStatus = true;
		}
		return approvalStatus;
	}

}
