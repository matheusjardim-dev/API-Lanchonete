package br.com.lanchonete.apilanchonete.user;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message){
        super(message);
    }
}
