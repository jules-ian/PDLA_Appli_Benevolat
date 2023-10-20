package controller;
import model.model.*;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {

    public Connection connection;

    public DBManager(Connection connection){
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

    public void add_user(User user) {
        //private Connection connect ;

        //TODO: Mettre tout les attributs de asker avec les get
        String insertQuery = "INSERT INTO users (id,nom,prenom,age,type) VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, user.getUid());
            preparedStatement.setString(2, user.getNom());
            preparedStatement.setString(3, user.getPrenom());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getClass().getSimpleName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    //public ArrayList<Mission> getAs


    public User getUser(int id){
        String getQuery = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(getQuery);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int Uid = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                int age = resultSet.getInt("age");
                String type = resultSet.getString("type");

                return switch (type) {
                    case "Asker" -> new Asker(nom, prenom, age, Uid);
                    case "Volunteer" -> new Volunteer(nom, prenom, age, Uid);
                    case "Admin" -> new Admin(nom, prenom, age, Uid);
                    default -> throw new RuntimeException("Unrecognised type " + type);
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }
}
