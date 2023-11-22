package model;
import controller.*;
import view.GUI;

public class Main {
    public static void main(String[] args) {

        Admin admin = new Admin("Jules-Ian","Barnavon", 21, 0);
        Asker a1 = new Asker("Andy", "Capé", 99);
        Volunteer v1 = new Volunteer("Barnavon", "Jules-ian", 5); //(age mental)

        Mission m1 = a1.createMission("G faim");
        Mission m2 = a1.createMission("G soif");
        v1.display_all_question();



        Connect co = new Connect();
        DBManager db = new DBManager(co.getConnection());


        db.reset_db();

        db.create_user_db();
        db.create_mission_db();

        GUI.start(db);
        System.out.println("Gui started");




        db.addUser(admin);


        db.addUser(a1);
        db.addUser(v1);

        db.addMission(m1);
        db.addMission(m2);

        System.out.println("affichage de a1: " + db.getUser(a1.getUid()));
        System.out.println("affichage de m1: " + db.getMission(m1.getMid()));
        System.out.println("affichage de m2: " + db.getMission(m2.getMid()));
        System.out.println("affichage des missions de a1 : \n" + db.get_missions_of_asker(a1.getUid()));

        //db.reset_db();

    }

}