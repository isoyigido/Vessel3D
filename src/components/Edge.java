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

    private Vertex getIntersectionPoint(Vertex behind, Vertex inFront) {
        double t = behind.z / (behind.z - inFront.z);
        double x = behind.x + t * (inFront.x - behind.x);
        double y = behind.y + t * (inFront.y - behind.y);
        return new Vertex(x, y, 0);
    }

    public void render(Camera camera, Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);

        double x_relative1 = vertex1.x - camera.x;
        double y_relative1 = vertex1.y - camera.y;
        double z_relative1 = vertex1.z - camera.z;

        double x_relative2 = vertex2.x - camera.x;
        double y_relative2 = vertex2.y - camera.y;
        double z_relative2 = vertex2.z - camera.z;

        if (z_relative1 >= 0 && z_relative2 >= 0) {
            int x_projected1 = (int) ((x_relative1 * camera.focal_length) / (camera.focal_length + z_relative1));
            int y_projected1 = (int) ((y_relative1 * camera.focal_length) / (camera.focal_length + z_relative1));

            int x_projected2 = (int) ((x_relative2 * camera.focal_length) / (camera.focal_length + z_relative2));
            int y_projected2 = (int) ((y_relative2 * camera.focal_length) / (camera.focal_length + z_relative2));

            graphics2D.drawLine(x_projected1 + Constants.xOffset, Constants.yOffset - y_projected1,
                    x_projected2 + Constants.xOffset, Constants.yOffset - y_projected2);
        }

        else if (z_relative1 >= 0 || z_relative2 >= 0) {
            Vertex intersection;
            if (z_relative1 < 0) {
                intersection = getIntersectionPoint(vertex1, vertex2);
                x_relative1 = intersection.x - camera.x;
                y_relative1 = intersection.y - camera.y;
                z_relative1 = intersection.z - camera.z;
            } else {
                intersection = getIntersectionPoint(vertex2, vertex1);
                x_relative2 = intersection.x - camera.x;
                y_relative2 = intersection.y - camera.y;
                z_relative2 = intersection.z - camera.z;
            }

            int x_projected1 = (int) ((x_relative1 * camera.focal_length) / (camera.focal_length + z_relative1));
            int y_projected1 = (int) ((y_relative1 * camera.focal_length) / (camera.focal_length + z_relative1));

            int x_projected2 = (int) ((x_relative2 * camera.focal_length) / (camera.focal_length + z_relative2));
            int y_projected2 = (int) ((y_relative2 * camera.focal_length) / (camera.focal_length + z_relative2));

            graphics2D.drawLine(x_projected1 + Constants.xOffset, Constants.yOffset - y_projected1,
                    x_projected2 + Constants.xOffset, Constants.yOffset - y_projected2);
        }
    }
}
