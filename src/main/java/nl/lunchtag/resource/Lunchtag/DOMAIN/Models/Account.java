package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountID;

    private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;


    @OneToMany(fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    private Set<Lunch> lunches = new HashSet<>();

    public Account(long accountID, String name, String lastname, String email, String username, String password) {
        this.accountID = accountID;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void addLunch(Lunch lunch) {
        lunches.add(lunch);
    }

    public Lunch getLunchByID(long lunchID) {
        Lunch lunch = lunches.stream()
                .filter(lunch1 -> lunchID == lunch1.getLunchID())
                .findFirst()
                .orElse(null);
        return lunch;
    }

    public void removeLunchDayByID(long lunchDayID) {
        lunches.remove(getLunchByID(lunchDayID));
    }

    public Set<Lunch> getAllLunch() {
        return lunches;
    }
}
