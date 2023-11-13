package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {

    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Handic App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container Accueil = new Container();
        Accueil.setLayout(new BorderLayout());

        JButton buttonAdd = new JButton("Add User");
        JButton buttonView = new JButton("View DataBase");
        JLabel Title = new JLabel("Welcome to Handic App !", JLabel.CENTER);

        JPanel panel = new JPanel(new FlowLayout());

        panel.add(buttonAdd);
        panel.add(buttonView);


        Accueil.add(Title, BorderLayout.PAGE_START);
        Accueil.add(panel, BorderLayout.CENTER);


        frame.setContentPane(Accueil);

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
        frame.pack();
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
        private Container View;

        public ChangeView(Container View) {
            this.View = View;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO:Mettre une liste de view en attribut eu GUI et créer la fonction ci-dessous.
            set_all_views_invisible()
            View.setVisible(true);

        }

    }



}
