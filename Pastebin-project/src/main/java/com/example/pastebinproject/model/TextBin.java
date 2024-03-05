package com.example.pastebinproject.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;


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


    @Override
    public String toString() {
        return "TextBin{" +
                "id=" + id +
                ", textOfBin='" + textOfBin + '\'' +
                ", hashOfBin=" + hashOfBin +
                '}';
    }
}
