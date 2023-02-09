package com.example.examen.domain;

import java.time.LocalDateTime;

public class Nevoie extends Entity<Long>{
    private String titlu;
    private String descriere;
    private LocalDateTime deadline;
    private long omInNevoie;
    private long omSalvator;
    private String status;

    public Nevoie(long id, String titlu, String descriere, LocalDateTime deadline, long omInNevoie, long omSalvator, String status) {
        this.id = id;
        this.titlu = titlu;
        this.descriere = descriere;
        this.deadline = deadline;
        this.omInNevoie = omInNevoie;
        this.omSalvator = omSalvator;
        this.status = status;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getDescriere() {
        return descriere;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public long getOmInNevoie() {
        return omInNevoie;
    }

    public long getOmSalvator() {
        return omSalvator;
    }

    public String getStatus() {
        return status;
    }
}
