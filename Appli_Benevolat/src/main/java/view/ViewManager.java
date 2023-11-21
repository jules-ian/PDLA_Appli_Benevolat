package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewManager {
    private ArrayList<Component> ViewList;
    private Component visibleComponent;


    public ViewManager(){
        this.ViewList = new ArrayList<>();
    }

    public JPanel createPanelView(){ // crée une vue sans l'afficher et l'ajoute à la liste
        JPanel v = new JPanel();
        ViewList.add(v);
        v.setVisible(false);
        return v;
    }
    public JScrollPane createScrollView(String[] data) {
        JList<String> liste_a_remplir = new JList<>(data);
        JScrollPane v = new JScrollPane(liste_a_remplir);
        ViewList.add(v);
        v.setVisible(false);
        return v;
    }

    public void set_all_views_invisible(){
        for(Component v : this.ViewList){
            v.setVisible(false);
        }
    }

    public void set_view_of_frame(JFrame frame, Container v){ // Affiche la vue passée en argument
        set_all_views_invisible();
        frame.setContentPane(v);
        setVisible(v);
        visibleComponent = v;
    }

    public void setView(Component v){ // Affiche la vue passée en argument
        set_all_views_invisible();
        setVisible((Container) v);
        this.visibleComponent = v;
    }
    public void setVisible(Container c){
        c.setVisible(true);
    }

    public Component getVisible(){
        return visibleComponent;
    }

}
