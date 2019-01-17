package com.revature.connection;

import com.revature.models.Verifyn;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class
DBConnector {
	public static Connection db;
	private String username;
	private String password;
	private String url;

	private Reimbursement reimbursement;
	private List<Reimbursement> reimbursements;


	public DBConnector() {
		reimbursement = new Reimbursement();
		reimbursements = new ArrayList<>();
	}


	public void createConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		url = "jdbc:postgresql://bankingappdb.ccwa0vat9pxu.us-east-2.rds.amazonaws.com:5432/postgres";
		username = "donte1913";
		password = "Baller210";
		//Create DB connection on new object creation

		try {
			//Try to establish connection with DB
			db = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	//Used to close the actual DB connection
	public void closeConnection() {
		try {
			if(db != null) {
			db.close();
			db = null;
			}
		} catch (SQLException e) {
			
		}
	}


	public List<Employee> getEmployees(){
		List<Employee> employees = new ArrayList<>();
		Employee employee;

		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("SELECT * FROM Employee ORDER BY EmployeeId;");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				employee = new Employee(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6)
				);
				employee.setEmployeeId(rs.getInt(1));
				employees.add(employee);
			}
		} catch(SQLException e) {

		}

		return employees;
	}

	public Employee getEmployeeById(int employeeId) {
		Employee employee = null;

		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("SELECT * FROM Employee WHERE EmployeeId = ?;");
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				employee = new Employee(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6)
				);
				employee.setEmployeeId(rs.getInt(1));
			}
		} catch(SQLException e) {

		}

		return employee;
	}

	public void updateEmployee(Employee employee) {
		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("UPDATE Employee SET FirstName = ?, LastName = ?, EmailAddress = ?, Address = ? WHERE EmployeeId = ?;");
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getEmailAddress());
			ps.setString(4, employee.getAddress());
			ps.setInt(5, employee.getEmployeeId());
			ps.executeUpdate();
		} catch(SQLException e) {

		}
	}



	public List<Manager> getManagers(){
		List<Manager> managers = new ArrayList<>();
		Manager manager;

		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("SELECT * FROM Manager;");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				manager = new Manager(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6)
				);
				manager.setManagerId(rs.getInt(1));
				managers.add(manager);
			}
		} catch(SQLException e) {

		}

		return managers;
	}


	public Manager getManagerById(int managerId) {
		Manager manager = null;

		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("SELECT * FROM Manager WHERE ManagerId = ?;");
			ps.setInt(1, managerId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				manager = new Manager(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6)
				);
				manager.setManagerId(rs.getInt(1));
			}
		} catch(SQLException e) {

		}

		return manager;
	}

	public List<Verifyn> getEmployeeAccounts(){
		List<Verifyn> accounts = new ArrayList<>();

		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("SELECT eu.UserId, eu.EmployeeId, eu.Username, eu.Password, eu.Status, CONCAT(e.FirstName,' ',e.LastName)\r\n" +
					"FROM EmployeeUsers eu\r\n" +
					"JOIN Employee e\r\n" +
					"ON eu.EmployeeId = e.EmployeeId;");
			ResultSet rs = ps.executeQuery();
			Verifyn verify;
			while(rs.next()) {
				verify = new Verifyn(
						rs.getString(3),
						rs.getString(4)
				);
				verify.setReferenceId(rs.getInt(2));
				verify.setUserId(rs.getInt(1));
				verify.setStatus(rs.getInt(5));
				verify.setFullName(rs.getString(6));
				accounts.add(verify);
			}
		} catch(SQLException e) {

		}

		return accounts;
	}

	public List<Verifyn> getManagerAccounts(){
		List<Verifyn> accounts = new ArrayList<>();

		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("SELECT mu.UserId, mu.ManagerId, mu.Username, mu.Password, mu.Status, CONCAT(m.FirstName,' ',m.LastName)\r\n" +
					"FROM ManagerUsers mu\r\n" +
					"JOIN Manager m\r\n" +
					"ON mu.ManagerId = m.ManagerId;");
			ResultSet rs = ps.executeQuery();
			Verifyn verify;
			while(rs.next()) {
				verify = new Verifyn(
						rs.getString(3),
						rs.getString(4)
				);
				verify.setReferenceId(rs.getInt(2));
				verify.setUserId(rs.getInt(1));
				verify.setStatus(rs.getInt(5));
				verify.setFullName(rs.getString(6));
				accounts.add(verify);
			}
		} catch(SQLException e) {

		}

		return accounts;
	}


	public int addReimbursement(Reimbursement reimbursement) {
		int reimbursementId = 0;

		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("INSERT INTO Reimbursement(EmployeeId, Status, Title, Description, Amount, ReimbursementDate, ReceiptLocation) VALUES(?,0,?,?,?,?,?);");
			ps.setInt(1, reimbursement.getEmployeeId());
			ps.setString(2, reimbursement.getTitle());
			ps.setString(3, reimbursement.getDescription());
			ps.setDouble(4, reimbursement.getAmount());
			ps.setDate(5, sqlDate);
			ps.setString(6, reimbursement.getReceiptLocation());
			ps.executeUpdate();

			ps = DBConnector.db.prepareStatement("SELECT ReimbursementId FROM Reimbursement WHERE EmployeeId = ? AND Status = ? AND Title = ? AND Description = ? AND Amount = ? AND ReimbursementDate = ? AND ReceiptLocation = ?;");
			ps.setInt(1, reimbursement.getEmployeeId());
			ps.setInt(2, 0);
			ps.setString(3, reimbursement.getTitle());
			ps.setString(4, reimbursement.getDescription());
			ps.setDouble(5, reimbursement.getAmount());
			ps.setDate(6, sqlDate);
			ps.setString(7, reimbursement.getReceiptLocation());
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				reimbursementId = rs.getInt(1);
			}
		} catch(SQLException e) {
			System.out.println(e);
		}

		return reimbursementId;
	}

	public Reimbursement getReimbursementById(int reimbursementId) {
		reimbursement = null;

		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("SELECT * FROM Reimbursement WHERE ReimbursementId = ?;");
			ps.setInt(1, reimbursementId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				reimbursement = new Reimbursement(
						rs.getInt(2),
						rs.getString(5),
						rs.getString(6),
						rs.getDouble(7),
						rs.getString(9));
				reimbursement.setReimbursementId(rs.getInt(1));
				if(rs.getString(3) != null) {
					reimbursement.setManagerId(Integer.parseInt(rs.getString(3)));
				}
				reimbursement.setStatus(rs.getInt(4));
				reimbursement.setReimbursementDate(rs.getString(8));
			}


		} catch(SQLException e) {

		}

		return reimbursement;
	}

	public List<Reimbursement> getAllReimbursements() {
		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("SELECT * FROM Reimbursement ORDER BY Status, ReimbursementId;");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				reimbursement = new Reimbursement(rs.getInt(2),
						rs.getString(5),
						rs.getString(6),
						rs.getDouble(7),
						rs.getString(9));
				reimbursement.setReimbursementId(rs.getInt(1));
				reimbursement.setManagerId(rs.getInt(3));
				reimbursement.setStatus(rs.getInt(4));
				reimbursement.setReimbursementDate(rs.getString(8));
				reimbursements.add(reimbursement);
			}

		} catch(SQLException e) {

		}

		return reimbursements;
	}

	public void updateReimbursement(Reimbursement reimbursement) {
		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("UPDATE Reimbursement SET ManagerId = ?, Status = ? WHERE ReimbursementId = ?;");
			ps.setInt(1, reimbursement.getManagerId());
			ps.setInt(2, reimbursement.getStatus());
			ps.setInt(3, reimbursement.getReimbursementId());
			ps.executeUpdate();

		} catch (SQLException e) {

		}

	}

	public void deleteReimbursement(Reimbursement reimbursement) {
		try {
			PreparedStatement ps = DBConnector.db.prepareStatement("DELETE FROM Reimbursement WHERE ReimbursementId = ?;");
			ps.setInt(1, reimbursement.getReimbursementId());
			ps.executeUpdate();
		} catch (SQLException e) {

		}
	}


}//Eoc
