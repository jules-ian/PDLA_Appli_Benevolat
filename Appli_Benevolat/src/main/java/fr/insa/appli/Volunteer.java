package fr.insa.appli;

import java.util.ArrayList;

public class Volunteer extends User{


    ArrayList<Question> MyQuestions;

    public Volunteer(String nom,String prenom,int age) {
        super(nom,prenom,age);
        this.MyQuestions = new ArrayList<>();
    }

    public void display_allquestion() {
        ArrayList<Question> Questions = Question.getAllQuestions();
        for (Question question : Questions) {
            System.out.println(question);
        }
    }
}
