package controller;
import java.sql.*;

public class Connect {
    private static final String jdbcURL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/";
    private static final String username = "projet_gei_034";
    private static final String password = "Ohphie5u";
    //private static connection connect;
    private Connection myConnection ;
    public Connect() {
        try {
            myConnection = DriverManager.getConnection(jdbcURL,username,password );
            Statement statement = myConnection.createStatement();
            statement.execute("USE projet_gei_034");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection() {
        return this.myConnection;
    }

}
