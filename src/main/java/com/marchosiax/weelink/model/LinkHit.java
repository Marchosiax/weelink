package com.marchosiax.weelink.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LinkHit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "link_id")
    private Link link;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    private String ip;
    @Column(columnDefinition = "TEXT")
    private String host;
    @Column(columnDefinition = "VARCHAR(100)")
    private String deviceType;
    @Column(columnDefinition = "VARCHAR(100)")
    private String os;
    @Column(columnDefinition = "VARCHAR(100)")
    private String browser;

    public LinkHit() {

    }

    public LinkHit(Link link, String ip) {
        this.link = link;
        this.ip = ip;
        this.date = LocalDateTime.now();
    }

    public LinkHit(Link link, String ip, String host, String deviceType, String os, String browser) {
        this.link = link;
        this.date = LocalDateTime.now();
        this.ip = ip;
        this.host = host;
        this.deviceType = deviceType;
        this.os = os;
        this.browser = browser;
    }

    public LinkHit(Link link, LocalDateTime date, String ip, String host, String deviceType, String os, String browser) {
        this.link = link;
        this.date = date;
        this.ip = ip;
        this.host = host;
        this.deviceType = deviceType;
        this.os = os;
        this.browser = browser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String sourceDomain) {
        this.host = host;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
