package model.model;

import java.util.ArrayList;

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
    public void display_allquestion() {
        ArrayList<Mission> missions = Mission.getAllQuestions();
        for (Mission mission : missions) {
            System.out.println(mission);
        }
    }
}
