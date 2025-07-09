/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.entity.xml;

import com.zzz.quanlibangiay.entity.Type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 * @author coole
 */

@XmlRootElement(name = "Types")
@XmlAccessorType(XmlAccessType.FIELD)
public class TypeXML {

    @XmlElement(name = "Type")
    private List<Type> types;

    public List<Type> getShoeTypes() {
        return types;
    }

    public void setShoeTypes(List<Type> types) {
        this.types = types;
    }
}
