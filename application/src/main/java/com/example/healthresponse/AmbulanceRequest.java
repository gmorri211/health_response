package com.example.healthresponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AmbulanceRequest {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String location;
    private String injuryDesc;
    private Integer activeStatus = 0;
    private String ambulance;
    private String hospital;

    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    public Integer getId(){return this.id;}
    public void setId(Integer id){this.id = id;}

    public String getLocation(){return this.location;}
    public void setLocation(String location){this.location = location;}

    public String getInjuryDesc(){return this.injuryDesc;}
    public void setInjuryDesc(String injuryDesc){this.injuryDesc = injuryDesc;}

    public Integer getActiveStatus(){return this.activeStatus;}
    public void setActiveStatus(Integer activeStatus){this.activeStatus = activeStatus;}

    public String getAmbulance(){return this.ambulance;}
    public void setAmbulance(String ambulance){this.ambulance = ambulance;}

    public String getHospital(){return this.hospital;}
    public void setHospital(String hospital){this.hospital = hospital;}
}
