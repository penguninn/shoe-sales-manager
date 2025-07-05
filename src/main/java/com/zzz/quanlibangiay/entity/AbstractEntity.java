/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author coole
 */
public class AbstractEntity implements Serializable{
    
    private int id;
    private Date createdDate;
    private String description;

    public AbstractEntity() {
    }

    public AbstractEntity(int id, Date createdDate, String description) {
        this.id = id;
        this.createdDate = createdDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
