package gui;

import camera.Camera;
import main.ProjectPanel;

import java.awt.*;

public class InfoGUI {
    public static void render(Camera camera, Graphics2D graphics2D) {
        if(camera.gui_enabled) {
            graphics2D.setColor(Color.BLACK);
            graphics2D.setFont(new Font("Arial", Font.PLAIN, 24));
            FontMetrics fontMetrics = graphics2D.getFontMetrics();
            String coordinate_str = "x: " + camera.x + ", y: " + camera.y + ", z: " + camera.z;
            graphics2D.drawString(coordinate_str, 0, fontMetrics.getAscent());
            String rotation_str = "yaw: " + camera.yaw + ", pitch: " + camera.pitch + ", roll: " + camera.roll;
            graphics2D.drawString(rotation_str, 0, fontMetrics.getHeight() + fontMetrics.getAscent());
            String focal_length_str = "Focal Length: " + camera.focal_length;
            graphics2D.drawString(focal_length_str, 0, 2 * fontMetrics.getHeight() + fontMetrics.getAscent());
        }
    }
}
