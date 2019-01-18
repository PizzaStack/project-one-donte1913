package com.revature.services;

import com.revature.connection.DBConnector;
import com.revature.models.Reimbursement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DownloadAllReimbursementService {
	private Reimbursement reimbursement = new Reimbursement();
	private List<Reimbursement> reimbursements = new ArrayList();
	private DBConnector conn = new DBConnector();

	public DownloadAllReimbursementService() {
	}

	public List<Reimbursement> getAllPendingReimbursements() {
		List<Reimbursement> pendingReimbursements = new ArrayList();
		this.reimbursements = this.conn.getAllReimbursements();
		Iterator it = this.reimbursements.iterator();

		while(it.hasNext()) {
			Reimbursement reimbursement = (Reimbursement)it.next();
			if (reimbursement.getStatus() == 0) {
				pendingReimbursements.add(reimbursement);
			}
		}

		return pendingReimbursements;
	}

	public List<Reimbursement> getAllResolvedReimbursements() {
		List<Reimbursement> resolvedReimbursements = new ArrayList();
		this.reimbursements = this.conn.getAllReimbursements();
		Iterator it = this.reimbursements.iterator();

		while(it.hasNext()) {
			Reimbursement reimbursement = (Reimbursement)it.next();
			if (reimbursement.getStatus() == 1) {
				resolvedReimbursements.add(reimbursement);
			}
		}

		return resolvedReimbursements;
	}
}
