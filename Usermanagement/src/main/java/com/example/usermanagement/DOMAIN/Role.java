package com.example.usermanagement.DOMAIN;

import java.util.HashSet;
import java.util.Set;

public class Role {

    private long id;

    private RoleType roltype;

    private Set<User> users = new HashSet<>();

    public Role(RoleType roltype) {
        this.roltype = roltype;
    }
}
