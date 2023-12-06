package model;

import java.util.ArrayList;

/** An Asker is a user of the app who create missions */
public class Asker extends User{
    /*Attribute*/
    ArrayList<Mission> myMissions;

    /*Constructor*/
    public Asker (String nom,String prenom,int age) {
        super(nom,prenom,age);
        this.myMissions = new ArrayList<>();
    }
    public Asker (String nom,String prenom,int age,int uid) {
        super(nom, prenom, age, uid);
        this.myMissions = new ArrayList<>();
    }

    /*Methods*/
    public Mission createMission(String description){
        Mission q = new Mission(description,this.uid);
        this.myMissions.add(q);
        return q;
    }

}
