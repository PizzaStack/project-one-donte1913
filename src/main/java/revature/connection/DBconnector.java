package revature.connection;

import revature.models.Employee;
import revature.models.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBconnector {
    String url;
    String username;
    String password;
    public static Connection db;
    List<Employee> employees = new ArrayList<>();
    List<Manager> managers = new ArrayList<>();
    Manager manager;
    Employee employee;

    //Constructor initializes DB connection string parameters
    public DBconnector() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        url = "jdbc:postgresql://bankingappdb.ccwa0vat9pxu.us-east-2.rds.amazonaws.com:5432/postgres";
        username = "donte1913";
        password = "Baller210";
        //Create DB connection on new object creation

        try {
            //Try to establish connection with DB
            db = DriverManager.getConnection(url, username, password);
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }
/*

	public List<Employee> getEmployees(){
		List<Employee> employees = new ArrayList<>();
		Employee employee;

		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT * FROM public.employees ORDER BY firstname;");
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
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT * FROM Employee WHERE EmployeeId = ?;");
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
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("UPDATE Employee SET FirstName = ?, LastName = ?, EmailAddress = ?, Address = ? WHERE EmployeeId = ?;");
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getEmailAddress());
			ps.setString(4, employee.getAddress());
			ps.setInt(5, employee.getEmployeeId());
			ps.executeUpdate();
		} catch(SQLException e) {

		}
	}
*/

}//EoC
