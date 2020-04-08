package nl.lunchtag.resource.Lunchtag.DOMAIN.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.lunchtag.resource.Lunchtag.DOMAIN.Models.enums.Role;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.util.*;
>>>>>>> dbb14d693a9b985c044efbca6b0a032cae618aca
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Account")

public class Account implements UserDetails {
    @Id
    @Type(type="uuid-char")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private UUID accountID;

    private String name;
<<<<<<< HEAD
//    private String lastname;
//    private String email;
//    private String username;
//    private String password;
=======
    private String lastName;
    private String email;

    @JsonIgnore
    private String password;
    private Role role = Role.USER;

>>>>>>> dbb14d693a9b985c044efbca6b0a032cae618aca


    private List<Account> accounts = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    private Set<Lunch> lunches = new HashSet<>();

    public Account(UUID accountID, String name, String lastName, String email, String password) {
        this.accountID = accountID;
        this.name = name;
<<<<<<< HEAD
//        this.lastname = lastname;
//        this.email = email;
//        this.username = username;
//        this.password = password;
=======
        this.lastName = lastName;
        this.email = email;
        this.password = password;
>>>>>>> dbb14d693a9b985c044efbca6b0a032cae618aca
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


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toString()));
    }


    @JsonIgnore
    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

<<<<<<< HEAD
    public void register(Account account)
    {
        accounts.add(account);
    }
=======
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }


>>>>>>> dbb14d693a9b985c044efbca6b0a032cae618aca
}
