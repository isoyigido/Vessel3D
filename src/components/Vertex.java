package components;

import camera.Camera;
import main.Constants;

import java.awt.*;
import java.util.ArrayList;

public class Vertex {
    public double x, y, z;

    public Vertex(double x_input, double y_input, double z_input){
        x = x_input;
        y = y_input;
        z = z_input;
    }

    public void render(Camera camera, Graphics2D graphics2D) {
        double z_relative = camera.zRelative(x, y, z);

        if (z_relative >= 0) {
            int x_projected = camera.projectX(x, y, z);
            int y_projected = camera.projectY(x, y, z);

            graphics2D.fillRect(x_projected + Constants.xOffset, Constants.yOffset - y_projected, 1, 1);
        }
    }
}
