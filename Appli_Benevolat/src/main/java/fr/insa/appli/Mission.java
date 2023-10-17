package fr.insa.appli;

import java.util.ArrayList;

public class Question { // Demande d'aide que peuvent faire les Askers

    String description;
    Asker asker;
    Volunteer volunteer;
    int Qid;
    static ArrayList<Question> AllQuestions = new ArrayList<>();

    static int questionCount = 0;
    public Question (String description, Asker asker) {
        this.description = description;
        this.asker = asker;
        this.volunteer = null;
        this.Qid = questionCount;
        questionCount++;
        AllQuestions.add(this);
    }

    public Asker getAsker() {
        return asker;
    }

    public int getQid() {
        return Qid;
    }

    public static int getQuestionCount() {
        return questionCount;
    }

    public String getDescription() {
        return description;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }
    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<Question> getAllQuestions() {
        return AllQuestions;
    }

    @Override
    public String toString() {
        return  this.Qid + " : " + description + " | Asker : " + asker;
    }
}
