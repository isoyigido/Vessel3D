package gui;

import main.ProjectPanel;

import java.awt.*;

public class InfoGUI {
    public static void render(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(new Font("Arial", Font.PLAIN, 24));
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        String focal_length_str = "Focal Length: " + ProjectPanel.camera.focal_length;
        graphics2D.drawString(focal_length_str, 0, fontMetrics.getAscent());
        String direction_str = "forward: " + ProjectPanel.camera.forward +
                ", backward: " + ProjectPanel.camera.backward +
                ", right: " + ProjectPanel.camera.right +
                ", left: " + ProjectPanel.camera.left +
                ", up: " + ProjectPanel.camera.up +
                ", down: " + ProjectPanel.camera.down;
        graphics2D.drawString(direction_str, 0, fontMetrics.getAscent() + fontMetrics.getAscent());
    }
}
