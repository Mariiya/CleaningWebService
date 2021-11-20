package com.opsu.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigInteger;

public class Service {
    @Min(value=0,message ="Service id is not correct")
    private BigInteger id;

    @NotEmpty(message ="Service can not be empty")
    @Size(min=2,max=20,message = "Name of the service should contain from 2 to 20 digits")
    private String name;

    private Boolean isCustom;


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

    public Boolean getCustom() {
        return isCustom;
    }

    public void setCustom(Boolean isCustom) {
        this.isCustom = isCustom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Service (BigInteger id, String name, String description, Boolean isCustom) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isCustom = isCustom;
    }
}