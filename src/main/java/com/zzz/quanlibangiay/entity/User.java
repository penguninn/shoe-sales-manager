/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.entity;

import com.zzz.quanlibangiay.enums.UserRole;
import com.zzz.quanlibangiay.enums.UserStatus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "User")
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends AbstractEntity {

    private String userName;
    private String password;
    private String fullName;
    private UserRole role;
    private String name;
    private boolean gender;
    private String phoneNumber;
    private String address;
    private UserStatus status;
    private Date birthDate;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String fullName, UserRole role, String name, boolean gender, String phoneNumber, String address, UserStatus status, Date birthDate, int id, Date createdDate, String description) {
        super(id, createdDate, description);
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.birthDate = birthDate;
    }

    public User(String userName, String password, String fullName, UserRole role, int id, Date createdDate, String description) {
        super(id, createdDate, description);
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
