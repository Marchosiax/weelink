package com.marchosiax.weelink.model;

import com.marchosiax.weelink.model.enums.Browser;
import com.marchosiax.weelink.model.enums.DeviceType;
import com.marchosiax.weelink.model.enums.OS;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class LinkHit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "link_id")
    private Link link;
    @Column(nullable = false, unique = true)
    private LocalDate date;
    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    private String ip;
    @Column(columnDefinition = "TEXT")
    private String sourceDomain;
    @Column(columnDefinition = "VARCHAR(20)")
    private DeviceType deviceType;
    @Column(columnDefinition = "VARCHAR(20)")
    private OS os;
    @Column(columnDefinition = "VARCHAR(20)")
    private Browser browser;
    @Column(columnDefinition = "VARCHAR(50)")
    private String country;

    public LinkHit() {

    }

    public LinkHit(Link link, LocalDate date, String ip, String sourceDomain) {
        this.link = link;
        this.date = date;
        this.ip = ip;
        this.sourceDomain = sourceDomain;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSourceDomain() {
        return sourceDomain;
    }

    public void setSourceDomain(String sourceDomain) {
        this.sourceDomain = sourceDomain;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public Browser getBrowser() {
        return browser;
    }

    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
