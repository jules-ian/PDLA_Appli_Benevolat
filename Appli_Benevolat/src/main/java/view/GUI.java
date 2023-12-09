package view;

import controller.DBManager;
import exceptions.UserNotFoundException;
import model.Asker;
import model.Mission;
import model.Volunteer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

public class GUI {

    public static void createAndShowGUI(DBManager DBM) {

        final int[] UID = {-1}; // Mécanisme bizarre mais nécessaire pour modifier l'UID a l'auth
        final Color GREEN = new Color(60, 252, 60, 255);
        final Color RED = new Color(255, 32, 32, 255);

        ViewManager MainVM = new ViewManager();
        ViewManager FormVM = new ViewManager();
        ViewManager ShowVM = new ViewManager(); //TODO: La liste des Missions ne s'actualise pas lorsqu'on quitte la vue et qu'on revient
        ViewManager LabelVM = new ViewManager();

        // Create and set up the window.
        JFrame frame = new JFrame("Handic App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /** Creation and initialization of the main views */
        JPanel Sign = MainVM.createPanelView(); // First page to be displayed to choose between signing in or signing up
        JPanel Signup = MainVM.createPanelView(); // Signing up page
        JPanel Signin = MainVM.createPanelView(); // Signing in page
        JPanel Home = MainVM.createPanelView(); // première page affichée sur le GUI
        JPanel AddMission = MainVM.createPanelView(); // page pour ajouter un User ou une Mission
        JPanel ShowMissions = MainVM.createPanelView(); // page pour afficher une liste de Users ou Missions

        Sign.setLayout(new BorderLayout());
        Signup.setLayout(new BoxLayout(Signup, BoxLayout.PAGE_AXIS)); // Vertical BoxLayout
        Signin.setLayout(new BoxLayout(Signin, BoxLayout.PAGE_AXIS));
        Home.setLayout(new BorderLayout());
        AddMission.setLayout((new BoxLayout(AddMission, BoxLayout.PAGE_AXIS)));
        ShowMissions.setLayout((new BoxLayout(ShowMissions, BoxLayout.PAGE_AXIS)));


        /** Creation and initialization of the forms to create a user or a mission */
        JPanel AskerForm = FormVM.createPanelView();
        JPanel VolunteerForm = FormVM.createPanelView();
        JPanel MissionForm = new JPanel();
        JPanel EmptyForm = FormVM.createPanelView();

        AskerForm.setLayout(new GridLayout(0, 2));
        VolunteerForm.setLayout(new GridLayout(0, 2));
        MissionForm.setLayout(new GridLayout(0, 2));
        EmptyForm.setSize(new Dimension(0, 50));


        /** Creation of ActionListeners to switch the view */
        ChangeView ShowSign = new ChangeView(frame, MainVM, Sign);
        ChangeView ShowSignup = new ChangeView(frame, MainVM, Signup);
        ChangeView ShowSignin = new ChangeView(frame, MainVM, Signin);
        ChangeView ShowHome = new ChangeView(frame, MainVM, Home);
        ChangeView ShowAddMission = new ChangeView(frame, MainVM, AddMission);
        ChangeView ShowShowDB = new ChangeView(frame, MainVM, ShowMissions );


        /** Creation of fields of the forms */

        // Asker Form Creation
        JLabel AskerSurname = new JLabel("Surname :");
        JTextField AskerSurnameField = new JTextField(20);
        JLabel AskerName = new JLabel("Name :");
        JTextField AskerNameField = new JTextField(20);
        JLabel AskerAge = new JLabel("Age :");
        JTextField AskerAgeField = new JTextField(3);

        AskerForm.add(AskerSurname);
        AskerForm.add(AskerSurnameField);
        AskerForm.add(AskerName);
        AskerForm.add(AskerNameField);
        AskerForm.add(AskerAge);
        AskerForm.add(AskerAgeField);

        // Volunteer Form Creation
        JLabel VolunteerSurname = new JLabel("Surname :");
        JTextField VolunteerSurnameField = new JTextField(20);
        JLabel VolunteerName = new JLabel("Name :");
        JTextField VolunteerNameField = new JTextField(20);
        JLabel VolunteerAge = new JLabel("Age :");
        JTextField VolunteerAgeField = new JTextField(3);

        VolunteerForm.add(VolunteerSurname);
        VolunteerForm.add(VolunteerSurnameField);
        VolunteerForm.add(VolunteerName);
        VolunteerForm.add(VolunteerNameField);
        VolunteerForm.add(VolunteerAge);
        VolunteerForm.add(VolunteerAgeField);

        // Mission Form Creation
        JLabel MissionDescription = new JLabel("Description : ");
        JTextField MissionDescriptionField = new JTextField(20);

        MissionForm.add(MissionDescription);
        MissionForm.add(MissionDescriptionField);


        /** Creating buttons and labels for all the pages */

        /** ---------------------- Signing page ------------------------ */

        JLabel SignTitle = new JLabel("Welcome to Handic App !", JLabel.CENTER);

        JButton buttonSignup= new JButton("Sign up");
        buttonSignup.addActionListener(ShowSignup);

        JButton buttonSignin = new JButton("Log in");
        //buttonSignin.setBackground(new Color(60, 252, 60));
        buttonSignin.addActionListener(ShowSignin);

        Sign.add(SignTitle, BorderLayout.PAGE_START);
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
        buttonSignupAsker.addActionListener(new ChangeForm(AskerForm, FormVM, Signup, 2));
        JButton buttonSignupVolunteer = new JButton("Volunteer");
        buttonSignupVolunteer.addActionListener(new ChangeLabel(labelSignupVolunteer, LabelVM, Signup, 0));
        buttonSignupVolunteer.addActionListener(new ChangeForm(VolunteerForm, FormVM, Signup, 2));
        JButton buttonSignupReturn = new JButton("Return");
        buttonSignupReturn.setBackground(RED);
        buttonSignupReturn.addActionListener(new ChangeLabel(labelSignup, LabelVM, Signup, 0));
        buttonSignupReturn.addActionListener(new ChangeForm(EmptyForm, FormVM, Signup, 2));
        buttonSignupReturn.addActionListener(ShowSign);
        buttonSignupReturn.addActionListener(e -> {labelInfoSignup.setText("");});
        JButton buttonConfirmSignup = new JButton("Create user");
        buttonConfirmSignup.setBackground(GREEN);
        buttonConfirmSignup.addActionListener(e -> {
            Component form = FormVM.getVisible();
            if(form.equals(EmptyForm)){
                ; // do nothing and wait for the user to choose an option
                labelInfoSignup.setText("Please choose what type of user you are");
            } else if (form.equals(AskerForm)) {
                if (AskerNameField.getText().isEmpty() || AskerAgeField.getText().isEmpty() || AskerSurnameField.getText().isEmpty()) {
                    labelInfoSignup.setText("Please fill all the fields");
                } else {
                    DBM.addUser(new Asker(AskerNameField.getText(), AskerSurnameField.getText(), Integer.parseInt(AskerAgeField.getText())));
                }
            }else if (form.equals(VolunteerForm)) {
                if (VolunteerNameField.getText().isEmpty() || VolunteerAgeField.getText().isEmpty() || VolunteerSurnameField.getText().isEmpty()) {
                    labelInfoSignup.setText("Please fill all the fields");
                } else {
                    DBM.addUser(new Volunteer(VolunteerNameField.getText(), VolunteerSurnameField.getText(), Integer.parseInt(VolunteerAgeField.getText())));
                }
            }
        });

        JPanel panelSignupButtons = new JPanel(new FlowLayout());
        panelSignupButtons.add(buttonSignupAsker);
        panelSignupButtons.add(buttonSignupVolunteer);

        JPanel panelBottomButtons = new JPanel(new FlowLayout());
        panelBottomButtons.add(buttonSignupReturn);
        panelBottomButtons.add(buttonConfirmSignup);

        LabelVM.setView(labelSignup);
        FormVM.setView(EmptyForm);

        Signup.add(labelSignup);
        Signup.add(panelSignupButtons);
        Signup.add(EmptyForm);
        Signup.add(labelInfoSignup);
        Signup.add(panelBottomButtons);



        /** ---------------------- Sign in page ------------------------ */

        JLabel AuthTitle = new JLabel("Please enter you user ID", JLabel.CENTER);

        JTextField UserIDField = new JTextField(20);

        JPanel padding = new JPanel();

        padding.setLayout(new BoxLayout(padding, BoxLayout.PAGE_AXIS));
        padding.add(Box.createRigidArea(new Dimension(0, 100)));
        padding.add(UserIDField);
        padding.add(Box.createRigidArea(new Dimension(0, 100)));

        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBackground(GREEN);
        //Checks that given ID exists
        buttonLogin.addActionListener(e -> {
            try {
                int resp = Integer.parseInt(UserIDField.getText());
                    UID[0] = resp;
                    System.out.println(DBM.getUser(resp));
                    MainVM.set_view_of_frame(frame, Home);
            } catch (UserNotFoundException unf){
                unf.printMessage();
                AuthTitle.setText("Provided ID doesn't exists");
                AuthTitle.setForeground(RED);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton buttonReturnSignin = new JButton("Return");
        buttonReturnSignin.setBackground(RED);
        buttonReturnSignin.addActionListener(ShowSign);
        buttonReturnSignin.addActionListener(e -> {
            AuthTitle.setText("Please enter you user ID");
            AuthTitle.setForeground(Color.BLACK);
        });

        JPanel SignButtons = new JPanel(new FlowLayout());
        SignButtons.add(buttonReturnSignin);
        SignButtons.add(buttonLogin);

        Signin.add(AuthTitle);
        Signin.add(padding);
        Signin.add(SignButtons);

        /** ---------------------- Home page ------------------------ */

        JLabel HomeTitle = new JLabel("Here you can display all the Missions or create one");

        JButton buttonAddMission = new JButton("Add Mission");
        buttonAddMission.addActionListener(ShowAddMission);
        JButton buttonViewMissions = new JButton("View Missions");
        buttonViewMissions.addActionListener(ShowShowDB);
        JButton HomeReturn = new JButton("Return");
        HomeReturn.addActionListener(ShowSignin);


        JPanel HomeButtons = new JPanel(new FlowLayout());
        HomeButtons.add(buttonAddMission);
        HomeButtons.add(buttonViewMissions);

        Home.add(HomeTitle, BorderLayout.PAGE_START);
        Home.add(HomeButtons, BorderLayout.CENTER);
        Home.add(HomeReturn, BorderLayout.PAGE_END);

        /** ---------------------- Create Mission page ------------------------ */

        JLabel AddMissionTitle = new JLabel("Here you can add an item to the DB", JLabel.CENTER);

        JLabel InfoText = new JLabel("");
        JButton buttonAdd = new JButton("Add");
        buttonAdd.setBackground(GREEN);
        buttonAdd.addActionListener(e -> {
                if (MissionDescriptionField.getText().isEmpty()){
                    InfoText.setText("Please fill the description field");
                    InfoText.setForeground(RED);
                } else {
                    DBM.addMission(new Mission(MissionDescriptionField.getText(), UID[0]));
                }
        });

        JButton buttonReturnAddMission = new JButton("Return");
        buttonReturnAddMission.setBackground(RED);
        buttonReturnAddMission.addActionListener(ShowHome);

        JPanel BottomButtons = new JPanel(new FlowLayout());
        BottomButtons.add(buttonReturnAddMission);
        BottomButtons.add(buttonAdd);


        AddMission.add(AddMissionTitle);
        AddMission.add(MissionForm);
        AddMission.add(BottomButtons);
        AddMission.add(InfoText);


        /** ---------------------- Show Mission page ------------------------ */


        JButton buttonMissionShow = new JButton("Mission");
        //buttonMissionShow.addActionListener(new ChangeForm(scrollPane, ShowVM, ShowMissions));
        //String[] data = {"je suis la première mission","je suis la deuxième mission"};
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JScrollPane scrollPane = ShowVM.createScrollView(listModel);
        scrollPane.setLayout(new ScrollPaneLayout());

        buttonMissionShow.addActionListener(e -> {

            //(eng) (liste déroulante pour) show DB
            listModel.clear();
            ArrayList<Mission> missions = DBM.getAllMissions();
            for (Mission mission : missions )
            {

                listModel.addElement(mission.toString());
                //data[i] = String.valueOf(missions.get(i));
                System.out.println("je suis la mission a ajouté au scroll : "+mission);
            }
                //Mettre à jour la data du scrollPane
            ShowMissions.remove(2);
            ShowVM.setView(scrollPane);
            ShowMissions.add(scrollPane, 2);
            ShowMissions.revalidate();
            ShowMissions.repaint();


        });

        //ScrollPane init




        JPanel AddbuttonsShow = new JPanel(new FlowLayout());
        AddbuttonsShow.add(buttonMissionShow);








        //for show DB :
        JLabel ShowDBTitle = new JLabel("Here you can search an item from the DB", JLabel.CENTER);
        ShowMissions.add(ShowDBTitle);
        ShowMissions.add(AddbuttonsShow);
        //Empty Form
        Component EmptyFormShow = Box.createRigidArea(new Dimension(0, 50));
        ShowMissions.add(EmptyFormShow);
        JLabel InfoTextShow = new JLabel("");
        ShowMissions.add(InfoTextShow);

        JPanel BottomButtonsShow = new JPanel(new FlowLayout());

        JButton buttonReturnCopie = new JButton("Return");
        BottomButtonsShow.add(buttonReturnCopie);


        ShowMissions.add(BottomButtonsShow);

        //AddMission.add(AskerForm, 2);


        buttonReturnCopie.addActionListener(ShowHome);





        MainVM.set_view_of_frame(frame, Sign);
        frame.setSize(500, 300);
        // Display the window.
        frame.setVisible(true);
    }


    public static void start(DBManager db) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(db);
                System.out.println("Showing gui");
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
            VM.set_view_of_frame(frame, View);
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
