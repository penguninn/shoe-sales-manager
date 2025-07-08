package com.zzz.quanlibangiay.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Refactored Shoe entity using foreign key IDs instead of embedded objects
 */
@XmlRootElement(name = "Shoe")
@XmlAccessorType(XmlAccessType.FIELD)
public class Shoe extends AbstractEntity {

    private String name;
    private int brandId;
    private int typeId;
    private int colorId;
    private int materialId;
    private int sizeId;
    private int quantity;
    private double price;
    private double importPrice;
    private String image;

    public Shoe() {
    }

    public Shoe(int id, Date createdDate, String description,
                String name, int brandId, int typeId, int colorId,
                int materialId, int sizeId, int quantity,
                double price, double importPrice, String image) {
        super(id, createdDate, description);
        this.name = name;
        this.brandId = brandId;
        this.typeId = typeId;
        this.colorId = colorId;
        this.materialId = materialId;
        this.sizeId = sizeId;
        this.quantity = quantity;
        this.price = price;
        this.importPrice = importPrice;
        this.image = image;
    }

    // Getters and setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getBrandId() { return brandId; }
    public void setBrandId(int brandId) { this.brandId = brandId; }

    public int getTypeId() { return typeId; }
    public void setTypeId(int typeId) { this.typeId = typeId; }

    public int getColorId() { return colorId; }
    public void setColorId(int colorId) { this.colorId = colorId; }

    public int getMaterialId() { return materialId; }
    public void setMaterialId(int materialId) { this.materialId = materialId; }

    public int getSizeId() { return sizeId; }
    public void setSizeId(int sizeId) { this.sizeId = sizeId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getImportPrice() { return importPrice; }
    public void setImportPrice(double importPrice) { this.importPrice = importPrice; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
