package com.zzz.quanlibangiay.component.table_custom;

import com.zzz.quanlibangiay.component.scrollbar_custom.ScrollbarCustom;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class TableCustom {

    public static void apply(JScrollPane scroll, TableType type) {
        JTable table = (JTable) scroll.getViewport().getComponent(0);
        table.setSelectionBackground(new Color(123, 207, 255));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setDefaultRenderer(new TableHeaderCustomCellRender(table));
        table.setRowHeight(30);
        HoverIndex hoverRow = new HoverIndex();
        TableCellRenderer cellRender;
        if (type == TableType.DEFAULT) {
            cellRender = new TableCustomCellRender(hoverRow);
        } else {
            cellRender = new TextAreaCellRenderer(hoverRow);
        }
        table.setDefaultRenderer(Object.class, cellRender);
        table.setDefaultRenderer(Boolean.class, new BooleanCellRenderer(hoverRow));
        table.setShowVerticalLines(true);
        table.setGridColor(new Color(220, 220, 220));
        table.setForeground(new Color(51, 51, 51));
        table.setSelectionForeground(new Color(51, 51, 51));
        scroll.setBorder(new LineBorder(new Color(220, 220, 220)));
        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics grphcs) {
                super.paint(grphcs);
                grphcs.setColor(new Color(220, 220, 220));
                grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
                grphcs.dispose();
            }
        };
        panel.setBackground(new Color(250, 250, 250));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollbarCustom());
        scroll.setHorizontalScrollBar(new ScrollbarCustom());
        table.getTableHeader().setBackground(new Color(250, 250, 250));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                hoverRow.setIndex(-1);
                table.repaint();
            }

        });
        table.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != hoverRow.getIndex()) {
                    hoverRow.setIndex(row);
                    table.repaint();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row != hoverRow.getIndex()) {
                    hoverRow.setIndex(row);
                    table.repaint();
                }
            }
        });
    }

    public enum TableType {
        MULTI_LINE, DEFAULT
    }
}
