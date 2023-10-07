package com.example.classassignment.model;

import jakarta.persistence.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Entity
@Table(name ="user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String userAuthority;

    @Override
    public List getAuthorities() {
        SimpleGrantedAuthority autho=  new SimpleGrantedAuthority(getUserAuthority());
        List list= List.of(autho);
        System.out.print("sadsadasdasdasdasdasdasd::"+list);
        return  list;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }


public User(){

}


    public User(Integer id, String name, String email, String password, String userAuthority) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userAuthority = userAuthority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(String userAuthority) {
        this.userAuthority = userAuthority;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
