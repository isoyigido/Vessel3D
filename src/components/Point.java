package components;

import camera.Camera;
import main.Constants;

import java.awt.*;

public class Point {
    public double x, y, z;
    public int radius;

    public Point(double x_input, double y_input, double z_input, int radius_input) {
        x = x_input;
        y = y_input;
        z = z_input;
        radius = radius_input;
    }

    public void render(Camera camera, Graphics2D graphics2D) {
        double z_relative = camera.zRelative(x, y, z);

        if (z_relative >= 0) {
            int x_projected = camera.projectX(x, y, z_relative);
            int y_projected = camera.projectY(x, y, z_relative);

            graphics2D.setColor(Color.BLACK);
            graphics2D.fillOval(x_projected + Constants.xOffset - radius, Constants.yOffset - y_projected - radius, radius, radius);
        }
    }
}