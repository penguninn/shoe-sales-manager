package com.zzz.quanlibangiay.entity.xml;

import com.zzz.quanlibangiay.entity.Size;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Sizes")
@XmlAccessorType(XmlAccessType.FIELD)
public class SizeXML {

    @XmlElement(name="Size")
    private List<Size> sizes;

    public List<Size> getSizes() {
        return sizes;
    }

    public void setSizes(List<Size> sizes) {
        this.sizes = sizes;
    }
}
