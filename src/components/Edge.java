package components;

import camera.Camera;
import main.Constants;

import java.awt.*;

public class Edge {
    Vertex vertex1, vertex2;

    public Edge(Vertex vertex1_input, Vertex vertex2_input) {
        vertex1 = vertex1_input;
        vertex2 = vertex2_input;
    }

    public Edge(double x1, double y1, double z1, double x2, double y2, double z2) {
        vertex1 = new Vertex(x1, y1, z1);
        vertex2 = new Vertex(x2, y2, z2);
    }

    public void render(Camera camera, Graphics2D graphics2D) {
        double z_relative1 = camera.zRelative(vertex1.x, vertex1.y, vertex1.z);
        double z_relative2 = camera.zRelative(vertex2.x, vertex2.y, vertex2.z);

        if (z_relative1 > 0 && z_relative2 > 0) {
            int x_projected1 = camera.projectX(vertex1.x, vertex1.y, vertex1.z);
            int x_projected2 = camera.projectX(vertex2.x, vertex2.y, vertex2.z);

            int y_projected1 = camera.projectY(vertex1.x, vertex1.y, vertex1.z);
            int y_projected2 = camera.projectY(vertex2.x, vertex2.y, vertex2.z);

            graphics2D.drawLine(x_projected1 + Constants.xOffset, Constants.yOffset - y_projected1, x_projected2 + Constants.xOffset, Constants.yOffset - y_projected2);
        }
    }
}
