package com.opsu.models;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigInteger;
/**
 * In this class we store information about Service
 * @author group 183
 * @version 2.1
 */
public class Service {
    /** field id
     * field's restriction
     * has limitation in value*/
    @Min(value=0,message ="Service id is not correct")
    private BigInteger id;


    /** field service's name
     * can't be empty
     * has limit in size
     */
    @NotEmpty(message ="Service can not be empty")
    @Size(min=2,max=20,message = "Name of the service should contain from 2 to 20 digits")
    private String name;

    /** field is service custom  */
    private Boolean isCustom;

    /** field service's description */
    private String description;

    /** receives information about service id
     * @return service id */
    public BigInteger getId() {
        return id;
    }
    /** inserts information  about service id
     * @param id  service  */
    public void setId(BigInteger id) {
        this.id = id;
    }

    /** receives information about service name
     * @return service's name */
    public String getName() {
        return name;
    }
    /** inserts information  about service name
     * @param name service's name */
    public void setName(String name) {
        this.name = name;
    }

    /** receives information about service description
     * @return service's description */
    public String getDescription() {
        return description;
    }
    /** inserts information  about service description
     * @param description service's description  */
    public void setDescription(String description) {
        this.description = description;
    }

    /** receives information about custom service
     * @return is service custom  */
    public Boolean getCustom() {
        return isCustom;
    }
    /**inserts information  about custom services
     *  @param isCustom is service custom*/
    public void setCustom(Boolean isCustom) {
        this.isCustom = isCustom;
    }

    /**
     * This constructor creates new service
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