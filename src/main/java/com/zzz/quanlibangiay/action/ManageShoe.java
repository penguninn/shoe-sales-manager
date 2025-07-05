/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.Brand;
import com.zzz.quanlibangiay.entity.Color;
import com.zzz.quanlibangiay.entity.Material;
import com.zzz.quanlibangiay.entity.Shoe;
import com.zzz.quanlibangiay.entity.ShoeType;
import com.zzz.quanlibangiay.entity.xml.ShoeXML;
import com.zzz.quanlibangiay.utils.FileUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coole
 */
public class ManageShoe {

    private static final String FILE_NAME = "Shoes.xml";
    private List<Shoe> shoes;

    public ManageShoe() {
        ShoeXML shoeXML = (ShoeXML) FileUtils.readXMLFile(FILE_NAME, ShoeXML.class);
        if (shoeXML == null || shoeXML.getShoes() == null) {
            this.shoes = new ArrayList<>();
        } else {
            this.shoes = shoeXML.getShoes();
        }
    }

    public List<Shoe> getAllShoes() {
        return shoes;
    }

    public Shoe getShoeById(int id) {
        for (Shoe s : shoes) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean addShoe(Shoe shoe) {
        if (shoe.getId() == 0) {
            shoe.setId(getNextId());
        } else if (getShoeById(shoe.getId()) != null) {
            return false;
        }
        shoes.add(shoe);
        saveToFile();
        return true;
    }

    public boolean updateShoe(Shoe updated) {
        for (int i = 0; i < shoes.size(); i++) {
            if (shoes.get(i).getId() == updated.getId()) {
                shoes.set(i, updated);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteShoe(int id) {
        Shoe shoe = getShoeById(id);
        if (shoe != null) {
            shoes.remove(shoe);
            saveToFile();
            return true;
        }
        return false;
    }

    public List<Shoe> searchByName(String name) {
        List<Shoe> result = new ArrayList<>();
        for (Shoe s : shoes) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Shoe> searchByBrandId(int brandId) {
        List<Shoe> result = new ArrayList<>();
        for (Shoe s : shoes) {
            if (s.getBrand().getId() == brandId) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Shoe> searchByPrice(double min, double max) {
        List<Shoe> result = new ArrayList<>();
        for (Shoe s : shoes) {
            if (s.getPrice() >= min && s.getPrice() <= max) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Shoe> searchBySize(int size) {
        List<Shoe> result = new ArrayList<>();
        for (Shoe s : shoes) {
            if (s.getSize() == size) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Shoe> searchByColor(Color color) {
        List<Shoe> result = new ArrayList<>();
        for (Shoe s : shoes) {
            if (s.getColor().getId() == color.getId()) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Shoe> searchByMaterial(Material material) {
        List<Shoe> result = new ArrayList<>();
        for (Shoe s : shoes) {
            if (s.getMaterial().getId() == material.getId()) {
                result.add(s);
            }
        }
        return result;
    }

    public List<Shoe> searchByType(ShoeType type) {
        List<Shoe> result = new ArrayList<>();
        for (Shoe s : shoes) {
            if (s.getType().getId() == type.getId()) {
                result.add(s);
            }
        }
        return result;
    }

    public boolean updateQuantity(int id, int newQty) {
        Shoe s = getShoeById(id);
        if (s != null) {
            s.setQuantity(newQty);
            saveToFile();
            return true;
        }
        return false;
    }

    private int getNextId() {
        int max = 0;
        for (Shoe s : shoes) {
            if (s.getId() > max) {
                max = s.getId();
            }
        }
        return max + 1;
    }

    private void saveToFile() {
        ShoeXML wrapper = new ShoeXML();
        wrapper.setShoes(shoes);
        FileUtils.writeXMLtoFile(FILE_NAME, wrapper);
    }
}
