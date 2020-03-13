package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Notification {
    private long notificationID;
    private String name;
    private Date date;
    private String description;

    public Notification(long notificationID, String name, Date date, String description) {
        this.notificationID = notificationID;
        this.name = name;
        this.date = date;
        this.description = description;
    }
}
