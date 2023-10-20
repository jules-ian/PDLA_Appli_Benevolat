package model.model;

import java.util.ArrayList;

public class Asker extends User{
    /*Attribut*/
    ArrayList<Mission> myMissions;
    /*constructeur*/
    public Asker (String nom,String prenom,int age) {
        super(nom,prenom,age);
        this.myMissions = new ArrayList<>();
    }
    public Asker (String nom,String prenom,int age,int uid) {
        super(nom, prenom, age, uid);
        this.myMissions = new ArrayList<>();
    }

    /*Méthode*/
    public void create_question(String description){
        Mission q = new Mission(description,this);
        this.myMissions.add(q);
    }

}
