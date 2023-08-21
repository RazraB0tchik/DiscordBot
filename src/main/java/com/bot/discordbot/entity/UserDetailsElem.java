package com.bot.discordbot.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetailsElem implements org.springframework.security.core.userdetails.UserDetails {

    private String discordId;
    private String password;
    private Collection<GrantedAuthority> authority;
    private Boolean activity;

    public UserDetailsElem(String discordId, String password, Collection<GrantedAuthority> authority, Boolean activity) {
        this.discordId = discordId;
        this.password = password;
        this.authority = authority;
        this.activity = activity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return discordId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return activity;
    }

    @Override
    public boolean isAccountNonLocked() {
        return activity;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return activity;
    }

    @Override
    public boolean isEnabled() {
        return activity;
    }
}
