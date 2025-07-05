/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.entity;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author coole
 */
@XmlRootElement(name = "Shoe")
@XmlAccessorType(XmlAccessType.FIELD)
public class Shoe extends AbstractEntity {

    private String name;
    private Brand brand;
    private ShoeType type;
    private Color color;
    private Material material;
    private int size;
    private int quantity;
    private double price;
    private String image;

    public Shoe() {
    }

    public Shoe(String name, Brand brand, ShoeType type, Color color, Material material, int size, int quantity, double price, String image) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.color = color;
        this.material = material;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
