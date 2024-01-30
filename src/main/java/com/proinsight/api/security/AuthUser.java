package com.proinsight.api.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthUser extends User {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String fullName;

    public AuthUser(com.proinsight.domain.model.User user, Collection<? extends GrantedAuthority> autorities) {
        super(user.getEmail(), user.getPassword(), autorities);

        this.fullName = user.getName();
        this.userId = user.getId();
    }

}
