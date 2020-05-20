package nl.lunchtag.resource.Lunchtag.entity;

import lombok.NoArgsConstructor;
import nl.lunchtag.resource.Lunchtag.entity.enums.LogType;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
public class Log extends BaseId{

    public Log(String logText, Account user, LogType logType) {
        this.logText = logText;
        this.user = user;
        this.logType = logType;
    }

    private String logText;

    @OneToOne(fetch = FetchType.EAGER)
    private Account user;

    private LogType logType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfLog = new Date();
}
