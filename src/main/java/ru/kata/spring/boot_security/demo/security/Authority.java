package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.model.Role;


public class Authority implements GrantedAuthority {
    private final Role role;

    public Authority(Role role) {
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return "ROLE_" + role.getName();
    }
}
