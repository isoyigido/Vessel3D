package objects;

import camera.Camera;
import components.Edge;
import components.Vertex;
import main.Constants;
import main.ProjectPanel;

import java.awt.*;
import java.util.ArrayList;

public class Object {
    static ArrayList<Vertex> vertex_table;
    static ArrayList<Edge> edge_table;

    public Object(ArrayList<Vertex> vertex_table_input, ArrayList<Edge> edge_table_input) {
        vertex_table = vertex_table_input;
        edge_table = edge_table_input;
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
