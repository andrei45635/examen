package com.example.examen.domain;

import java.io.Serializable;

public class Entity<ID> implements Serializable {
    private final long serialVersionUID = 1234567L;
    protected ID id;

    public ID getId() {
        return id;
    }
}
