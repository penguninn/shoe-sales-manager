/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.Brand;
import com.zzz.quanlibangiay.entity.xml.BrandXML;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coole
 */
public class ManageBrand {
    
private static final String BRAND_FILE = "Brands.xml";
    private List<Brand> brandList;

    public ManageBrand() {
        BrandXML brandXML = (BrandXML) FileUtils.readXMLFile(BRAND_FILE, BrandXML.class);
        if (brandXML == null || brandXML.getBrands() == null) {
            brandList = new ArrayList<>();
        } else {
            brandList = brandXML.getBrands();
        }
    }

    public List<Brand> getAllBrands() {
        return brandList;
    }

    public Brand getBrandById(int id) {
        for (Brand b : brandList) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    public boolean addBrand(Brand brand) {
        if (brand.getId() == 0) {
            brand.setId(getNextId());
        } else if (getBrandById(brand.getId()) != null) {
            return false;
        }
        brandList.add(brand);
        saveToFile();
        return true;
    }

    public boolean updateBrand(Brand updated) {
        for (int i = 0; i < brandList.size(); i++) {
            if (brandList.get(i).getId() == updated.getId()) {
                brandList.set(i, updated);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteBrand(int id) {
        Brand brand = getBrandById(id);
        if (brand != null) {
            brandList.remove(brand);
            saveToFile();
            return true;
        }
        return false;
    }

    public List<Brand> searchBrandByName(String keyword) {
        List<Brand> result = new ArrayList<>();
        for (Brand b : brandList) {
            if (b.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(b);
            }
        }
        return result;
    }

    private int getNextId() {
        int max = 0;
        for (Brand b : brandList) {
            if (b.getId() > max) {
                max = b.getId();
            }
        }
        return max + 1;
    }

    private void saveToFile() {
        BrandXML brandXML = new BrandXML();
        brandXML.setBrands(brandList);
        FileUtils.writeXMLtoFile(BRAND_FILE, brandXML);
    }
}
