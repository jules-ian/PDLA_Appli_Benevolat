package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {

    private static void createAndShowGUI() {

        ViewManager VM = new ViewManager();

        // Create and set up the window.
        JFrame frame = new JFrame("Handic App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel Home = VM.createView(); // première page affichée sur le GUI
        JPanel AddItem = VM.createView(); // page pour ajouter un User ou une Mission
        JPanel ShowDB = VM.createView(); // page pour afficher une liste de Users ou Missions

        Home.setLayout(new BorderLayout());
        AddItem.setLayout((new BoxLayout(AddItem, BoxLayout.PAGE_AXIS))); // Vertical BoxLayout



        JButton buttonAdd = new JButton("Add User");
        JButton buttonViewDB = new JButton("View DataBase");

        JButton buttonAsker = new JButton("Asker");
        JButton buttonVolunteer = new JButton("Volunteer");
        JButton buttonMission = new JButton("Mission");

        JButton buttonReturn = new JButton("Return");


        JLabel HomeTitle = new JLabel("Welcome to Handic App !", JLabel.CENTER);
        JLabel AddItemTitle = new JLabel("Here you can add an item to the DB", JLabel.CENTER);


        JPanel HomeButtons = new JPanel(new FlowLayout());
        JPanel AddButtons = new JPanel(new FlowLayout());


        AddButtons.add(buttonAsker);
        AddButtons.add(buttonVolunteer);
        AddButtons.add(buttonMission);

        HomeButtons.add(buttonAdd);
        HomeButtons.add(buttonViewDB);


        Home.add(HomeTitle, BorderLayout.PAGE_START);
        Home.add(HomeButtons, BorderLayout.CENTER);

        AddItem.add(AddItemTitle);
        AddItem.add(AddButtons);
        AddItem.add(Box.createRigidArea(new Dimension(0, 50)));
        AddItem.add(buttonReturn);



        VM.setView(frame, Home);

        ChangeView ShowHome = new ChangeView(frame, VM, Home);
        ChangeView ShowAddItem = new ChangeView(frame, VM, AddItem);

        buttonAdd.addActionListener(ShowAddItem);
        buttonReturn.addActionListener(ShowHome);



        //CounterComponent textField = new CounterComponent();

        //JLabel emptyLabel = new JLabel("Greetings Swinger", JLabel.CENTER);
        //JLabel Label2 = new JLabel("The Label", JLabel.CENTER
/*
        CounterComponent counter = new CounterComponent();

        buttonSub.addActionListener(new PrintSO(textField,2));
        buttonAdd.addActionListener(new PrintSO(textField,1));
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
            VM.setView(frame, View);
        }

    }



}