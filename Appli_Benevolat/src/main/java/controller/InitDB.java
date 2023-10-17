package controller;
import java.sql.*;

public class InitDB {

    public Connection connection;

    public InitDB(Connection connection){
        this.connection = connection;
    }


    public void create_user_db(){ // DB avec tous les utilisateurs => champ type pour identifier Asker, Volunteer, Admin
        try {
            Statement statement = this.connection.createStatement();
            String createTableSQL = "CREATE TABLE users ("
                    + "id INT PRIMARY KEY,"
                    + "nom VARCHAR(50) NOT NULL,"
                    + "prenom VARCHAR(50) NOT NULL,"
                    + "age INT NOT NULL,"
                    + "type VARCHAR(10) NOT NULL)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table users créée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create_mission_db(){ // DB avec toutes les missions
        try {
            Statement statement = this.connection.createStatement();
            String createTableSQL = "CREATE TABLE missions ("
                    + "id INT PRIMARY KEY,"
                    + "description VARCHAR(255) NOT NULL,"
                    + "askerID INT FOREIGN KEY NOT NULL,"
                    + "volunteerID INT FOREIGN KEY,";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table missions créée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
