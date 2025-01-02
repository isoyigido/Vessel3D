package objects;

import camera.Camera;
import components.Edge;
import data.Converter;
import main.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Liver {
    double x, y, z;

    Object vessel;
    Object liver;

    public boolean vessel_enabled;
    public boolean liver_enabled;

    public Liver(double x_input, double y_input, double z_input, int[][][] int3, BufferedImage[] liver_input, int scale, int y_upscale) {
        x = x_input;
        y = y_input;
        z = z_input;

        vessel = new Object(x_input, y_input, z_input, int3, scale, y_upscale);
        liver = new Object(x_input, y_input, z_input, Converter.removeInnerUnits(Converter.convertToInt3(liver_input)), scale, y_upscale);

        vessel_enabled = true;
        liver_enabled = true;
    }

    public void render(Camera camera, Graphics2D graphics2D) {
        if (vessel_enabled) {
            graphics2D.setColor(Constants.VESSEL_COLOR);
            for (Edge edge : vessel.edge_table) {
                edge.render(camera, graphics2D);
            }
        }
        if (liver_enabled) {
            graphics2D.setColor(Constants.LIVER_COLOR);
            for (Edge edge : liver.edge_table) {
                edge.render(camera, graphics2D);
            }
        }
    }
}