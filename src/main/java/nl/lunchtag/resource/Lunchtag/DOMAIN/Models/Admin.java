package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

public class Admin {

    private long adminID;
    private String username;
    private String password;
    private String repeatedPassword;

    public Admin(long adminID, String username, String password) {
        this.adminID = adminID;
        this.username = username;
        this.password = password;
    }


}
