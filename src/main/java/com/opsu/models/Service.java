package com.opsu.models;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigInteger;
/**
 * Class Service
 * @author group 183
 * @version 2.1
 */
public class Service {
    /** field id */
    @Min(value=0,message ="Service id is not correct")
    private BigInteger id;
    /** field service's name */
    @NotEmpty(message ="Service can not be empty")
    @Size(min=2,max=20,message = "Name of the service should contain from 2 to 20 digits")
    private String name;

    /** field is service custom  */
    private Boolean isCustom;

    /** field service's description */
    private String description;

    /** @return service id */
    public BigInteger getId() {
        return id;
    }
    /** @param id  service  */
    public void setId(BigInteger id) {
        this.id = id;
    }
    /** @return service's name */
    public String getName() {
        return name;
    }
    /** @param name service's name */
    public void setName(String name) {
        this.name = name;
    }
    /** @return service's description */
    public String getDescription() {
        return description;
    }
    /** @return is service custom  */
    public Boolean getCustom() {
        return isCustom;
    }
    /** @param isCustom is service custom*/
    public void setCustom(Boolean isCustom) {
        this.isCustom = isCustom;
    }
    /** @param description service's description  */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * constructor- create service
     * @param id service service id
     * @param  name service's name
     * @param isCustom is service custom
     * @param description service's description
     */
    public Service (BigInteger id, String name, String description, Boolean isCustom) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isCustom = isCustom;
    }
}