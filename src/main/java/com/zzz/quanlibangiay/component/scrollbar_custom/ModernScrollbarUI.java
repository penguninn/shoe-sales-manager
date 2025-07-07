package com.zzz.quanlibangiay.component.scrollbar_custom;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ModernScrollbarUI extends BasicScrollBarUI {

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        scrollbar.setUnitIncrement(20);
    }
    
    @Override
    protected Dimension getMaximumThumbSize() {
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            int extent = scrollbar.getModel().getExtent();
            int range = scrollbar.getModel().getMaximum() - scrollbar.getModel().getMinimum();
            if(range == 0) {
                range = 1;
            }
            int height = (extent * scrollbar.getHeight()) / range;
            return new Dimension(0, Math.max(height, 10)); // Ensure a minimum size
        } else {
            int extent = scrollbar.getModel().getExtent();
            int range = scrollbar.getModel().getMaximum() - scrollbar.getModel().getMinimum();
            if(range == 0) {
                range = 1;
            }
            int width = (extent * scrollbar.getWidth()) / range;
            return new Dimension(Math.max(width, 10), 0); // Ensure a minimum size
        }
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        return getMaximumThumbSize();
    }

    @Override
    protected JButton createIncreaseButton(int i) {
        return new ScrollBarButton();
    }

    @Override
    protected JButton createDecreaseButton(int i) {
        return new ScrollBarButton();
    }

    @Override
    protected void paintTrack(Graphics grphcs, JComponent jc, Rectangle rctngl) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int orientation = scrollbar.getOrientation();
        int size;
        int x;
        int y;
        int width;
        int height;
        if (orientation == JScrollBar.VERTICAL) {
            size = rctngl.width / 2;
            x = rctngl.x + ((rctngl.width - size) / 2);
            y = rctngl.y;
            width = size;
            height = rctngl.height;
        } else {
            size = rctngl.height / 2;
            y = rctngl.y + ((rctngl.height - size) / 2);
            x = 0;
            width = rctngl.width;
            height = size;
        }
        g2.setColor(new Color(240, 240, 240));
        g2.fillRect(x, y, width, height);
    }

    @Override
    protected void paintThumb(Graphics grphcs, JComponent jc, Rectangle rctngl) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int x = rctngl.x;
        int y = rctngl.y;
        int width = rctngl.width;
        int height = rctngl.height;
        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            y += 8;
            height -= 16;
        } else {
            x += 8;
            width -= 16;
        }
        g2.setColor(scrollbar.getForeground());
        g2.fillRoundRect(x, y, width, height, 10, 10);
    }

    private class ScrollBarButton extends JButton {
        public ScrollBarButton() {
            setBorder(BorderFactory.createEmptyBorder());
        }

        @Override
        public void paint(Graphics grphcs) {
        }
    }
}
