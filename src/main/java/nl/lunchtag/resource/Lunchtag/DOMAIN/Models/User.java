package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private long userID;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String repeatedPassword;

    public User(long userID, String name, String lastname, String email, String username, String password) {
        this.userID = userID;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
