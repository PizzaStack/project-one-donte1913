package com.revature.services;

import com.revature.connection.DBConnector;
import com.revature.models.Reimbursement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DownloadReimbursementService {
	private Reimbursement reimbursement = new Reimbursement();
	private List<Reimbursement> reimbursements = new ArrayList();
	private DBConnector conn = new DBConnector();

	public DownloadReimbursementService() {
	}

	public List<Reimbursement> getPendingReimbursementsByEmployeeId(int id) {
		this.reimbursements = this.conn.getAllReimbursements();
		List<Reimbursement> reimbursementsById = new ArrayList();
		Iterator it = this.reimbursements.iterator();

		while(it.hasNext()) {
			Reimbursement reimbursement = (Reimbursement)it.next();
			if (reimbursement.getEmployeeId() == id && reimbursement.getStatus() == 0) {
				reimbursementsById.add(reimbursement);
			}
		}

		return reimbursementsById;
	}

	public List<Reimbursement> getResolvedReimbursementsByEmployeeId(int id) {
		this.reimbursements = this.conn.getAllReimbursements();
		List<Reimbursement> reimbursementsById = new ArrayList();
		Iterator it = this.reimbursements.iterator();

		while(it.hasNext()) {
			Reimbursement reimbursement = (Reimbursement)it.next();
			if (reimbursement.getEmployeeId() == id && reimbursement.getStatus() == 1) {
				reimbursementsById.add(reimbursement);
			}
		}

		return reimbursementsById;
	}

	public List<Reimbursement> getAllReimbursementsByEmployeeId(int id) {
		this.reimbursements = this.conn.getAllReimbursements();
		List<Reimbursement> reimbursementsById = new ArrayList();
		Iterator it = this.reimbursements.iterator();

		while(it.hasNext()) {
			Reimbursement reimbursement = (Reimbursement)it.next();
			if (reimbursement.getEmployeeId() == id) {
				reimbursementsById.add(reimbursement);
			}
		}

		return reimbursementsById;
	}
}