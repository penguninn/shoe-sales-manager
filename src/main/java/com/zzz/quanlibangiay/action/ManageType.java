/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.Type;
import com.zzz.quanlibangiay.entity.xml.TypeXML;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coole
 */
public class ManageType {

    private static final String FILE_NAME = "Types.xml";
    private List<Type> typeList;

    public ManageType() {
        TypeXML typeXML = (TypeXML) FileUtils.readXMLFile(FILE_NAME, TypeXML.class);
        if (typeXML == null || typeXML.getShoeTypes() == null) {
            typeList = new ArrayList<>();
        } else {
            typeList = typeXML.getShoeTypes();
        }
    }

    public List<Type> getAllShoeTypes() {
        return typeList;
    }

    public Type getShoeTypeById(int id) {
        for (Type st : typeList) {
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }

    public boolean addShoeType(Type type) {
        type.setId(getNextId());
        type.setCreatedDate(new java.util.Date());
        typeList.add(type);
        saveToFile();
        return true;
    }

    public boolean updateShoeType(Type updated) {
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).getId() == updated.getId()) {
                typeList.set(i, updated);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteShoeType(int id) {
        Type st = getShoeTypeById(id);
        if (st != null) {
            typeList.remove(st);
            saveToFile();
            return true;
        }
        return false;
    }

    public List<Type> searchShoeTypeByName(String keyword) {
        List<Type> result = new ArrayList<>();
        for (Type st : typeList) {
            if (st.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(st);
            }
        }
        return result;
    }

    private int getNextId() {
        int max = 0;
        for (Type st : typeList) {
            if (st.getId() > max) {
                max = st.getId();
            }
        }
        return max + 1;
    }

    private void saveToFile() {
        TypeXML typeXML = new TypeXML();
        typeXML.setShoeTypes(typeList);
        FileUtils.writeXMLtoFile(FILE_NAME, typeXML);
    }
}
