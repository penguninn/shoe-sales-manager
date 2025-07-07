package com.zzz.quanlibangiay.component.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListMenu<E extends Object> extends JList<E> {

    private final DefaultListModel model;
    private int selectedIndex = -1;
    private int itemDisable = -1;

    public int getItemDisable() {
        return itemDisable;
    }

    public void setItemDisable(int itemDisable) {
        this.itemDisable = itemDisable;
        repaint();
    }

    private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
    }

    public ListMenu() {
        model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int index = locationToIndex(e.getPoint());
                    if (index == itemDisable) {
                        return;
                    }
                    Object o = model.getElementAt(index);
                    if (o instanceof Model_Menu menu) {
                        if (menu.getType() == Model_Menu.MenuType.MENU) {
                            selectedIndex = index;
                            if (event != null) {
                                event.selectedIndex(index);
                            }
                        }
                    } else {
                        selectedIndex = index;
                    }
                    repaint();
                }
            }

        });
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object o, int index, boolean selected, boolean focus) {
                Model_Menu data;
                if (o instanceof Model_Menu) {
                    data = (Model_Menu) o;
                } else {
                    data = new Model_Menu("", o + "", Model_Menu.MenuType.EMPTY);
                }

                MenuItem item = null;
                if (index == itemDisable) {
                    item = new MenuItem(data, true);
                } else {
                    item = new MenuItem(data, false);
                }
                item.setSelected(selectedIndex == index);
                return item;
            }
        };
    }

    public void addItem(Model_Menu data) {
        model.addElement(data);
    }

}
