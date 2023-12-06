package model;

import java.util.ArrayList;

/** The Askers can create missions to ask for help */
public class Mission {

    String description;
    int askerID;
    int volunteerID; // if a volunteer selected the mission
    int Mid;
    static ArrayList<Mission> allMissions = new ArrayList<>(); // used before the database

    static int missionCount = 1;
    public Mission(String description, int asker) { //Constructeur de création d'une nouvelle mission
        this.description = description;
        this.askerID = asker;
        this.volunteerID = 0;
        this.Mid = missionCount;
        missionCount++;
        allMissions.add(this);
    }

    public Mission(String description, int asker, int volunteer, int id){ //Constructeur pour la création d'une mission récupérée dans la db
        this.description = description;
        this.Mid = id;
        this.askerID = asker;
        this.volunteerID = volunteer;
    }

    public int getAskerID() {
        return askerID;
    }

    public int getMid() {
        return Mid;
    }

    public static int getMissionCount() {
        return missionCount;
    }

    public String getDescription() {
        return description;
    }

    public int getVolunteerID() {
        return volunteerID;
    }
    public void setVolunteerID(int volunteerID) {
        this.volunteerID = volunteerID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<Mission> getAllMission() {
        return allMissions;
    }

    @Override
    public String toString() {
        return  this.Mid + " : " + description + " | Asker : " + askerID;
    }
}
