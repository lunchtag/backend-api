package nl.lunchtag.resource.Lunchtag.entity;

import lombok.*;
import nl.lunchtag.resource.Lunchtag.entity.enums.LogType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
public class Log extends BaseId implements Serializable {

    public Log(String logText, Account user, LogType logType) {
        this.logText = logText;
        this.user = user;
        this.logType = logType;
    }

    public Log() {
    }

    private String logText;

    @OneToOne(fetch = FetchType.EAGER)
    private Account user;

    private LogType logType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfLog = new Date();
}
