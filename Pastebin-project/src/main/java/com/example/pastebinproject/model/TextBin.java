package com.example.pastebinproject.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;


@Component
@Entity
public class TextBin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Transient
    private String textOfBin;

    @Column
    private int hashOfBin;

    @Column
    private LocalDateTime expiry_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTextOfBin() {
        return textOfBin;
    }

    public void setTextOfBin(String textOfBin) {
        this.textOfBin = textOfBin;
    }

    public int getHashOfBin() {
        return hashOfBin;
    }

    public void setHashOfBin(int hashOfBin) {
        this.hashOfBin = hashOfBin;
    }

    public LocalDateTime getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(LocalDateTime expiry_time) {
        this.expiry_time = expiry_time;
    }

    @Override
    public String toString() {
        return "TextBin{" +
                "id=" + id +
                ", textOfBin='" + textOfBin + '\'' +
                ", hashOfBin=" + hashOfBin +
                '}';
    }
}
