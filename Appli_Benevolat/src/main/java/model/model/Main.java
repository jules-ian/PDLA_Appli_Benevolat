package model.model;

public class Main {
    public static void main(String[] args) {

        Asker a1 = new Asker("Landi", "Capé", 99);
        Volunteer v1 = new Volunteer("Barnavon", "Jules-ian", 5); //(age mental)

        a1.create_question("G faim");
        v1.display_allquestion();
        a1.create_question("G soif");
        v1.display_allquestion();
    }

}