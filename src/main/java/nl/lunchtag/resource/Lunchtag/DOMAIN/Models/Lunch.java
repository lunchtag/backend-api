package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "Lunch")
public class Lunch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long lunchID;
    private Date date;
    @JsonIgnore //als ik t ophaal wordt bank genegeerd in json
    @ManyToOne(fetch = FetchType.LAZY) // ophalen wanneerje erna vraagt
    @JoinColumn(name = "Account_ID")
    private Account account;
}
