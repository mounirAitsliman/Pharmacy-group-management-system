package main;

public class Admins extends Users{


    public Admins(int uid, String firstname, String lastname, String username, String password, String cin, String role) {
        super(uid, firstname, lastname, username, password, cin, "Admin");

    }
}
