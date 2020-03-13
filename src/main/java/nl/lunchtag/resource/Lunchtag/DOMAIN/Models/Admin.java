package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

import java.util.HashSet;
import java.util.Set;

public class Admin {

    private long adminID;
    private String username;
    private String password;
    private String repeatedPassword;
    private Set<User> users = new HashSet<>();

    public Admin(long adminID, String username, String password) {
        this.adminID = adminID;
        this.username = username;
        this.password = password;
    }


    public User getUserByID(long userID)
    {
        User user = users.stream()
                .filter( user1 -> userID == user1.getUserID())
                .findFirst()
                .orElse(null);
        return user;
    }
    public void addUser(User user)
    {
        users.add(user);
    }
    public void removeUser(long userID)
    {
        users.remove(getUserByID(userID));
    }

}
