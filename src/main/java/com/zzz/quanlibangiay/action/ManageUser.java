/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.entity.xml.UserXML;
import com.zzz.quanlibangiay.enums.UserRole;
import com.zzz.quanlibangiay.enums.UserStatus;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coole
 */
public class ManageUser {

    private static final String FILE_NAME = "Users.xml";
    private List<User> userList;

    public ManageUser() {
        UserXML userXML = (UserXML) FileUtils.readXMLFile(FILE_NAME, UserXML.class);
        if (userXML == null || userXML.getUsers() == null) {
            userList = new ArrayList<>();
        } else {
            userList = userXML.getUsers();
        }
    }

    public boolean authenticate(String username, String password) {
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(username) && user.getPassword().equals(password) && user.getStatus() == UserStatus.WORKING) {
                return true;
            }
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public User getUserById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUserName(String userName) {
        for (User user : userList) {
            if (user.getUserName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        return null;
    }

    public List<User> searchUserByFullname(String keyword) {
        List<User> result = new ArrayList<>();
        for (User u : userList) {
            if (u.getFullName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(u);
            }
        }
        return result;
    }

    public boolean isUsernameExists(String username, int excludeId) {
        for (User u : getAllUsers()) {
            if (u.getUserName().equalsIgnoreCase(username) && u.getId() != excludeId) {
                return true;
            }
        }
        return false;
    }

    public boolean isPhoneExists(String phone, int excludeId) {
        for (User u : getAllUsers()) {
            if (u.getPhoneNumber().equals(phone) && u.getId() != excludeId) {
                return true;
            }
        }
        return false;
    }


    public boolean addUser(User user) {
        user.setId(getNextId());
        user.setCreatedDate(new java.util.Date());
        userList.add(user);
        saveToFile();
        return true;
    }

    public boolean updateUser(User updatedUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == updatedUser.getId()) {
                userList.set(i, updatedUser);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        User user = getUserById(id);
        if (user != null) {
            userList.remove(user);
            saveToFile();
            return true;
        }
        return false;
    }

    private int getNextId() {
        int max = 0;
        for (User user : userList) {
            if (user.getId() > max) {
                max = user.getId();
            }
        }
        return max + 1;
    }

    private void saveToFile() {
        UserXML userXML = new UserXML();
        userXML.setUsers(userList);
        FileUtils.writeXMLtoFile(FILE_NAME, userXML);
    }

    public List<User> getUsersByStatus(UserStatus status) {
        List<User> result = new ArrayList<>();
        for (User user : userList) {
            if (user.getStatus() == status) {
                result.add(user);
            }
        }
        return result;
    }

    public List<User> getUsersByRole(UserRole role) {
        List<User> result = new ArrayList<>();
        for (User user : userList) {
            if (user.getRole() == role) {
                result.add(user);
            }
        }
        return result;
    }

    public List<User> getAllStaff() {
        List<User> result = new ArrayList<>();
        for (User user : userList) {
            if (user.getRole() == UserRole.STAFF) {
                result.add(user);
            }
        }
        return result;
    }
}