package model;

public abstract class User {
    /* Attribut */
    protected String nom;
    protected String prenom;
    protected int uid;
    protected int age;

    protected static int userCount = 1;


    /*Constructeur*/

    public User (String nom,String prenom,int age) {
        this.nom=nom;
        this.prenom=prenom;
        this.uid =userCount;
        this.age=age;

        this.userCount++;
    }

    public User (String nom,String prenom,int age, int id) {
        this.nom=nom;
        this.prenom=prenom;
        this.uid =id;
        this.age=age;

    }

    /* Méthode */
    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public int getAge(){
        return age;
    }

    public int getUid(){
        return this.uid;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return uid + " | " + nom + " " + prenom + " " + age + " ans";
    }
}

