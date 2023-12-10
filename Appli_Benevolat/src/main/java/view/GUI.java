package view;

import controller.DBManager;
import exceptions.UserNotFoundException;
import model.Asker;
import model.Mission;
import model.Volunteer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

public class GUI {

    private static final Logger LOGGER = LogManager.getLogger(GUI.class);
    private static void createAndShowGUI(DBManager DBM) {

        final int[] UID = {-1};
        final Color GREEN = new Color(60, 252, 60, 255);
        final Color RED = new Color(255, 32, 32, 255);


        ViewManager MainVM = new ViewManager();
        ViewManager FormVM = new ViewManager();
        ViewManager ShowVM = new ViewManager();
        ViewManager LabelVM = new ViewManager();

        // Create and set up the window.
        JFrame frame = new JFrame("Handic App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** Creation and initialization of the main views */
        JPanel Sign = MainVM.createPanelView(); // First page to be displayed to choose between signing in or signing up
        JPanel Signup = MainVM.createPanelView(); // Signing up page
        JPanel Signin = MainVM.createPanelView(); // Signing in page
        JPanel Home = MainVM.createPanelView(); // Home page when logged in
        JPanel AddMission = MainVM.createPanelView(); // Page to add a new Mission
        JPanel ShowMissions = MainVM.createPanelView(); // Page where all the available Missions are displayed

        Sign.setLayout(new BorderLayout());
        Signup.setLayout(new BoxLayout(Signup, BoxLayout.PAGE_AXIS));
        Signin.setLayout(new BoxLayout(Signin, BoxLayout.PAGE_AXIS));
        Home.setLayout(new BorderLayout());
        AddMission.setLayout((new BoxLayout(AddMission, BoxLayout.PAGE_AXIS)));
        ShowMissions.setLayout((new BoxLayout(ShowMissions, BoxLayout.PAGE_AXIS)));


        /** Creation and initialization of the forms to create a user or a mission */
        JPanel askerForm = FormVM.createPanelView();
        JPanel volunteerForm = FormVM.createPanelView();
        JPanel missionForm = new JPanel();
        JPanel emptyForm = FormVM.createPanelView();

        askerForm.setLayout(new GridLayout(0, 2));
        volunteerForm.setLayout(new GridLayout(0, 2));
        missionForm.setLayout(new GridLayout(0, 2));
        emptyForm.setSize(new Dimension(0, 50));


        /** Creation of ActionListeners to switch the view */
        ChangeView ShowSign = new ChangeView(frame, MainVM, Sign);
        ChangeView ShowSignup = new ChangeView(frame, MainVM, Signup);
        ChangeView ShowSignin = new ChangeView(frame, MainVM, Signin);
        ChangeView ShowHome = new ChangeView(frame, MainVM, Home);
        ChangeView ShowAddMission = new ChangeView(frame, MainVM, AddMission);
        ChangeView ShowShowDB = new ChangeView(frame, MainVM, ShowMissions );


        /** Creation of fields of the forms */

        // Asker Form Creation
        JLabel askerSurname = new JLabel("Surname :");
        JTextField askerSurnameField = new JTextField(20);
        JLabel askerName = new JLabel("Name :");
        JTextField askerNameField = new JTextField(20);
        JLabel askerAge = new JLabel("Age :");
        JTextField askerAgeField = new JTextField(3);

        askerForm.add(askerSurname);
        askerForm.add(askerSurnameField);
        askerForm.add(askerName);
        askerForm.add(askerNameField);
        askerForm.add(askerAge);
        askerForm.add(askerAgeField);

        // Volunteer Form Creation
        JLabel volunteerSurname = new JLabel("Surname :");
        JTextField volunteerSurnameField = new JTextField(20);
        JLabel volunteerName = new JLabel("Name :");
        JTextField volunteerNameField = new JTextField(20);
        JLabel volunteerAge = new JLabel("Age :");
        JTextField volunteerAgeField = new JTextField(3);

        volunteerForm.add(volunteerSurname);
        volunteerForm.add(volunteerSurnameField);
        volunteerForm.add(volunteerName);
        volunteerForm.add(volunteerNameField);
        volunteerForm.add(volunteerAge);
        volunteerForm.add(volunteerAgeField);

        // Mission Form Creation
        JLabel missionDescription = new JLabel("Description : ");
        JTextField missionDescriptionField = new JTextField(20);

        missionForm.add(missionDescription);
        missionForm.add(missionDescriptionField);


        /** Creating buttons and labels for all the pages */

        /** ---------------------- Signing page ------------------------ */

        JLabel signTitle = new JLabel("Welcome to Handic App !", JLabel.CENTER);

        JButton buttonSignup= new JButton("Sign up");
        buttonSignup.addActionListener(ShowSignup);

        JButton buttonSignin = new JButton("Log in");
        buttonSignin.addActionListener(ShowSignin);

        Sign.add(signTitle, BorderLayout.PAGE_START);
        Sign.add(buttonSignin, BorderLayout.LINE_START);
        Sign.add(buttonSignup, BorderLayout.LINE_END);


        /** ---------------------- Sign up page ------------------------ */


        JLabel labelSignup = LabelVM.createLabel();
        labelSignup.setText("Please choose a category of user");
        labelSignup.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelSignupAsker = LabelVM.createLabel();
        labelSignupAsker.setText("Creating an account as an Asker");
        labelSignupAsker.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelSignupVolunteer = LabelVM.createLabel();
        labelSignupVolunteer.setText("Creating an account as a Volunteer");
        labelSignupVolunteer.setHorizontalAlignment(JLabel.CENTER);
        JLabel labelInfoSignup = new JLabel("");
        labelInfoSignup.setForeground(RED);

        JButton buttonSignupAsker = new JButton("Asker");
        buttonSignupAsker.addActionListener(new ChangeLabel(labelSignupAsker, LabelVM, Signup, 0));
        buttonSignupAsker.addActionListener(new ChangeForm(askerForm, FormVM, Signup, 2));

        JButton buttonSignupVolunteer = new JButton("Volunteer");
        buttonSignupVolunteer.addActionListener(new ChangeLabel(labelSignupVolunteer, LabelVM, Signup, 0));
        buttonSignupVolunteer.addActionListener(new ChangeForm(volunteerForm, FormVM, Signup, 2));

        JButton buttonSignupReturn = new JButton("Return");
        buttonSignupReturn.setBackground(RED);
        buttonSignupReturn.addActionListener(new ChangeLabel(labelSignup, LabelVM, Signup, 0));
        buttonSignupReturn.addActionListener(new ChangeForm(emptyForm, FormVM, Signup, 2));
        buttonSignupReturn.addActionListener(ShowSign);
        buttonSignupReturn.addActionListener(e -> {labelInfoSignup.setText("");});

        JButton buttonConfirmSignup = new JButton("Create user");
        buttonConfirmSignup.setBackground(GREEN);
        buttonConfirmSignup.addActionListener(e -> {
            try {
                Component form = FormVM.getVisible();
                if (form.equals(emptyForm)) {
                    ; // do nothing and wait for the user to choose an option
                    labelInfoSignup.setText("Please choose what type of user you are");
                } else if (form.equals(askerForm)) {
                    if (askerNameField.getText().isEmpty() || askerAgeField.getText().isEmpty() || askerSurnameField.getText().isEmpty()) {
                        labelInfoSignup.setText("Please fill all the fields");
                    } else {
                        DBM.addUser(new Asker(askerNameField.getText(), askerSurnameField.getText(), Integer.parseInt(askerAgeField.getText())));
                    }
                } else if (form.equals(volunteerForm)) {
                    if (volunteerNameField.getText().isEmpty() || volunteerAgeField.getText().isEmpty() || volunteerSurnameField.getText().isEmpty()) {
                        labelInfoSignup.setText("Please fill all the fields");
                    } else {
                        DBM.addUser(new Volunteer(volunteerNameField.getText(), volunteerSurnameField.getText(), Integer.parseInt(volunteerAgeField.getText())));
                    }
                }
            }catch(SQLException ex){
                labelInfoSignup.setText("Could not add user to DB");
            }
        });

        JPanel panelSignupButtons = new JPanel(new FlowLayout());
        panelSignupButtons.add(buttonSignupAsker);
        panelSignupButtons.add(buttonSignupVolunteer);

        JPanel panelBottomButtons = new JPanel(new FlowLayout());
        panelBottomButtons.add(buttonSignupReturn);
        panelBottomButtons.add(buttonConfirmSignup);

        LabelVM.setView(labelSignup);
        FormVM.setView(emptyForm);

        Signup.add(labelSignup);
        Signup.add(panelSignupButtons);
        Signup.add(emptyForm);
        Signup.add(labelInfoSignup);
        Signup.add(panelBottomButtons);



        /** ---------------------- Sign in page ------------------------ */

        JLabel authTitle = new JLabel("Please enter you user ID", JLabel.CENTER);

        JTextField userIDField = new JTextField(20);

        JPanel padding = new JPanel();

        padding.setLayout(new BoxLayout(padding, BoxLayout.PAGE_AXIS));
        padding.add(Box.createRigidArea(new Dimension(0, 100)));
        padding.add(userIDField);
        padding.add(Box.createRigidArea(new Dimension(0, 100)));

        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBackground(GREEN);
        //Checks that given ID exists
        buttonLogin.addActionListener(e -> {
            try {
                int resp = Integer.parseInt(userIDField.getText());
                    UID[0] = resp;
                    DBM.getUser(resp);
                    MainVM.setViewOfFrame(frame, Home);
            } catch (UserNotFoundException unf){
                unf.printMessage();
                authTitle.setText("Provided ID doesn't exists");
                authTitle.setForeground(RED);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton buttonReturnSignin = new JButton("Return");
        buttonReturnSignin.setBackground(RED);
        buttonReturnSignin.addActionListener(ShowSign);
        buttonReturnSignin.addActionListener(e -> {
            authTitle.setText("Please enter you user ID");
            authTitle.setForeground(Color.BLACK);
        });

        JPanel signButtons = new JPanel(new FlowLayout());
        signButtons.add(buttonReturnSignin);
        signButtons.add(buttonLogin);

        Signin.add(authTitle);
        Signin.add(padding);
        Signin.add(signButtons);

        /** ---------------------- Home page ------------------------ */

        JLabel homeTitle = new JLabel("Here you can display all the Missions or create one");

        JButton buttonAddMission = new JButton("Add Mission");
        buttonAddMission.addActionListener(ShowAddMission);
        JButton buttonViewMissions = new JButton("View Missions");
        buttonViewMissions.addActionListener(ShowShowDB);
        JButton homeReturn = new JButton("Return");
        homeReturn.addActionListener(ShowSignin);


        JPanel homeButtons = new JPanel(new FlowLayout());
        homeButtons.add(buttonAddMission);
        homeButtons.add(buttonViewMissions);

        Home.add(homeTitle, BorderLayout.PAGE_START);
        Home.add(homeButtons, BorderLayout.CENTER);
        Home.add(homeReturn, BorderLayout.PAGE_END);

        /** ---------------------- Create Mission page ------------------------ */

        JLabel addMissionTitle = new JLabel("Here you can add an item to the DB", JLabel.CENTER);

        JLabel infoText = new JLabel("");
        JButton buttonAdd = new JButton("Add");
        buttonAdd.setBackground(GREEN);
        buttonAdd.addActionListener(e -> {
            try {
                if (missionDescriptionField.getText().isEmpty()) {
                    infoText.setText("Please fill the description field");
                    infoText.setForeground(RED);
                } else {
                    DBM.addMission(new Mission(missionDescriptionField.getText(), UID[0]));
                }
            }catch(SQLException ex){
                infoText.setText("Could not add the mission to the database");
            }
        });

        JButton buttonReturnAddMission = new JButton("Return");
        buttonReturnAddMission.setBackground(RED);
        buttonReturnAddMission.addActionListener(ShowHome);

        JPanel bottomButtons = new JPanel(new FlowLayout());
        bottomButtons.add(buttonReturnAddMission);
        bottomButtons.add(buttonAdd);


        AddMission.add(addMissionTitle);
        AddMission.add(missionForm);
        AddMission.add(bottomButtons);
        AddMission.add(infoText);


        /** ---------------------- Show Mission page ------------------------ */


        JButton buttonMissionShow = new JButton("Mission");
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JScrollPane scrollPane = ShowVM.createScrollView(listModel);
        scrollPane.setLayout(new ScrollPaneLayout());


        ActionListener showMissionActionListener = e -> {
            try {
                listModel.clear();
                ArrayList<Mission> missions = DBM.getAllMissions();
                for (Mission mission : missions) {
                    listModel.addElement(mission.toString());
                }
            }catch(SQLException ex){
                //TODO: FAire un Label info ou on affiche l'erreur
            }
           ShowMissions.remove(2);
           ShowVM.setView(scrollPane);
           ShowMissions.add(scrollPane, 2);
           ShowMissions.revalidate();
           ShowMissions.repaint();
       };
        buttonMissionShow.addActionListener(showMissionActionListener);
        buttonViewMissions.addActionListener(showMissionActionListener);




        JPanel addbuttonsShow = new JPanel(new FlowLayout());
        addbuttonsShow.add(buttonMissionShow);








        //for show DB :
        JLabel showDBTitle = new JLabel("Here you can search an item from the DB", JLabel.CENTER);
        ShowMissions.add(showDBTitle);
        ShowMissions.add(addbuttonsShow);
        //Empty Form
        Component emptyFormShow = Box.createRigidArea(new Dimension(0, 50));
        ShowMissions.add(emptyFormShow);
        JLabel infoTextShow = new JLabel("");
        ShowMissions.add(infoTextShow);

        JPanel bottomButtonsShow = new JPanel(new FlowLayout());

        JButton buttonReturnCopie = new JButton("Return");
        bottomButtonsShow.add(buttonReturnCopie);


        ShowMissions.add(bottomButtonsShow);



        buttonReturnCopie.addActionListener(ShowHome);





        MainVM.setViewOfFrame(frame, Sign);
        frame.setSize(500, 300);
        // Display the window.
        frame.setVisible(true);
    }


    public static void start(DBManager db) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(db);
                LOGGER.info("Showing gui");
            }
        });

    }

