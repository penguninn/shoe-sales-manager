/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.Color;
import com.zzz.quanlibangiay.entity.xml.ColorXML;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coole
 */
public class ManageColor {
    
private static final String COLOR_FILE = "Colors.xml";
    private List<Color> colorList;

    public ManageColor() {
        ColorXML colorXML = (ColorXML) FileUtils.readXMLFile(COLOR_FILE, ColorXML.class);
        if (colorXML == null || colorXML.getColors() == null) {
            colorList = new ArrayList<>();
        } else {
            colorList = colorXML.getColors();
        }
    }

    public List<Color> getAllColors() {
        return colorList;
    }

    public Color getColorById(int id) {
        for (Color c : colorList) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean addColor(Color color) {
        if (color.getId() == 0) {
            color.setId(getNextId());
        } else if (getColorById(color.getId()) != null) {
            return false;
        }
        colorList.add(color);
        saveToFile();
        return true;
    }

    public boolean updateColor(Color updated) {
        for (int i = 0; i < colorList.size(); i++) {
            if (colorList.get(i).getId() == updated.getId()) {
                colorList.set(i, updated);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteColor(int id) {
        Color color = getColorById(id);
        if (color != null) {
            colorList.remove(color);
            saveToFile();
            return true;
        }
        return false;
    }

    public List<Color> searchColorByName(String keyword) {
        List<Color> result = new ArrayList<>();
        for (Color c : colorList) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(c);
            }
        }
        return result;
    }

    private int getNextId() {
        int max = 0;
        for (Color c : colorList) {
            if (c.getId() > max) {
                max = c.getId();
            }
        }
        return max + 1;
    }

    private void saveToFile() {
        ColorXML colorXML = new ColorXML();
        colorXML.setColors(colorList);
        FileUtils.writeXMLtoFile(COLOR_FILE, colorXML);
    }
}
