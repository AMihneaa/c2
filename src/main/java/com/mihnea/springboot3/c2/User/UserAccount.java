package com.mihnea.springboot3.c2.User;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class UserAccount {

    private @Id @GeneratedValue Long id;
    private String userName;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> authorities = new ArrayList<>();

    protected UserAccount() {}

    public UserAccount(String username, String password, String... authorities) {
        this.userName = username;
        this.password = password;
        this.authorities = Arrays.stream(authorities) //
                .map(SimpleGrantedAuthority::new) //
                .map(GrantedAuthority.class::cast) //
                .toList();
    }

    public UserDetails asUser() {
        return User.withDefaultPasswordEncoder() //
                .username(getUsername()) //
                .password(getPassword()) //
                .authorities(getAuthorities()) //
                .build();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            this.authorities = new ArrayList<>();
        }
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserAccount user = (UserAccount) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName)
                && Objects.equals(password, user.password) && Objects.equals(authorities, user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, authorities);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + userName + '\'' + ", password='" + password + '\'' + ", authorities="
                + authorities + '}';
    }
}