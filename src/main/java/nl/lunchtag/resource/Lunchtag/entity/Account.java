package nl.lunchtag.resource.Lunchtag.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.lunchtag.resource.Lunchtag.entity.entity_logger_listeners.AccountLoggerListener;
import nl.lunchtag.resource.Lunchtag.entity.enums.Role;
import org.apache.commons.lang3.RandomUtils;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.HashSet;
import java.util.Set;

@EntityListeners(AccountLoggerListener.class)
@Getter
@Setter
@Entity
@Table(name = "Account")
public class Account extends BaseId implements UserDetails, Serializable {

    private String name;

    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String pincode;

    private Boolean isNonLocked = true;

    private Role role = Role.USER;

    public Account() {}

    public Account(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Account(UUID accountId){
        this.setId(accountId);
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
        return this.isNonLocked;
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
