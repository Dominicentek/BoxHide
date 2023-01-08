package com.boxhide;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Map;

// https://stackoverflow.com/a/71990694/13880720 - Edited

public class ResizableUndecoratedJFrame extends JFrame {
    private Map<Boolean, String> mousePoint;
    private String direction;
    private int px;
    private int py;
    public static final int RANGE = 10;
    public ResizableUndecoratedJFrame(String name) {
        super(name);
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(100, 100);
        getContentPane().setPreferredSize(new Dimension(100, 100));
        pack();
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                px = e.getX();
                py = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                mousePoint = new HashMap<>();
                mousePoint.put(e.getY() < RANGE, "N");
                mousePoint.put(e.getX() > (getWidth() - RANGE), "E");
                mousePoint.put(e.getY() > (getHeight() - RANGE), "S");
                mousePoint.put(e.getX() < RANGE, "W");
                mousePoint.put(e.getY() < RANGE && e.getX() > (getWidth() - RANGE), "NE");
                mousePoint.put(e.getY() > (getHeight() - RANGE) && e.getX() > (getWidth() - RANGE), "SE");
                mousePoint.put(e.getY() > (getHeight() - RANGE) && e.getX() <= RANGE, "SW");
                mousePoint.put(e.getY() < RANGE && e.getX() < RANGE, "NW");
                for (Map.Entry<Boolean, String> item : mousePoint.entrySet()) {
                    if (item.getKey()) {
                        direction = item.getValue();
                        switch (item.getValue()) {
                            case "N":
                                setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                                break;
                            case "E":
                                setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                                break;
                            case "S":
                                setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                                break;
                            case "W":
                                setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                                break;
                            case "NE":
                                setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                                break;
                            case "SE":
                                setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                                break;
                            case "SW":
                                setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                                break;
                            case "NW":
                                setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                                break;
                        }
                    }
                    else {
                        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    }
                }
            }
            public void mouseDragged(MouseEvent e) {
                if (!getCursor().equals(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR))) {
                    switch (direction) {
                        case "N":
                            if (e.getYOnScreen() > getY()) setBounds(getX(), e.getYOnScreen(), getWidth(), getHeight() - (e.getYOnScreen() - getY()));
                            else setBounds(getX(), e.getYOnScreen(), getWidth(), getHeight() + (getY() - e.getYOnScreen()));
                            break;
                        case "E":
                            setBounds(getX(), getY(), e.getX(), getHeight());
                            break;
                        case "S":
                            setBounds(getX(), getY(), getWidth(), e.getY());
                            break;
                        case "W":
                            if (e.getXOnScreen() > getX()) setBounds(e.getXOnScreen(), getY(), getWidth() - (e.getXOnScreen() - getX()), getHeight());
                            else setBounds(e.getXOnScreen(), getY(), getWidth() + (getX() - e.getXOnScreen()), getHeight());
                            break;
                        case "NE":
                            setBounds(getX(), getY(), e.getX(), getHeight());
                            if (e.getYOnScreen() > getY()) setBounds(getX(), e.getYOnScreen(), getWidth(), getHeight() - (e.getYOnScreen() - getY()));
                            else setBounds(getX(), e.getYOnScreen(), getWidth(), getHeight() + (getY() - e.getYOnScreen()));
                            break;
                        case "SE":
                            setBounds(getX(), getY(), e.getX(), e.getY());
                            break;
                        case "SW":
                            setBounds(getX(), getY(), getWidth(), e.getY());
                            if (e.getXOnScreen() > getX()) setBounds(e.getXOnScreen(), getY(), getWidth() - (e.getXOnScreen() - getX()), getHeight());
                            else setBounds(e.getXOnScreen(), getY(), getWidth() + (getX() - e.getXOnScreen()), getHeight());
                            break;
                        case "NW":
                            if (e.getYOnScreen() > getY()) setBounds(getX(), e.getYOnScreen(), getWidth(), getHeight() - (e.getYOnScreen() - getY()));
                            else setBounds(getX(), e.getYOnScreen(), getWidth(), getHeight() + (getY() - e.getYOnScreen()));
                            if (e.getXOnScreen() > getX()) setBounds(e.getXOnScreen(), getY(), getWidth() - (e.getXOnScreen() - getX()), getHeight());
                            else setBounds(e.getXOnScreen(), getY(), getWidth() + (getX() - e.getXOnScreen()), getHeight());
                            break;
                    }
                }
                else setLocation(e.getXOnScreen() - px, e.getYOnScreen() - py);
            }
        });
    }
}