package com.zzz.quanlibangiay.entity;

public class ShoeSearchCriteria {
    private String name;
    private Brand brand;          
    private ShoeType type;        
    private Color color;
    private Material material;    
    private Size size;            
    private Double priceFrom;     
    private Double priceTo;

    public ShoeSearchCriteria() {
    }

    public ShoeSearchCriteria(String name, Brand brand, ShoeType type, Color color, Material material, Size size, Double priceFrom, Double priceTo) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.material = material;
        this.size = size;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ShoeType getType() {
        return type;
    }

    public void setType(ShoeType type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }
}
