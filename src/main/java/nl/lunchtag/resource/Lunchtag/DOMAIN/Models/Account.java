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
import java.util.*;
import java.util.HashSet;
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
    private String lastName;
    private String email;

    @JsonIgnore
    private String password;
    private Role role = Role.USER;



    @OneToMany(fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    private Set<Lunch> lunches = new HashSet<>();

    public Account(UUID accountID, String name, String lastName, String email, String password) {
        this.accountID = accountID;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
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


}
