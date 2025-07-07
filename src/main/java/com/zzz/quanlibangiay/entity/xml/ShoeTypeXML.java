/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.entity.xml;

import com.zzz.quanlibangiay.entity.ShoeType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 * @author coole
 */

@XmlRootElement(name = "ShoeTypes")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShoeTypeXML {

    @XmlElement(name = "ShoeType")
    private List<ShoeType> shoeTypes;

    public List<ShoeType> getShoeTypes() {
        return shoeTypes;
    }

    public void setShoeTypes(List<ShoeType> shoeTypes) {
        this.shoeTypes = shoeTypes;
    }
}
