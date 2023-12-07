package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/** A ViewManager allows to manage views in a JFrame : We can choose the active view and hide all the others */
public class ViewManager {
    private ArrayList<Component> ViewList;
    private Component visibleView;


    public ViewManager(){
        this.ViewList = new ArrayList<>();
    }

    /** Creates a new Component and adds it to the list */
    public Component createComponent(){
        Component v = new Component(){};
        ViewList.add(v);
        v.setVisible(false);
        return v;
    }

    /** Creates a new JLabel and adds it to the list */
    public JLabel createLabel(){
        JLabel v = new JLabel(){};
        ViewList.add(v);
        v.setVisible(false);
        return v;
    }

    /** Creates a new JPanel and adds it to the list */
    public JPanel createPanelView(){
        JPanel v = new JPanel();
        ViewList.add(v);
        v.setVisible(false);
        return v;
    }

    /** Creates a new JScrollPane and adds it to the list */
    public JScrollPane createScrollView(DefaultListModel<String> data) {
        JList<String> liste_a_remplir = new JList<>(data);
        JScrollPane v = new JScrollPane(liste_a_remplir);
        ViewList.add(v);
        v.setVisible(false);
        return v;
    }

    /** Hides all the views */
    public void set_all_views_invisible(){
        for(Component v : this.ViewList){
            v.setVisible(false);
        }
    }

    /** Displays the view in the JFrame passed as argument */
    public void set_view_of_frame(JFrame frame, Container v){
        set_all_views_invisible();
        frame.setContentPane(v);
        setVisible(v);
        visibleView = v;
    }

    /** Displays the view passed as an argument */
    public void setView(Component v){
        set_all_views_invisible();
        setVisible((Container) v);
        this.visibleView = v;
    }
    public void setVisible(Container c){
        c.setVisible(true);
    }

    public Component getVisible(){
        return visibleView;
    }


}
