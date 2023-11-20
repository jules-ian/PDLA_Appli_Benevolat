package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {

    private static void createAndShowGUI() {

        ViewManager MainVM = new ViewManager();
        ViewManager FormVM = new ViewManager();

        // Create and set up the window.
        JFrame frame = new JFrame("Handic App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel Home = MainVM.createView(); // première page affichée sur le GUI
        JPanel AddItem = MainVM.createView(); // page pour ajouter un User ou une Mission
        JPanel ShowDB = MainVM.createView(); // page pour afficher une liste de Users ou Missions


        Home.setLayout(new BorderLayout());
        AddItem.setLayout((new BoxLayout(AddItem, BoxLayout.PAGE_AXIS))); // Vertical BoxLayout

        JPanel AskerForm = FormVM.createView();
        JPanel VolunteerForm = FormVM.createView();
        JPanel MissionForm = FormVM.createView();



        //Forms Initialization
        AskerForm.setLayout(new GridLayout(0, 2));
        VolunteerForm.setLayout(new GridLayout(0, 2));
        MissionForm.setLayout(new GridLayout(0, 2));

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
        JLabel AskerSurnameMission = new JLabel("Surname of asker :");
        JTextField AskerSurnameMissionField = new JTextField(20);
        JLabel AskerMissionName = new JLabel("Name of asker :");
        JTextField AskerMissionNameField = new JTextField(20);

        MissionForm.add(MissionDescription);
        MissionForm.add(MissionDescriptionField);
        MissionForm.add(AskerSurnameMission);
        MissionForm.add(AskerSurnameMissionField);
        MissionForm.add(AskerMissionName);
        MissionForm.add(AskerMissionNameField);

        //On crée la layout de la page d'accueil



        // Setting up form buttons
        JButton buttonAsker = new JButton("Asker");
        buttonAsker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Asker Appuyé!");
                AddItem.remove(2);
                FormVM.setView(AskerForm);
                AddItem.add(AskerForm, 2);
                AddItem.revalidate();
                AddItem.repaint();
            }
        });


        JButton buttonVolunteer = new JButton("Volunteer");
        buttonVolunteer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Volunteer Appuyé!");
                AddItem.remove(2);
                FormVM.setView(VolunteerForm);
                AddItem.add(VolunteerForm, 2);
                AddItem.revalidate();
                AddItem.repaint();
            }
        });
        JButton buttonMission = new JButton("Mission");
        buttonMission.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mission Appuyé!");
                AddItem.remove(2);
                FormVM.setView(MissionForm);
                AddItem.add(MissionForm, 2);
                AddItem.revalidate();
                AddItem.repaint();
            }
        });

        //Ajouter les boutons crée au dessus au panel
        JPanel AddButtons = new JPanel(new FlowLayout());
        AddButtons.add(buttonAsker);
        AddButtons.add(buttonVolunteer);
        AddButtons.add(buttonMission);




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
        AddItem.add(Box.createRigidArea(new Dimension(0, 50)));


        JPanel BottomButtons = new JPanel(new FlowLayout());

        JButton buttonReturn = new JButton("Return");
        BottomButtons.add(buttonReturn);

        JButton buttonAdd = new JButton("Add");
        buttonAdd.setBackground(new Color(0, 255, 0));
        BottomButtons.add(buttonAdd);

        AddItem.add(BottomButtons);


        //AddItem.add(AskerForm, 2);

        MainVM.set_view_of_frame(frame, Home);

        ChangeView ShowHome = new ChangeView(frame, MainVM, Home);
        ChangeView ShowAddItem = new ChangeView(frame, MainVM, AddItem);

        buttonAddUser.addActionListener(ShowAddItem);
        buttonReturn.addActionListener(ShowHome);




        //CounterComponent textField = new CounterComponent();

        //JLabel emptyLabel = new JLabel("Greetings Swinger", JLabel.CENTER);
        //JLabel Label2 = new JLabel("The Label", JLabel.CENTER
/*
        CounterComponent counter = new CounterComponent();

        buttonSub.addActionListener(new PrintSO(textField,2));
        buttonAddUser.addActionListener(new PrintSO(textField,1));
        buttonReset.addActionListener(new PrintSO(textField, 3));


        //frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.getContentPane().add(textField, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.PAGE_END);
        //frame.getContentPane().add(button, BorderLayout.PAGE_END);
        //frame.getContentPane().add(Label2, BorderLayout.PAGE_END);
*/
        // make window's dimension fit its content
        frame.setSize(500, 300);
        // Display the window.
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
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


}
