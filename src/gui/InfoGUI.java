package gui;

import camera.Camera;
import main.Constants;
import main.Project;
import main.ProjectPanel;

import java.awt.*;

public class InfoGUI {
    ProjectPanel projectPanel;

    public InfoGUI(ProjectPanel projectPanel_input) {
        projectPanel = projectPanel_input;
    }

    public void render(Camera camera, Graphics2D graphics2D) {
        if(camera.gui_enabled) {
            graphics2D.setColor(Color.BLACK);
            graphics2D.setFont(new Font("Arial", Font.PLAIN, 24));
            FontMetrics fontMetrics = graphics2D.getFontMetrics();
            String coordinate_str = "x: " + (int) camera.x + ", y: " + (int) camera.y + ", z: " + (int) camera.z;
            graphics2D.drawString(coordinate_str, 0, fontMetrics.getAscent());
            String rotation_str = "yaw: " + (int) camera.yaw + ", pitch: " + (int) camera.pitch + ", roll: " + (int) camera.roll;
            graphics2D.drawString(rotation_str, 0, fontMetrics.getHeight() + fontMetrics.getAscent());
            String focal_length_str = "Focal Length: " + camera.focal_length;
            graphics2D.drawString(focal_length_str, 0, 2*fontMetrics.getHeight() + fontMetrics.getAscent());

            String fps_ups_str = "FPS: " + projectPanel.FPS + " | UPS: " + projectPanel.UPS;
            graphics2D.drawString(fps_ups_str, Constants.screenWidth - fontMetrics.stringWidth(fps_ups_str), fontMetrics.getAscent());
        }
    }
}
