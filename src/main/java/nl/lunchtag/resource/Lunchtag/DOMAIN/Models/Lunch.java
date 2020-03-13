package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
public class Lunch {
    private long lunchID;
    private Date date;
}
