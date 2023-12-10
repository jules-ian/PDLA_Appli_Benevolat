package controller;

import static org.junit.jupiter.api.Assertions.*;

import model.Asker;
import model.Mission;
import model.User;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.ArrayList;

public class DBManagerTest {
    private Connection connection;
    private DBManager DBM;

    @BeforeEach
    public void setUp(){
        try {
            connection = new Connect().getConnection();
        } catch (SQLException e) {
            fail("Could not establish connection");
            e.printStackTrace();
        }
        DBM = new DBManager(connection);
        DBM.reset_db();
    }

    @AfterEach
    public void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            fail("Could not close connection");
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test de création et suppression de la table users")
    public void testUserTableOperations() {
        assertDoesNotThrow(() -> {
            DBM.create_user_db();
            DBM.remove_user_db();
        });
    }

    @Test
    @DisplayName("Test de création et suppression de la table missions")
    public void testMissionTableOperations() {
        assertDoesNotThrow(() -> {
            DBM.create_user_db(); //Needed for foreign key constraints
            DBM.create_mission_db();
            DBM.remove_mission_db();
        });
    }

    @Test
    @DisplayName("Test d'ajout d'utilisateur à la base de données")
    public void testAddUserToDB() {
        Asker asker = new Asker("Name", "Surname", 99);
        assertDoesNotThrow(() -> {
            DBM.create_user_db();
            DBM.addUser(asker);
        });
    }

    @Test
    @DisplayName("Test d'ajout de mission à la base de données")
    public void testAddMissionToDB() {
        Asker asker = new Asker("Name", "Surname", 99, 5);
        Mission mission = new Mission("Mission Description", asker.getUid());
        assertDoesNotThrow(() -> {
            DBM.create_user_db();
            DBM.create_mission_db();
            DBM.addUser(asker);
            DBM.addMission(mission);
        });
    }

    @Test
    @DisplayName("Test de récupération de mission par ID")
    public void testGetMissionByID() {
        Asker asker = new Asker("Name", "Surname", 99, 5);
        Mission mission = new Mission("Mission Description", asker.getUid());
        assertDoesNotThrow(() -> {
            DBM.create_user_db();
            DBM.create_mission_db();
            DBM.addUser(asker);
            DBM.addMission(mission);
        });
        assertDoesNotThrow(() -> assertNotNull(DBM.getMission(mission.getMid())));
    }

    @Test
    @DisplayName("Test de récupération de tous les missions")
    public void testGetAllMissions() throws SQLException {
        Asker asker = new Asker("Name", "Surname", 99, 5);
        Mission miss1 = new Mission("Desc 1", asker.getUid(), -1, 1);
        Mission miss2 = new Mission("Desc 2", asker.getUid(), -1, 2);
        DBM.create_user_db();
        DBM.addUser(asker);

        DBM.create_mission_db();
        DBM.addMission(miss1);
        DBM.addMission(miss2);

        ArrayList<Mission> MList = DBM.getAllMissions();

        assertTrue(MList.contains(miss1));
        assertTrue(MList.contains(miss2));

        MList.remove(miss1);
        MList.remove(miss2);
        assertTrue(MList.isEmpty());
    }

    @Test
    @DisplayName("Test de récupération d'utilisateur par ID")
    public void testGetUserByID() throws SQLException {
        Asker asker = new Asker("Name", "Surname", 99, 5);
        DBM.create_user_db();
        DBM.addUser(asker);
        assertDoesNotThrow(() -> DBM.getUser(asker.getUid()));
    }

    @Test
    @DisplayName("Test de récupération des missions d'un Asker")
    public void testGetMissionsOfAsker() throws SQLException {
        Asker asker = new Asker("Name", "Surname", 99, 5);
        Mission miss1 = new Mission("Desc 1", asker.getUid(), -1, 1);
        DBM.createTables();
        DBM.addUser(asker);
        DBM.addMission(miss1);
        try {
            ArrayList<Mission> ret = DBM.get_missions_of_asker(asker.getUid());
            assertEquals(ret.get(0), miss1);
        } catch (SQLException e) {
            fail("Could not get the missions of the asker");
        }
    }

}
