package controller;
import model.model.*;
import java.sql.*;
//TODO:++ Faire un DBManager qui regroupe DBinit et new user
public class NewAsker {
    private Connection connect ;
    public void setConnection(Connection connect){
        this.connect = connect;
    }

    public void insertAsker(Asker asker) {
        //TODO: Mettre tout les attributs de asker avec les get
        String insertQuery = "INSERT INTO asker (username,) VALUES (a1)";
        try (PreparedStatement preparedStatement = connect.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, asker.getNom());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
