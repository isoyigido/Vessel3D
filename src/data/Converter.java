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

        int lowerLimit = 0xFF646464;

        for (int z = 0; z < slices.length; z++) {
            BufferedImage slice = slices[z];
            for (int y = 0; y < slice.getHeight(); y++) {
                for (int x = 0; x < slice.getWidth(); x++) {
                    if (slice.getRGB(x, y) < lowerLimit) {
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
                        points.add(new Point(x_index, z_index*3, y_index, 1));
                    }
                }
            }
        }

        return points.toArray(new Point[0]);
    }

    public static RectangularPrism[] convertToRectangularPrisms(int[][][] int3) {
        List<RectangularPrism> rectangularPrisms = new ArrayList<>();

        int scale = 1;
        int y_upscale = 3;

        int temp = 0;
        int firstX = -1;

        for(int z_index = 0; z_index < int3.length; z_index++) {
            for(int y_index = 0; y_index < int3[0].length; y_index++) {
                for(int x_index = 0; x_index < int3[0][0].length; x_index++) {
                    if(int3[z_index][y_index][x_index] == 1) {
                        if(temp == 0) {
                            firstX = x_index;
                        }
                        temp++;
                    } else if (temp > 0) {
                        rectangularPrisms.add(new RectangularPrism(firstX*scale, z_index*scale*y_upscale, y_index*scale, scale*temp, scale*y_upscale, scale));
                        temp = 0;
                    }
                }
            }
        }

        return rectangularPrisms.toArray(new RectangularPrism[0]);
    }

    public static RectangularPrism[] convertToRectangularPrisms_test(int[][][] int3) {
        List<RectangularPrism> rectangularPrisms = new ArrayList<>();

        int firstX = -1, lastX;

        int scale = 1;
        int y_upscale = 3;
        for(int z_index = 0; z_index < 1; z_index++) {
            for(int y_index = 0; y_index < int3[0].length; y_index++) {
                for(int x_index = 0; x_index < int3[0][0].length; x_index++) {
                    if(int3[z_index][y_index][x_index] == 1) {
                        if(firstX == -1) {
                            firstX = x_index;
                        }
                    } else if (firstX != -1) {
                        lastX = x_index - 1;
                        for(int row = y_index+1; row < int3[0].length; row++) {
                            for(int col = firstX; col <= lastX; col++) {
                                if(int3[z_index][row][col] != 1) {
                                    rectangularPrisms.add(new RectangularPrism(firstX*scale, z_index*scale*y_upscale, y_index*scale, scale*(lastX-firstX+1), scale*y_upscale, scale*(row-y_index)));
                                    firstX = -1;
                                    break;
                                }
                            }
                            if(firstX == -1) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        return rectangularPrisms.toArray(new RectangularPrism[0]);
    }
}
