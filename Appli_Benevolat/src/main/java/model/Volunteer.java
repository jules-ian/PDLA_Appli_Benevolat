package model;

import java.util.ArrayList;

/** A volunteer is someone who accept missions and fulfills them */
public class Volunteer extends User{


    ArrayList<Mission> myMissions;

    public Volunteer(String nom,String prenom,int age) {
        super(nom,prenom,age);
        this.myMissions = new ArrayList<>();
    }
    public Volunteer (String nom,String prenom,int age,int uid) {
        super(nom, prenom, age, uid);
        this.myMissions = new ArrayList<>();
    }
    public void display_all_missions() {
        ArrayList<Mission> missions = Mission.getAllMission();
        for (Mission mission : missions) {
            System.out.println(mission);
        }
    }
}
