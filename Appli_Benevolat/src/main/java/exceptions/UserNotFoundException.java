package exceptions;

public class UserNotFoundException extends Exception{
    String message;
    int id;
    public UserNotFoundException(int id){
        this.id = id;
        this.message = "User with id " + this.id + " not found in database";
    }

    public void printMessage(){
        System.out.println(message);
    }



}
