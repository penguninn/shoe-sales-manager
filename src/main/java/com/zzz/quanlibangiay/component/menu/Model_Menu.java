package com.zzz.quanlibangiay.component.menu;

import javax.swing.*;

public class Model_Menu {

    private String icon;
    private String name;
    private MenuType type;

    public Model_Menu() {
    }

    public Model_Menu(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Icon toIcon() {
        try {
            return new ImageIcon(getClass().getResource("/icon/" + icon + ".png"));
        } catch (Exception e) {
            System.out.println("Icon not found: " + e.getMessage());
            return null;
        }
    }

    public enum MenuType {
        TITLE, MENU, EMPTY
    }

}
