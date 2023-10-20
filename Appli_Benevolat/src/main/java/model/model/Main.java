package model.model;
import controller.*;

public class Main {
    public static void main(String[] args) {

        Asker a1 = new Asker("Landi", "Capé", 99);
        Volunteer v1 = new Volunteer("Barnavon", "Jules-ian", 5); //(age mental)

        a1.create_question("G faim");
        v1.display_allquestion();
        a1.create_question("G soif");
        v1.display_allquestion();

        Connect co = new Connect();
        DBManager db = new DBManager(co.getConnection());

        db.create_user_db();

        db.add_user(a1);
        System.out.println(db.getUser(a1.getUid()));


    }

}