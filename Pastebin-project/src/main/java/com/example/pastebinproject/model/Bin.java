package com.example.pastebinproject.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;


@Component
@Entity
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Transient
    private String content;

    @Column
    private int hash;

    @Column
    private LocalDateTime expiry_time;

    @Column
    private boolean expired = false;

    @Column
    private String URL;

    @Column
    private String category;

    @Column
    private String password;

    @Transient
    private boolean password_match = true;

    @Column
    private String cloud_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHash() {
        return hash;
    }

    public void setHash(int hash) {
        this.hash = hash;
    }

    public LocalDateTime getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(LocalDateTime expiry_time) {
        this.expiry_time = expiry_time;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPassword_match() {
        return password_match;
    }

    public void setPassword_match(boolean password_match) {
        this.password_match = password_match;
    }

    public String getCloud_id() {
        return cloud_id;
    }

    public void setCloud_id(String cloud_id) {
        this.cloud_id = cloud_id;
    }

    @Override
    public String toString() {
        return "Bin{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", hash=" + hash +
                ", expiry_time=" + expiry_time +
                ", expired=" + expired +
                ", URL='" + URL + '\'' +
                ", category='" + category + '\'' +
                ", password='" + password + '\'' +
                ", password_match=" + password_match +
                ", cloud_id='" + cloud_id + '\'' +
                '}';
    }
}
