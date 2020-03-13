package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

import java.util.HashSet;
import java.util.Set;

public class Account {
    private long accountID;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String repeatedPassword;
    private Set<Lunch> lunches = new HashSet<>();

    public Account(long accountID, String name, String lastname, String email, String username, String password) {
        this.accountID = accountID;
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
                .filter(lunch1 -> lunchID == (lunch1.getLunchID()))
                .findFirst()
                .orElse(null);
        return lunch;
    }



}
