/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.ShoeType;
import com.zzz.quanlibangiay.entity.xml.ShoeTypeXML;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coole
 */
public class ManageShoeType {

    private static final String FILE_NAME = "ShoeTypes.xml";
    private List<ShoeType> shoeTypeList;

    public ManageShoeType() {
        ShoeTypeXML shoeTypeXML = (ShoeTypeXML) FileUtils.readXMLFile(FILE_NAME, ShoeTypeXML.class);
        if (shoeTypeXML == null || shoeTypeXML.getShoeTypes() == null) {
            shoeTypeList = new ArrayList<>();
        } else {
            shoeTypeList = shoeTypeXML.getShoeTypes();
        }
    }

    public List<ShoeType> getAllShoeTypes() {
        return shoeTypeList;
    }

    public ShoeType getShoeTypeById(int id) {
        for (ShoeType st : shoeTypeList) {
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }

    public boolean addShoeType(ShoeType shoeType) {
        shoeType.setId(getNextId());
        shoeTypeList.add(shoeType);
        saveToFile();
        return true;
    }

    public boolean updateShoeType(ShoeType updated) {
        for (int i = 0; i < shoeTypeList.size(); i++) {
            if (shoeTypeList.get(i).getId() == updated.getId()) {
                shoeTypeList.set(i, updated);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteShoeType(int id) {
        ShoeType st = getShoeTypeById(id);
        if (st != null) {
            shoeTypeList.remove(st);
            saveToFile();
            return true;
        }
        return false;
    }

    public List<ShoeType> searchShoeTypeByName(String keyword) {
        List<ShoeType> result = new ArrayList<>();
        for (ShoeType st : shoeTypeList) {
            if (st.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(st);
            }
        }
        return result;
    }

    private int getNextId() {
        int max = 0;
        for (ShoeType st : shoeTypeList) {
            if (st.getId() > max) {
                max = st.getId();
            }
        }
        return max + 1;
    }

    private void saveToFile() {
        ShoeTypeXML shoeTypeXML = new ShoeTypeXML();
        shoeTypeXML.setShoeTypes(shoeTypeList);
        FileUtils.writeXMLtoFile(FILE_NAME, shoeTypeXML);
    }
}
