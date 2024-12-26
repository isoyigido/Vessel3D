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
        graphics2D.setColor(Color.BLACK);

        double x_relative = x - camera.x;
        double y_relative = y - camera.y;
        double z_relative = z - camera.z;

        int x_projected = (int) ((float) (x_relative * camera.focal_length) / (float) (camera.focal_length + z_relative));
        int y_projected = (int) ((float) (y_relative * camera.focal_length) / (float) (camera.focal_length + z_relative));

        if (z_relative >= 0) {
            graphics2D.drawRect(x_projected + Constants.xOffset, Constants.yOffset - y_projected, 0, 0);
        }
    }
}
