package revature.services;

import revature.connection.DBconnector;

import java.sql.SQLException;

//Class that handles the communication to the DB and the
public class EmployeeService {

    public boolean getCheckEmployee(String username, String password) {
        boolean checkEmployeeResult = false;

        DBconnector conn = new DBconnector();
        try {
            if (conn.checkEmployee(username,password))
                checkEmployeeResult = true;

            DBconnector.db.close();
            return checkEmployeeResult;
        } catch (SQLException e) {
            e.getMessage();
        }

        return checkEmployeeResult; //Returns false by default
    }//End of getCheckEmployee


}
