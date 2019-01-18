
package com.revature.services;

import com.revature.connection.DBConnector;
import com.revature.models.Reimbursement;

public class ReimbursementApprovalService {
	private DBConnector conn = new DBConnector();
	Reimbursement reimbursement = new Reimbursement();

	public ReimbursementApprovalService() {
	}

	public boolean approveReimbursementRequest(int reimbursementId, int managerId) {
		boolean approvalStatus = false;
		this.reimbursement = this.conn.getReimbursementById(reimbursementId);
		this.reimbursement.setStatus(1);
		this.reimbursement.setManagerId(managerId);
		this.conn.updateReimbursement(this.reimbursement);
		Reimbursement changedReimbursement = this.conn.getReimbursementById(reimbursementId);
		if (this.reimbursement.getStatus() == changedReimbursement.getStatus() && this.reimbursement.getManagerId() == changedReimbursement.getManagerId()) {
			approvalStatus = true;
		}

		return approvalStatus;
	}

	public boolean denyReimbursementRequest(int reimbursementId) {
		boolean approvalStatus = false;
		this.reimbursement = this.conn.getReimbursementById(reimbursementId);
		this.conn.deleteReimbursement(this.reimbursement);
		Reimbursement changedReimbursement = this.conn.getReimbursementById(reimbursementId);
		if (changedReimbursement == null) {
			approvalStatus = true;
		}

		return approvalStatus;
	}
}
