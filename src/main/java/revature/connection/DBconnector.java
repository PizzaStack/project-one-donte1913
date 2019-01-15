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


}//EoC
