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

        vertex_table = new ArrayList<>();

        Vertex A = new Vertex(x + size, y, z);
        Vertex B = new Vertex(x + size, y + size, z);
        Vertex C = new Vertex(x, y, z);
        Vertex D = new Vertex(x, y + size, z);
        Vertex E = new Vertex(x + size, y, z + size);
        Vertex F = new Vertex(x + size, y + size, z + size);
        Vertex G = new Vertex(x, y, z + size);
        Vertex H = new Vertex(x, y + size, z + size);

        vertex_table.add(A);
        vertex_table.add(B);
        vertex_table.add(C);
        vertex_table.add(D);
        vertex_table.add(E);
        vertex_table.add(F);
        vertex_table.add(G);
        vertex_table.add(H);

        edge_table = new ArrayList<>();

        Edge AB = new Edge(A, B);
        Edge AC = new Edge(A, C);
        Edge AD = new Edge(B, D);
        Edge BC = new Edge(C, D);
        Edge AE = new Edge(A, E);
        Edge BF = new Edge(B, F);
        Edge CG = new Edge(C, G);
        Edge DH = new Edge(D, H);
        Edge EF = new Edge(E, F);
        Edge EG = new Edge(E, G);
        Edge FH = new Edge(F, H);
        Edge GH = new Edge(G, H);

        edge_table.add(AB);
        edge_table.add(AC);
        edge_table.add(AD);
        edge_table.add(BC);
        edge_table.add(AE);
        edge_table.add(BF);
        edge_table.add(CG);
        edge_table.add(DH);
        edge_table.add(EF);
        edge_table.add(EG);
        edge_table.add(FH);
        edge_table.add(GH);
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
