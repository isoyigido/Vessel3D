package objects;

import camera.Camera;
import components.Edge;
import components.Vertex;

import java.awt.*;
import java.util.ArrayList;

public class Pyramid {
    public double x, y, z;
    public int size;

    ArrayList<Vertex> vertex_table;
    ArrayList<Edge> edge_table;

    public Pyramid(double x_input, double y_input, double z_input, int size_input) {
        x = x_input;
        y = y_input;
        z = z_input;
        size = size_input;

        vertex_table = new ArrayList<>();

        Vertex A = new Vertex(x + size, y, z);
        Vertex B = new Vertex(x + size, y, z + size);
        Vertex C = new Vertex(x, y, z);
        Vertex D = new Vertex(x, y, z + size);
        Vertex E = new Vertex(x + size*0.5f, y+size, z + size*0.5f);

        vertex_table.add(A);
        vertex_table.add(B);
        vertex_table.add(C);
        vertex_table.add(D);
        vertex_table.add(E);

        edge_table = new ArrayList<>();

        Edge AB = new Edge(A, B);
        Edge AC = new Edge(A, C);
        Edge BD = new Edge(B, D);
        Edge CD = new Edge(C, D);
        Edge AE = new Edge(A, E);
        Edge BE = new Edge(B, E);
        Edge CE = new Edge(C, E);
        Edge DE = new Edge(D, E);

        edge_table.add(AB);
        edge_table.add(AC);
        edge_table.add(BD);
        edge_table.add(CD);
        edge_table.add(AE);
        edge_table.add(BE);
        edge_table.add(CE);
        edge_table.add(DE);
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
