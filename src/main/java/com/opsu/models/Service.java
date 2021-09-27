package com.opsu.models;

import java.math.BigInteger;

public class Service {
    private BigInteger id;
    private String name;
    private String description;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Service (BigInteger id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;

    }
}