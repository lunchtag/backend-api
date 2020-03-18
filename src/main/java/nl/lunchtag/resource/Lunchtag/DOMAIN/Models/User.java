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
    private List<LunchDate> lunchDates = new ArrayList<LunchDate>();


    public User(long userID, String name, String lastname, String email, String username, String password) {
        this.userID = userID;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    public void addLunch(LunchDate lunchdate)
    {
        lunchDates.add(lunchdate);
    }
    public LunchDate getLunchDayByID(long lunchDayID)
    {
        LunchDate date = lunchDates.stream()
        .filter(lunchDate -> lunchDayID == lunchDate.getId())
                .findFirst()
                .orElse(null);
        return date;
    }

    public void removeLunch(long lunchID)
    {
        lunchDates.remove(getLunchDayByID(lunchID));
    }
}
