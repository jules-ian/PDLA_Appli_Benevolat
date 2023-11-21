package view;

import controller.Connect;
import controller.DBManager;
import model.Asker;
import model.Mission;
import model.Volunteer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {

    public static void createAndShowGUI(DBManager DBM) {

        ViewManager MainVM = new ViewManager();
        ViewManager FormVM = new ViewManager();
        ViewManager ShowVM = new ViewManager();

        // Create and set up the window.
        JFrame frame = new JFrame("Handic App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel Home = MainVM.createPanelView(); // première page affichée sur le GUI
        JPanel AddItem = MainVM.createPanelView(); // page pour ajouter un User ou une Mission
        JPanel ShowDB = MainVM.createPanelView(); // page pour afficher une liste de Users ou Missions


        Home.setLayout(new BorderLayout());
        AddItem.setLayout((new BoxLayout(AddItem, BoxLayout.PAGE_AXIS))); // Vertical BoxLayout
        ShowDB.setLayout((new BoxLayout(ShowDB, BoxLayout.PAGE_AXIS)));

        JPanel AskerForm = FormVM.createPanelView();
        JPanel VolunteerForm = FormVM.createPanelView();
        JPanel MissionForm = FormVM.createPanelView();


        //(eng) (liste déroulante pour) show DB
        String[] data = {"je suis la première mission","je suis la deuxième mission"};
        JScrollPane scrollPane = ShowVM.createScrollView(data);

        //Forms Initialization
        AskerForm.setLayout(new GridLayout(0, 2));
        VolunteerForm.setLayout(new GridLayout(0, 2));
        MissionForm.setLayout(new GridLayout(0, 2));

        //ScrollPane init
        scrollPane.setLayout(new ScrollPaneLayout());




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
        JLabel MissionAskerSurname = new JLabel("Surname of asker :");
        JTextField MissionAskerSurnameField = new JTextField(20);
        JLabel MissionAskerName = new JLabel("Name of asker :");
        JTextField MissionAskerNameField = new JTextField(20);

        MissionForm.add(MissionDescription);
        MissionForm.add(MissionDescriptionField);
        MissionForm.add(MissionAskerSurname);
        MissionForm.add(MissionAskerSurnameField);
        MissionForm.add(MissionAskerName);
        MissionForm.add(MissionAskerNameField);



        //On crée la layout de la page d'accueil



        // Setting up form buttons
        //Buttons for Adding
        JButton buttonAsker = new JButton("Asker");
        buttonAsker.addActionListener(new ChangeForm(AskerForm, FormVM, AddItem));
        JButton buttonVolunteer = new JButton("Volunteer");
        buttonVolunteer.addActionListener(new ChangeForm(VolunteerForm, FormVM, AddItem));
        JButton buttonMission = new JButton("Mission");
        buttonMission.addActionListener(new ChangeForm(MissionForm, FormVM, AddItem));

        //Buttons for search
        JButton buttonMissionShow = new JButton("Mission");
        buttonMissionShow.addActionListener(new ChangeForm(scrollPane, ShowVM, ShowDB));


        //Ajouter les boutons crée au dessus au panel
        JPanel AddButtons = new JPanel(new FlowLayout());
        AddButtons.add(buttonAsker);
        AddButtons.add(buttonVolunteer);
        AddButtons.add(buttonMission);

        JPanel AddbuttonsShow = new JPanel(new FlowLayout());
        AddbuttonsShow.add(buttonMissionShow);



        //On ajoute les boutons et le titre de la page d'accueil
        JPanel HomeButtons = new JPanel(new FlowLayout());

        JButton buttonAddUser = new JButton("Add User");
        HomeButtons.add(buttonAddUser);

        JButton buttonViewDB = new JButton("View DataBase");
        HomeButtons.add(buttonViewDB);

        JLabel HomeTitle = new JLabel("Welcome to Handic App !", JLabel.CENTER);
        Home.add(HomeTitle, BorderLayout.PAGE_START);

        Home.add(HomeButtons, BorderLayout.CENTER);

        JLabel AddItemTitle = new JLabel("Here you can add an item to the DB", JLabel.CENTER);
        AddItem.add(AddItemTitle);
        AddItem.add(AddButtons);
        //Empty Form
        Component EmptyForm = Box.createRigidArea(new Dimension(0, 50));
        AddItem.add(EmptyForm);
        JLabel InfoText = new JLabel("");
        AddItem.add(InfoText);

        //for show DB :
        JLabel ShowDBTitle = new JLabel("Here you can search an item from the DB", JLabel.CENTER);
        ShowDB.add(ShowDBTitle);
        ShowDB.add(AddbuttonsShow);
        //Empty Form
        Component EmptyFormShow = Box.createRigidArea(new Dimension(0, 50));
        ShowDB.add(EmptyFormShow);
        JLabel InfoTextShow = new JLabel("");
        ShowDB.add(InfoTextShow);

        JPanel BottomButtons = new JPanel(new FlowLayout());
        JPanel BottomButtonsShow = new JPanel(new FlowLayout());

        JButton buttonReturn = new JButton("Return");
        BottomButtons.add(buttonReturn);
        JButton buttonReturnCopie = new JButton("Return");
        BottomButtonsShow.add(buttonReturnCopie);

        JButton buttonAdd = new JButton("Add");
        buttonAdd.setBackground(new Color(109, 245, 109, 255));
        BottomButtons.add(buttonAdd);



        AddItem.add(BottomButtons);
        ShowDB.add(BottomButtonsShow);

        //AddItem.add(AskerForm, 2);

        MainVM.set_view_of_frame(frame, Home);

        ChangeView ShowHome = new ChangeView(frame, MainVM, Home);
        ChangeView ShowAddItem = new ChangeView(frame, MainVM, AddItem);
        ChangeView ShowShowDB = new ChangeView(frame, MainVM, ShowDB );

        buttonAddUser.addActionListener(ShowAddItem);
        buttonViewDB.addActionListener(ShowShowDB);
        buttonReturn.addActionListener(ShowHome);
        buttonReturn.addActionListener(new ChangeForm(EmptyForm, FormVM, AddItem));
        buttonReturnCopie.addActionListener(ShowHome);
        buttonReturnCopie.addActionListener(new ChangeForm(EmptyForm, FormVM, AddItem));

        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component form = FormVM.getVisible();
                if(form.equals(EmptyForm)){
                    ; // do nothing and wait for the user to choose an option
                } else if (form.equals(AskerForm)) {

                    if (AskerNameField.getText().isEmpty() || AskerAgeField.getText().isEmpty() || AskerSurnameField.getText().isEmpty()){
                        InfoText.setText("Please fill all the fields");
                        InfoText.setForeground(new Color(194, 0, 0));
                    } else {
                        DBM.addUser(new Asker(AskerSurnameField.getText(), AskerNameField.getText(), Integer.parseInt(AskerAgeField.getText())));
                    }
                    
                } else if (form.equals(VolunteerForm)) {

                    if (VolunteerNameField.getText().isEmpty() || VolunteerAgeField.getText().isEmpty() || VolunteerSurnameField.getText().isEmpty()){
                        InfoText.setText("Please fill all the fields");
                        InfoText.setForeground(new Color(194, 0, 0));
                    } else {
                        DBM.addUser(new Volunteer(VolunteerSurnameField.getText(), VolunteerNameField.getText(), Integer.parseInt(VolunteerAgeField.getText())));
                    }
                    
                }else if (form.equals(MissionForm)){

                    if (MissionDescriptionField.getText().isEmpty() || MissionAskerNameField.getText().isEmpty() || MissionAskerSurnameField.getText().isEmpty()){
                        InfoText.setText("Please fill all the fields");
                        InfoText.setForeground(new Color(194, 0, 0));
                    } else {
                        DBM.addMission(new Mission(MissionDescriptionField.getText(), 0)); // TODO: Trouver un moyen de récupérer l'ID
                    }

                }
            }
        });

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

    public static class ChangeForm implements ActionListener {
        private JPanel View;
        private ViewManager VM;
        private Component form;

        public ChangeForm(Component form, ViewManager VM, JPanel View) {
            this.View = View;
            this.VM = VM;
            this.form = form;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            View.remove(2);
            VM.setView(form);
            View.add(form, 2);
            View.revalidate();
            View.repaint();
        }

    }


}
