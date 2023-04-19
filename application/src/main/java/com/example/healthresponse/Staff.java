package com.example.healthresponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer staffType;

    public Integer getId(){return id;}

    public void setId(Integer id){this.id = id;}

    public String getUsername(){return username;}

    public void setUsername(String username){this.username = username;}

    public String getPassword(){return password;}

    public void setPassword(String password){
        this.password = password;
    }

    public Integer getStaffType(){return staffType;}

    public void setStaffType(Integer staffType){this.staffType = staffType;}

}
