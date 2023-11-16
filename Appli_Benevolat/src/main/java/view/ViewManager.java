package view;

import javax.swing.*;
import java.util.ArrayList;

public class ViewManager {
    public ArrayList<JPanel> ViewList;

    public ViewManager(){
        this.ViewList = new ArrayList<>();
    }

    public JPanel createView(){ // crée une vue sans l'afficher et l'ajoute à la liste
        JPanel v = new JPanel();
        ViewList.add(v);
        v.setVisible(false);
        return v;
    }

    public void set_all_views_invisible(){
        for(JPanel v : this.ViewList){
            v.setVisible(false);
        }
    }

    public void set_view_of_frame(JFrame frame, JPanel v){ // Affiche la vue passée en argument
        set_all_views_invisible();
        frame.setContentPane(v);
        setVisible(v);
    }

    public void setView(JPanel v){ // Affiche la vue passée en argument
        set_all_views_invisible();
        setVisible(v);
    }
    public void setVisible(JPanel c){
        c.setVisible(true);
    }

}
