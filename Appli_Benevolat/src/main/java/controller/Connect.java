package controller;
import java.sql.*;

/** Used to connect to a specific database */
public class Connect {
    private static final String jdbcURL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/";
    private static final String username = "projet_gei_034";
    private static final String password = "Ohphie5u"; // very very bad for security :(
    private final Connection myConnection ;
    public Connect() throws SQLException {
            myConnection = DriverManager.getConnection(jdbcURL,username,password );
            Statement statement = myConnection.createStatement();
            statement.execute("USE projet_gei_034");
    }
    public Connection getConnection() {
        return this.myConnection;
    }

}








