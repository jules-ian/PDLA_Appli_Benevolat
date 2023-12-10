package controller;
import exceptions.UserNotFoundException;
import model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/** This class contains all the SQL requests that can be made on the DB */
public class DBManager {

    public Connection connection;

    public DBManager(Connection connection){
        this.connection = connection;
    }
    private static final Logger LOGGER = LogManager.getLogger(DBManager.class);

    /** Deletes the table users */
    public void removeUserTable() throws SQLException {
            Statement statement = this.connection.createStatement();
            String removeTableSQL = "DROP TABLE users";
            statement.executeUpdate(removeTableSQL);
            LOGGER.info("Table users supprimée avec succès.");
    }

    /** Deletes the table missions */
    public void removeMissionTable() throws SQLException {
            Statement statement = this.connection.createStatement();
            String removeTableSQL = "DROP TABLE missions";
            statement.executeUpdate(removeTableSQL);
            LOGGER.info("Table missions supprimée avec succès.");
    }

    /** Deletes all the tables */
    public void resetDB(){

        try {
            removeMissionTable();
        }catch (SQLException e) {
            LOGGER.trace("Could not delete mission table");
            // Normal if tables are already deleted
        }

        try {
            removeUserTable();
        }catch (SQLException e) {
            LOGGER.trace("Could not delete user table");
            // Normal if tables are already deleted
        }
    }

    /** Creates the tables users and missions */
    public void createTables() throws SQLException {
        createUserTable();
        createMissionTable();
    }
    /** Creates the table users */
    public void createUserTable() throws SQLException { // DB avec tous les utilisateurs => champ type pour identifier Asker, Volunteer, Admin
            Statement statement = this.connection.createStatement();
            String createTableSQL = "CREATE TABLE users ("
                    + "id INT PRIMARY KEY,"
                    + "nom VARCHAR(50) NOT NULL,"
                    + "prenom VARCHAR(50) NOT NULL,"
                    + "age INT NOT NULL,"
                    + "type VARCHAR(10) NOT NULL)";
            statement.executeUpdate(createTableSQL);
            LOGGER.info("Table users créée avec succès.");
    }

    /** Creates the table missions */
    public void createMissionTable() throws SQLException {
            Statement statement = this.connection.createStatement();
            String createTableSQL = "CREATE TABLE missions ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "description VARCHAR(255) NOT NULL,"
                    + "askerID INT NOT NULL,"
                    + "volunteerID INT,"
                    + "FOREIGN KEY (askerID) REFERENCES users(id) ON DELETE CASCADE)";
            statement.executeUpdate(createTableSQL);
            LOGGER.info("Table missions créée avec succès.");
    }

    /** Adds a user to the DB */
    public void addUser(User user) throws SQLException {
        String insertQuery = "INSERT INTO users (id,nom,prenom,age,type) VALUES (?,?,?,?,?)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, user.getUid());
            preparedStatement.setString(2, user.getNom());
            preparedStatement.setString(3, user.getPrenom());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getClass().getSimpleName());

            preparedStatement.executeUpdate();
    }

    /** Adds a mission to the DB */
    public void addMission(Mission mission) throws SQLException {
        if(mission.getVolunteerID() == -1){
            String insertQuery = "INSERT INTO missions (description,askerID) VALUES (?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, mission.getDescription());
            preparedStatement.setInt(2, mission.getAskerID());
            preparedStatement.executeUpdate();
        }else{
            String insertQuery = "INSERT INTO missions (description,askerID,volunteerID) VALUES (?,?,?)";
            PreparedStatement preparedStatement = this.connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, mission.getDescription());
            preparedStatement.setInt(2, mission.getAskerID());
            preparedStatement.setInt(3, mission.getVolunteerID());
            preparedStatement.executeUpdate();
        }
    }


    /** Gets a Mission from the DB by ID*/
    public Mission getMission(int id) throws SQLException {
        String getQuery = "SELECT * FROM missions WHERE id = ?";
            PreparedStatement preparedStatement = this.connection.prepareStatement(getQuery);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int Mid = resultSet.getInt("id");
                String desc = resultSet.getString("description");
                int Aid = resultSet.getInt("askerID");
                int Vid = resultSet.getInt("volunteerID");

                return new Mission(desc, Aid, Vid, Mid);
            }

        return null;
    }

    /** Gets all the Missions of the DB */
    public ArrayList<Mission> getAllMissions() throws SQLException {
        String getQuery = "SELECT * FROM missions";
        ArrayList<Mission> Qresult = new ArrayList<>();
            PreparedStatement preparedStatement = this.connection.prepareStatement(getQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Mid = resultSet.getInt("id");
                String desc = resultSet.getString("description");
                int Aid = resultSet.getInt("askerID");
                int Vid = resultSet.getInt("volunteerID");

                Qresult.add(new Mission(desc, Aid, Vid, Mid));
            }

        return Qresult;
    }

    /** Gets a User from the DB by ID*/
    public User getUser(int id) throws SQLException, UserNotFoundException {
        String getQuery = "SELECT * FROM users WHERE id = ?";
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
        }else{
            throw new UserNotFoundException(id);
        }
    }

    /** Gets all the Missions asked by a specific Asker */
    public ArrayList<Mission> getMissionsOfAsker(int id) throws SQLException {
        String getQuery = "SELECT * FROM missions WHERE askerID = ?";
        ArrayList<Mission> result = new ArrayList<>();
            PreparedStatement preparedStatement = this.connection.prepareStatement(getQuery);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Mid = resultSet.getInt("id");
                String desc = resultSet.getString("description");
                int Aid = resultSet.getInt("askerID");
                int Vid = resultSet.getInt("volunteerID");

                result.add(new Mission(desc, Aid, Vid, Mid));
            }

        return result;
    }
}
