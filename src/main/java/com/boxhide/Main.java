package com.boxhide;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        ResizableUndecoratedJFrame frame = new ResizableUndecoratedJFrame("Box Hide");
        frame.add(new JPanel() {
            public void paint(Graphics g) {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
            }
        });
        frame.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB));
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
}
