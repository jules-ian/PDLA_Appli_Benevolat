package model;

import java.util.ArrayList;
import java.util.Objects;

/** The Askers can create missions to ask for help */
public class Mission {

    String description;
    int askerID;
    int volunteerID; // if a volunteer selected the mission
    int Mid;

    @Deprecated // since we now use the DB
    static ArrayList<Mission> allMissions = new ArrayList<>();

    @Deprecated // since we now use the DB
    static int missionCount = 1;
    public Mission(String description, int asker) { //Constructeur de création d'une nouvelle mission
        this.description = description;
        this.askerID = asker;
        this.volunteerID = -1;
        this.Mid = missionCount;
        missionCount++;
        allMissions.add(this);
    }

    public Mission(String description, int asker, int volunteer, int id){ //Constructeur pour la création d'une mission récupérée dans la db
        this.description = description;
        if(volunteer == 0){
            this.volunteerID = -1;
        }else{
            this.volunteerID = volunteer;
        }
        this.Mid = id;
        this.askerID = asker;
    }

    public int getAskerID() {
        return askerID;
    }

    public int getMid() {
        return Mid;
    }

    @Deprecated // since we now use the DB
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

    @Deprecated // since we now use the DB
    public static ArrayList<Mission> getAllMission() {
        return allMissions;
    }

    @Override
    public String toString() {
        return  this.Mid + " : " + description + " | Asker : " + askerID + " | Volunteer : " + volunteerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission mission = (Mission) o;
        return askerID == mission.askerID && volunteerID == mission.volunteerID && Mid == mission.Mid && Objects.equals(description, mission.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, askerID, volunteerID, Mid);
    }
}
