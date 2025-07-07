/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.Material;
import com.zzz.quanlibangiay.entity.xml.MaterialXML;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coole
 */
public class ManageMaterial {
    
private static final String MATERIAL_FILE = "Materials.xml";
    private List<Material> materialList;

    public ManageMaterial() {
        MaterialXML materialXML = (MaterialXML) FileUtils.readXMLFile(MATERIAL_FILE, MaterialXML.class);
        if (materialXML == null || materialXML.getMaterials() == null) {
            materialList = new ArrayList<>();
        } else {
            materialList = materialXML.getMaterials();
        }
    }

    public List<Material> getAllMaterials() {
        return materialList;
    }

    public Material getMaterialById(int id) {
        for (Material m : materialList) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public boolean addMaterial(Material material) {
        if (material.getId() == 0) {
            material.setId(getNextId());
        } else if (getMaterialById(material.getId()) != null) {
            return false;
        }
        materialList.add(material);
        saveToFile();
        return true;
    }

    public boolean updateMaterial(Material updated) {
        for (int i = 0; i < materialList.size(); i++) {
            if (materialList.get(i).getId() == updated.getId()) {
                materialList.set(i, updated);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteMaterial(int id) {
        Material m = getMaterialById(id);
        if (m != null) {
            materialList.remove(m);
            saveToFile();
            return true;
        }
        return false;
    }

    public List<Material> searchMaterialByName(String keyword) {
        List<Material> result = new ArrayList<>();
        for (Material m : materialList) {
            if (m.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(m);
            }
        }
        return result;
    }

    private int getNextId() {
        int max = 0;
        for (Material m : materialList) {
            if (m.getId() > max) {
                max = m.getId();
            }
        }
        return max + 1;
    }

    private void saveToFile() {
        MaterialXML materialXML = new MaterialXML();
        materialXML.setMaterials(materialList);
        FileUtils.writeXMLtoFile(MATERIAL_FILE, materialXML);
    }
}
