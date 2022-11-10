package com.marchosiax.weelink.model;

import com.marchosiax.weelink.model.enums.LinkType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "space_id")
    private Space space;
    @OneToOne
    @JoinColumn(name = "auth_id")
    private LinkAuthentication linkAuthentication;
    @Column(columnDefinition = "TEXT", nullable = false, unique = true)
    private String alias;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String origin;
    private LocalDateTime expirationTime;
    private LocalDateTime availabilityTime;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(100)", nullable = false)
    private LinkType type;
    @Column(columnDefinition = "VARCHAR(36)", nullable = false, unique = true)
    private String uuid;

    public Link() {

    }

    public Link(
            Space space,
            LinkAuthentication linkAuthentication,
            String alias,
            String origin,
            LocalDateTime expirationTime,
            LocalDateTime availabilityTime,
            LinkType type
    ) {
        this.space = space;
        this.linkAuthentication = linkAuthentication;
        this.alias = alias;
        this.origin = origin;
        this.expirationTime = expirationTime;
        this.availabilityTime = availabilityTime;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

    public LinkAuthentication getLinkAuthentication() {
        return linkAuthentication;
    }

    public void setLinkAuthentication(LinkAuthentication linkAuthentication) {
        this.linkAuthentication = linkAuthentication;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public LocalDateTime getAvailabilityTime() {
        return availabilityTime;
    }

    public void setAvailabilityTime(LocalDateTime availabilityTime) {
        this.availabilityTime = availabilityTime;
    }

    public LinkType getType() {
        return type;
    }

    public void setType(LinkType type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
