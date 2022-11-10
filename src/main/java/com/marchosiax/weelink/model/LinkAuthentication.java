package com.marchosiax.weelink.model;

import javax.persistence.*;

@Entity
public class LinkAuthentication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    public LinkAuthentication(){

    }

    public LinkAuthentication(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
