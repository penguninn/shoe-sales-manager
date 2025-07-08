package com.zzz.quanlibangiay.component.panel_custom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage img;

    public ImagePanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
    }

    public void setImage(File file) {
        try {
            img = ImageIO.read(file);
        } catch (IOException ex) {
            img = null;
            ex.printStackTrace();
        }
        repaint();
    }

    public void clearImage() {
        img = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            int w = getWidth(), h = getHeight();
            g.drawImage(img.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, w, h, null);
        } else {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}

