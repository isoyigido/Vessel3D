package data;

import components.Point;
import objects.RectangularPrism;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static int[][][] convertToInt3(BufferedImage[] slices) {
        int[][][] result = new int[slices.length][slices[0].getHeight()][slices[0].getWidth()];

        int blackRGB = 0xFF000000;

        for (int z = 0; z < slices.length; z++) {
            BufferedImage slice = slices[z];
            for (int y = 0; y < slice.getHeight(); y++) {
                for (int x = 0; x < slice.getWidth(); x++) {
                    if (slice.getRGB(x, y) == blackRGB) {
                        result[z][y][x] = 0;
                    } else {
                        result[z][y][x] = 1;
                    }
                }
            }
        }
        return result;
    }

    public static Point[] convertToPoints(int[][][] int3) {
        List<Point> points = new ArrayList<>();

        for(int z_index = 0; z_index < int3.length; z_index++) {
            for(int y_index = 0; y_index < int3[0].length; y_index++) {
                for(int x_index = 0; x_index < int3[0][0].length; x_index++) {
                    if(int3[z_index][y_index][x_index] == 1) {
                        points.add(new Point(x_index, z_index, y_index, 1));
                    }
                }
            }
        }

        return points.toArray(new Point[0]);
    }

//    public static RectangularPrism[] convertToRectangularPrisms(int[][][] int3) {
//        List<RectangularPrism> rectangularPrisms = new ArrayList<>();
//
//        for(int z_index = 0; z_index < int3.length; z_index++) {
//            for(int y_index = 0; y_index < int3[0].length; y_index++) {
//                for(int x_index = 0; x_index < int3[0][0].length; x_index++) {
//                    if(int3[z_index][y_index][x_index] == 1) {
//                        rectangularPrisms.add(new Point(x_index, y_index, z_index, 1));
//                    }
//                }
//            }
//        }
//
//        return rectangularPrisms.toArray(new Point[0]);
//    }
}
