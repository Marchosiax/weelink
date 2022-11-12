package com.marchosiax.weelink.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "VARCHAR(200)", nullable = false, unique = true)
    private String label;

    public Space() {

    }

    public Space(String label) {
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
