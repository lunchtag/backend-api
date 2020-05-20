package nl.lunchtag.resource.Lunchtag.entity;

import lombok.Getter;
import lombok.Setter;
import nl.lunchtag.resource.Lunchtag.entity.entity_logger_listeners.LunchLoggerListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@EntityListeners(LunchLoggerListener.class)
@Entity
@Getter
@Setter
@Table(name = "Lunch")
public class Lunch extends BaseId implements Serializable {

    private Date date;

    @OneToOne(fetch = FetchType.EAGER)
    private Account account;

    public Lunch() {}

    public Lunch(Date date, Account account) {
        this.date = date;
        this.account = account;
    }
}