    /** A few ActionListeners definitions */

    /** This ActionListener changes the view of the current JFrame */
    public static class ChangeView implements ActionListener {
        private JPanel View;
        private ViewManager VM;
        private JFrame frame;

        public ChangeView(JFrame frame, ViewManager VM, JPanel View) {
            this.View = View;
            this.VM = VM;
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            VM.setViewOfFrame(frame, View);
        }

    }

    /** This ActionListener changes the form of the current JPanel */
    public static class ChangeForm implements ActionListener {
        private JPanel View;
        private ViewManager VM;
        private Component form;
        private int index;

        public ChangeForm(Component form, ViewManager VM, JPanel View, int index) {
            this.View = View;
            this.VM = VM;
            this.form = form;
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            View.remove(this.index);
            VM.setView(form);
            View.add(form, this.index);
            View.revalidate();
            View.repaint();
        }

    }

    /** This ActionListener changes the label of the current JPanel */
    public static class ChangeLabel implements ActionListener {
        private JPanel View;
        private ViewManager VM;
        private Component label;
        private int index;

        public ChangeLabel(Component label, ViewManager VM, JPanel View, int index) {
            this.View = View;
            this.VM = VM;
            this.label = label;
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            View.remove(this.index);
            VM.setView(label);
            View.add(label, this.index);
            View.revalidate();
            View.repaint();
        }

    }


}
