package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

import java.util.HashSet;
import java.util.Set;

public class Admin {

    private long adminID;
    private String username;
    private String password;
    private String repeatedPassword;
    private Set<User> users = new HashSet<>();
    private Set<Notification> notifications = new HashSet<>();

    public Admin(long adminID, String username, String password) {
        this.adminID = adminID;
        this.username = username;
        this.password = password;
    }


    public User getUserByID(long userID) {
        User user = users.stream()
                .filter(user1 -> userID == user1.getUserID())
                .findFirst()
                .orElse(null);
        return user;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(long userID) {
        users.remove(getUserByID(userID));
    }

    public Set<User> getAllUsers() {
        return users;
    }

    public void createNotification(Notification notification) {
        notifications.add(notification);
    }

    public Notification getNotificationByID(long notificationID) {
        Notification notification = notifications.stream()
                .filter(notification1 -> notificationID == notification1.getNotificationID())
                .findFirst()
                .orElse(null);
        return notification;
    }

    public void removeNotifcationByID(long notificationID)
    {
        notifications.remove(getNotificationByID(notificationID));
    }


}
