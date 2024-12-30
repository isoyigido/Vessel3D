package objects;

import camera.Camera;
import components.Edge;
import components.Vertex;

import java.awt.*;
import java.util.ArrayList;

public class Vessel3D {
    double x, y, z;

    ArrayList<Edge> edge_table;

    public Vessel3D(double x_input, double y_input, double z_input, int[][][] int3, int scale, int y_upscale) {
        x = x_input;
        y = y_input;
        z = z_input;

        edge_table = new ArrayList<>();

        int[][][] int3_extended = new int[int3.length + 2][int3[0].length + 2][int3[0][0].length + 2];
        for (int z_index = 0; z_index < int3_extended.length; z_index++) {
            for (int y_index = 0; y_index < int3_extended[0].length; y_index++) {
                for (int x_index = 0; x_index < int3_extended[0][0].length; x_index++) {
                    int3_extended[z_index][y_index][x_index] = 0;
                }
            }
        }
        for (int z_index = 0; z_index < int3.length; z_index++) {
            for (int y_index = 0; y_index < int3[0].length; y_index++) {
                for (int x_index = 0; x_index < int3[0][0].length; x_index++) {
                    int3_extended[z_index + 1][y_index + 1][x_index + 1] = int3[z_index][y_index][x_index];
                }
            }
        }

        double bottom, top, front, back, left, right;

        for (int z_index = 1; z_index <= int3.length; z_index++) {
            for (int y_index = 1; y_index <= int3[0].length; y_index++) {
                for (int x_index = 1; x_index <= int3[0][0].length; x_index++) {
                    if (int3_extended[z_index][y_index][x_index] == 1) {
                        bottom = y + ((z_index-1)*scale*y_upscale);
                        top = y + ((z_index)*scale*y_upscale);
                        back = z + (int3[0].length - y_index + 1)*scale;
                        front = z + (int3[0].length - y_index)*scale;
                        left = x + ((x_index-1)*scale);
                        right = x + (x_index*scale);
                        if(int3_extended[z_index-1][y_index][x_index] == 0 && int3_extended[z_index][y_index-1][x_index] == 0) {
                            edge_table.add(new Edge(left, bottom, back, right, bottom, back));
                        }
                        if(int3_extended[z_index-1][y_index][x_index] == 0 && int3_extended[z_index][y_index+1][x_index] == 0) {
                            edge_table.add(new Edge(left, bottom, front, right, bottom, front));
                        }
                        if(int3_extended[z_index+1][y_index][x_index] == 0 && int3_extended[z_index][y_index-1][x_index] == 0) {
                            edge_table.add(new Edge(left, top, back, right, top, back));
                        }
                        if(int3_extended[z_index+1][y_index][x_index] == 0 && int3_extended[z_index][y_index+1][x_index] == 0) {
                            edge_table.add(new Edge(left, top, front, right, top, front));
                        }
                        if(int3_extended[z_index-1][y_index][x_index] == 0 && int3_extended[z_index][y_index][x_index-1] == 0) {
                            edge_table.add(new Edge(left, bottom, front, left, bottom, back));
                        }
                        if(int3_extended[z_index-1][y_index][x_index] == 0 && int3_extended[z_index][y_index][x_index+1] == 0) {
                            edge_table.add(new Edge(right, bottom, front, right, bottom, back));
                        }
                        if(int3_extended[z_index+1][y_index][x_index] == 0 && int3_extended[z_index][y_index][x_index-1] == 0) {
                            edge_table.add(new Edge(left, top, front, left, top, back));
                        }
                        if(int3_extended[z_index+1][y_index][x_index] == 0 && int3_extended[z_index][y_index][x_index+1] == 0) {
                            edge_table.add(new Edge(right, top, front, right, top, back));
                        }
                        if(int3_extended[z_index][y_index-1][x_index] == 0 && int3_extended[z_index][y_index][x_index-1] == 0) {
                            edge_table.add(new Edge(left, bottom, back, left, top, back));
                        }
                        if(int3_extended[z_index][y_index-1][x_index] == 0 && int3_extended[z_index][y_index][x_index+1] == 0) {
                            edge_table.add(new Edge(right, bottom, back, right, top, back));
                        }
                        if(int3_extended[z_index][y_index+1][x_index] == 0 && int3_extended[z_index][y_index][x_index-1] == 0) {
                            edge_table.add(new Edge(left, bottom, front, left, top, front));
                        }
                        if(int3_extended[z_index][y_index+1][x_index] == 0 && int3_extended[z_index][y_index][x_index+1] == 0) {
                            edge_table.add(new Edge(right, bottom, front, right, top, front));
                        }
                    }
                }
            }
        }
    }

    public void render(Camera camera, Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        for (Edge edge : edge_table) {
            edge.render(camera, graphics2D);
        }
    }
}