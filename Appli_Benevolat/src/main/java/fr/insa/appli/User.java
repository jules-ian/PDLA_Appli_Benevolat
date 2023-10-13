package fr.insa.appli;

public abstract class User {
    /* Attribut */
    protected String nom;
    protected String prenom;
    protected int Uid;
    protected int age;

    protected static int userCount = 0;


    /*Constructeur*/

    public User (String nom,String prenom,int age) {
        this.nom=nom;
        this.prenom=prenom;
        this.Uid =userCount;
        this.age=age;

        this.userCount++;
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
        return this.Uid;
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
        return Uid + " | " + nom + " " + prenom + " " + age + " ans";
    }
}

