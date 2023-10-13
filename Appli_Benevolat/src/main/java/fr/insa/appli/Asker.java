package fr.insa.appli;

import java.util.ArrayList;

public class Asker extends User{
    /*Attribut*/
    ArrayList<Question> MyQuestions;
    /*constructeur*/
    public Asker (String nom,String prenom,int age) {
        super(nom,prenom,age);
        this.MyQuestions = new ArrayList<>();
    }

    /*Méthode*/
    public void create_question(String description){
        Question q = new Question(description,this);
        this.MyQuestions.add(q);
    }

}
