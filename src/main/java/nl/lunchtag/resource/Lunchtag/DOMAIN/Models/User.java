package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private List<Lunch> lunches = new ArrayList<>();

    public User(long userID, String name, String lastname, String email, String username, String password) {
        this.userID = userID;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public void addLunch(Lunch lunch)
    {
        lunches.add(lunch);
    }
    public Lunch getLunchByID(long lunchID)
    {
        Lunch lunch = lunches.stream()
                .filter( lunch1 -> lunchID == lunch1.getLunchID())
                .findFirst()
                .orElse(null);
        return lunch;
    }

    public void removeLunch(long lunchID)
    {
        lunches.remove(getLunchByID(lunchID));
    }
}
