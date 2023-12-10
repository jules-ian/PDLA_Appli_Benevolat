package exceptions;

/** Thrown when the user corresponding to the given id is not found in the DB */
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
