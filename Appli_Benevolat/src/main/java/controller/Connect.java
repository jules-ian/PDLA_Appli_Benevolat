package controller;
import java.sql.*;

public class Connect {
    private static final String jdbcURL = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/";
    private static final String username = "projet_gei_034";
    private static final String password = "Ohphie5u";
    //private static connection connect;
    private Connection myConnection ;
    public void setConnection() throws SQLException {
        myConnection = DriverManager.getConnection(jdbcURL,username,password );
    }
    public Connection getConnection() {
        return this.myConnection;
    }

}
