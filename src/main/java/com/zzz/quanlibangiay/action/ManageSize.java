package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.Size;
import com.zzz.quanlibangiay.entity.xml.SizeXML;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;


public class ManageSize {

    private static final String SIZE_FILE = "Sizes.xml";
    private List<Size> sizeList;

    public ManageSize() {
        SizeXML sizeXML = (SizeXML) FileUtils.readXMLFile(SIZE_FILE, SizeXML.class);
        if (sizeXML == null || sizeXML.getSizes() == null) {
            sizeList = new ArrayList<>();
        } else {
            sizeList = sizeXML.getSizes();
        }
    }

    public List<Size> getAllSizes() {
        return sizeList;
    }

    public Size getSizeById(int id) {
        for (Size s : sizeList) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean addSize(Size size) {
        size.setId(getNextId());
        size.setCreatedDate(new java.util.Date());
        sizeList.add(size);
        saveToFile();
        return true;
    }

    public boolean updateSize(Size updated) {
        for (int i = 0; i < sizeList.size(); i++) {
            if (sizeList.get(i).getId() == updated.getId()) {
                sizeList.set(i, updated);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteSize(int id) {
        Size size = getSizeById(id);
        if (size != null) {
            sizeList.remove(size);
            saveToFile();
            return true;
        }
        return false;
    }

    public List<Size> searchSizeByName(String keyword) {
        List<Size> result = new ArrayList<>();
        for (Size s : sizeList) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    private int getNextId() {
        int max = 0;
        for (Size s : sizeList) {
            if (s.getId() > max) {
                max = s.getId();
            }
        }
        return max + 1;
    }

    private void saveToFile() {
        SizeXML sizeXML = new SizeXML();
        sizeXML.setSizes(sizeList);
        FileUtils.writeXMLtoFile(SIZE_FILE, sizeXML);
    }
}