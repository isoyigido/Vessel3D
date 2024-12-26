package objects;

import camera.Camera;
import components.Edge;
import components.Vertex;

import java.awt.*;
import java.util.ArrayList;

public class Cube {
    public double x, y, z;
    public int size;

    ArrayList<Vertex> vertex_table;
    ArrayList<Edge> edge_table;

    public Cube(double x_input, double y_input, double z_input, int size_input) {
        x = x_input;
        y = y_input;
        z = z_input;
        size = size_input;

        RectangularPrism rectangularPrism = new RectangularPrism(x, y, z, size, size, size);
        vertex_table = rectangularPrism.vertex_table;
        edge_table = rectangularPrism.edge_table;
    }

    public void renderVertices(Camera camera, Graphics2D graphics2D) {
        for (Vertex vertex : vertex_table) {
            vertex.render(camera, graphics2D);
        }
    }

    public void renderEdges(Camera camera, Graphics2D graphics2D) {
        for (Edge edge : edge_table) {
            edge.render(camera, graphics2D);
        }
    }
}
