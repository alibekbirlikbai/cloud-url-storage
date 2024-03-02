package com.example.pastebinproject.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;


@Component
@Entity
public class TextBin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String textOfBin;

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
}
