import controller.*;
import view.GUI;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Connect co = null;
        try {
            co = new Connect();
        } catch (SQLException e) {
            System.out.println("Unable to establish connection");
            System.exit(1);
        }
        DBManager db = new DBManager(co.getConnection());


        db.resetDB();

        GUI.start(db);
        System.out.println("Gui started");


    }

}