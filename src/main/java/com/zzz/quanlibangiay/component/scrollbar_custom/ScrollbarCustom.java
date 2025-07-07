
package com.zzz.quanlibangiay.component.scrollbar_custom;

import javax.swing.*;
import java.awt.*;

public class ScrollbarCustom extends JScrollBar {
    public ScrollbarCustom() {
        setUI(new ModernScrollbarUI());
        setPreferredSize(new Dimension(6, 6));
        setForeground(new Color(48, 144, 216));
    }
}
