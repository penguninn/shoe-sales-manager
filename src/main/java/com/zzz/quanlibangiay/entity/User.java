/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.entity;

import com.zzz.quanlibangiay.enums.UserRole;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author coole
 */
@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends AbstractEntity {
    
    private String userName;
    private String password;
    private String fullName;
    private UserRole role;

    public User() {
    }

    public User(String userName, String password, String fullName, UserRole role, int id, Date createdDate, String description) {
        super(id, createdDate, description);
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
    
    
}
