package model.model;

import java.util.ArrayList;

public class Admin extends User{
    public Admin(String nom,String prenom,int age) {
        super(nom,prenom,age);
    }
    public Admin (String nom,String prenom,int age,int uid) {
        super(nom, prenom, age, uid);
    }

}
