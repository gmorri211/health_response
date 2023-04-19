package com.example.healthresponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ERPatient {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String injuryDesc;
    private String urgencyLevel;

    public Integer getId(){return id;}

    public void setId(Integer id){this.id = id;}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getInjuryDesc(){return injuryDesc;}

    public void setInjuryDesc(String injuryDesc){
        this.injuryDesc = injuryDesc;
    }

    public String getUrgencyLevel(){return urgencyLevel;}

    public void setUrgencyLevel(String urgencyLevel){this.urgencyLevel = urgencyLevel;}

}
