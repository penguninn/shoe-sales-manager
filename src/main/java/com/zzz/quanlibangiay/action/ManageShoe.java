package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.Shoe;
import com.zzz.quanlibangiay.entity.ShoeSearchCriteria;
import com.zzz.quanlibangiay.entity.xml.ShoeXML;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ManageShoe {
    private static final String FILE_NAME = "Shoes.xml";
    private List<Shoe> shoes;

    public ManageShoe() {
        loadShoes();
    }

    private void loadShoes() {
        ShoeXML shoeXML = (ShoeXML) FileUtils.readXMLFile(FILE_NAME, ShoeXML.class);
        this.shoes = (shoeXML == null || shoeXML.getShoes() == null)
                ? new ArrayList<>()
                : shoeXML.getShoes();
    }

    public List<Shoe> getAllShoes() {
        return new ArrayList<>(shoes);
    }

    public Shoe getShoeById(int id) {
        return shoes.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Shoe> searchAndSort(ShoeSearchCriteria crit, String sortBy) {
        List<Shoe> filtered = searchShoes(crit);
        if (sortBy != null && !sortBy.isEmpty()) {
            Comparator<Shoe> cmp = getComparator(sortBy);
            if (cmp != null) filtered.sort(cmp);
        }
        return filtered;
    }

    private Comparator<Shoe> getComparator(String sortBy) {
        switch (sortBy) {
            case "ID_ASC":
                return Comparator.comparingInt(Shoe::getId);
            case "ID_DESC":
                return Comparator.comparingInt(Shoe::getId).reversed();
            case "NAME_ASC":
                return Comparator.comparing(Shoe::getName, String.CASE_INSENSITIVE_ORDER);
            case "NAME_DESC":
                return Comparator.comparing(Shoe::getName, String.CASE_INSENSITIVE_ORDER).reversed();
            case "PRICE_ASC":
                return Comparator.comparingDouble(Shoe::getPrice);
            case "PRICE_DESC":
                return Comparator.comparingDouble(Shoe::getPrice).reversed();
            case "IMPORT_PRICE_ASC":
                return Comparator.comparingDouble(Shoe::getImportPrice);
            case "IMPORT_PRICE_DESC":
                return Comparator.comparingDouble(Shoe::getImportPrice).reversed();
            case "QUANTITY_ASC":
                return Comparator.comparingInt(Shoe::getQuantity);
            case "QUANTITY_DESC":
                return Comparator.comparingInt(Shoe::getQuantity).reversed();
            default:
                return null;
        }
    }

    public List<Shoe> searchShoes(ShoeSearchCriteria c) {
        return shoes.stream()
                .filter(s -> matchesName(s, c.getName()))
                .filter(s -> c.getBrand() == null || matchesId(c.getBrand().getId(), s.getBrandId()))
                .filter(s -> c.getType() == null || matchesId(c.getType().getId(), s.getTypeId()))
                .filter(s -> c.getColor() == null || matchesId(c.getColor().getId(), s.getColorId()))
                .filter(s -> c.getMaterial() == null || matchesId(c.getMaterial().getId(), s.getMaterialId()))
                .filter(s -> c.getSize() == null || matchesId(c.getSize().getId(), s.getSizeId()))
                .filter(s -> matchesRange(s.getPrice(), c.getPriceFrom(), c.getPriceTo()))
                .collect(Collectors.toList());
    }


    public boolean isBrandInUse(int brandId) {
        return shoes.stream().anyMatch(shoe -> shoe.getBrandId() == brandId);
    }

    public boolean isTypeInUse(int typeId) {
        return shoes.stream().anyMatch(shoe -> shoe.getTypeId() == typeId);
    }

    public boolean isMaterialInUse(int materialId) {
        return shoes.stream().anyMatch(shoe -> shoe.getMaterialId() == materialId);
    }

    public boolean isColorInUse(int colorId) {
        return shoes.stream().anyMatch(shoe -> shoe.getColorId() == colorId);
    }

    public boolean isSizeInUse(int sizeId) {
        return shoes.stream().anyMatch(shoe -> shoe.getSizeId() == sizeId);
    }

    private boolean matchesName(Shoe s, String name) {
        return name == null || s.getName().toLowerCase().contains(name.toLowerCase());
    }

    private boolean matchesId(Integer critId, int actualId) {
        return critId == null || critId == 0 || critId == actualId;
    }

    private boolean matchesRange(double value, Double min, Double max) {
        return (min == null || value >= min)
                && (max == null || value <= max);
    }

    public boolean addShoe(Shoe shoe) {
        shoe.setId(nextId());
        shoes.add(shoe);
        return save();
    }

    public boolean updateShoe(Shoe updated) {
        for (int i = 0; i < shoes.size(); i++) {
            if (shoes.get(i).getId() == updated.getId()) {
                shoes.set(i, updated);
                return save();
            }
        }
        return false;
    }

    public boolean deleteShoe(int id) {
        boolean removed = shoes.removeIf(s -> s.getId() == id);
        return removed && save();
    }

    public boolean updateQuantity(int id, int newQty) {
        Shoe s = getShoeById(id);
        if (s != null) {
            s.setQuantity(newQty);
            return save();
        }
        return false;
    }

    private int nextId() {
        return shoes.stream()
                .mapToInt(Shoe::getId)
                .max()
                .orElse(0) + 1;
    }

    private boolean save() {
        try {
            ShoeXML wrapper = new ShoeXML();
            wrapper.setShoes(shoes);
            FileUtils.writeXMLtoFile(FILE_NAME, wrapper);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
