package data;

import components.Point;
import components.Text;
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

//      Debugging purposes
//        int x_max = slices[0].getWidth() - 1;
//        int y_max = slices[0].getHeight() - 1;
//        int z_max = slices.length - 1;
//        result[0][0][0] = 1;
//        result[0][0][x_max] = 1;
//        result[0][y_max][0] = 1;
//        result[0][y_max][x_max] = 1;
//        result[z_max][0][0] = 1;
//        result[z_max][0][x_max] = 1;
//        result[z_max][y_max][0] = 1;
//        result[z_max][y_max][x_max] = 1;
//      --------------------------------

        return result;
    }

//    public static boolean isInnermost(int[][][] int3, int x, int y, int z) {
//        if (x <= 0 || x >= int3[0][0].length - 1 || y <= 0 || y >= int3[0].length - 1 || z <= 0 || z >= int3.length - 1) {
//            return false;
//        }
//        return int3[z - 1][y][x] == 1 && int3[z + 1][y][x] == 1 && int3[z][y - 1][x] == 1 && int3[z][y + 1][x] == 1 && int3[z][y][x - 1] == 1 && int3[z][y][x + 1] == 1;
//    }
//
//    public static int[][][] removeInnerUnits(int[][][] int3) {
//        int[][][] result = new int[int3.length][int3[0].length][int3[0][0].length];
//        for (int z = 0; z < int3.length; z++) {
//            for (int y = 0; y < int3[0].length; y++) {
//                for (int x = 0; x < int3[0][0].length; x++) {
//                    result[z][y][x] = int3[z][y][x];
//                }
//            }
//        }
//
//        for (int z = 0; z < int3.length; z++) {
//            for (int y = 0; y < int3[0].length; y++) {
//                for (int x = 0; x < int3[0][0].length; x++) {
//                    if (int3[z][y][x] == 1 && isInnermost(int3, x, y, z)) {
//                        result[z][y][x] = 0;
//                    }
//                }
//            }
//        }
//
//        print2dIntegerArray(result[1]);
//
//        return result;
//    }
//
    @Deprecated
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

//    public static Text[] convertToText(int[][][] int3) {
//        List<Text> texts = new ArrayList<>();
//
//        for(int z_index = 0; z_index < int3.length; z_index++) {
//            for(int y_index = 0; y_index < int3[0].length; y_index++) {
//                for(int x_index = 0; x_index < int3[0][0].length; x_index++) {
//                    texts.add(new Text(x_index-100, z_index, y_index, Integer.toString(int3[z_index][y_index][x_index]), new Font("Calibri", Font.PLAIN, 24)));
//                }
//            }
//        }
//
//        return texts.toArray(new Text[0]);
//    }


//
//    public static RectangularPrism[] convertToRectangularPrisms(int[][][] int3) {
//        List<RectangularPrism> rectangularPrisms = new ArrayList<>();
//
//        int scale = 1;
//        int y_upscale = 3;
//
//        int temp = 0;
//        int firstX = -1;
//
//        for(int z_index = 0; z_index < int3.length; z_index++) {
//            for(int y_index = 0; y_index < int3[0].length; y_index++) {
//                for(int x_index = 0; x_index < int3[0][0].length; x_index++) {
//                    if(int3[z_index][y_index][x_index] == 1) {
//                        if(x_index == int3[0][0].length - 1) {
//                            rectangularPrisms.add(new RectangularPrism(x_index*scale, z_index*scale*y_upscale, (int3[0].length-y_index-1)*scale, scale, scale*y_upscale, scale));
//                            temp = 0;
//                        } else if (temp == 0) {
//                            firstX = x_index;
//                        }
//                        temp++;
//                    } else if (temp > 0) {
//                        rectangularPrisms.add(new RectangularPrism(firstX*scale, z_index*scale*y_upscale, (int3[0].length-y_index-1)*scale, scale*temp, scale*y_upscale, scale));
//                        temp = 0;
//                    }
//                }
//            }
//        }
//
//        return rectangularPrisms.toArray(new RectangularPrism[0]);
//    }
//
//    public static RectangularPrism[] convertToRectangularPrisms_test(int[][][] int3) {
//        List<RectangularPrism> rectangularPrisms = new ArrayList<>();
//
//        int firstX = -1, lastX;
//
//        int scale = 1;
//        int y_upscale = 3;
//        for(int z_index = 0; z_index < 1; z_index++) {
//            for(int y_index = 0; y_index < int3[0].length; y_index++) {
//                for(int x_index = 0; x_index < int3[0][0].length; x_index++) {
//                    if(int3[z_index][y_index][x_index] == 1) {
//                        if(x_index == int3[0][0].length - 1) {
//                            if(y_index == int3[0].length - 1) {
//                                rectangularPrisms.add(new RectangularPrism(x_index*scale, z_index*scale*y_upscale, (int3[0].length-y_index-1)*scale, scale, scale*y_upscale, scale));
//                                firstX = -1;
//                            } else {
//                                for (int row = y_index + 1; row < int3[0].length; row++) {
//                                    if (int3[z_index][row][x_index] != 1) {
//                                        rectangularPrisms.add(new RectangularPrism(x_index * scale, z_index*scale*y_upscale, (int3[0].length - y_index - 1) * scale, scale, scale * y_upscale, scale * (row - y_index)));
//                                        firstX = -1;
//                                        break;
//                                    }
//                                }
//                            }
//                        } else if (firstX == -1) {
//                            firstX = x_index;
//                        }
//                    } else if (firstX != -1) {
//                        lastX = x_index - 1;
//                        if(y_index == int3[0].length - 1) {
//                            rectangularPrisms.add(new RectangularPrism(firstX*scale, z_index*scale*y_upscale, (int3[0].length-y_index-1)*scale, scale*(lastX-firstX+1), scale*y_upscale, scale));
//                            firstX = -1;
//                        } else {
//                            for (int row = y_index + 1; row < int3[0].length; row++) {
//                                for (int col = firstX; col <= lastX; col++) {
//                                    if (int3[z_index][row][col] != 1) {
//                                        System.out.println(firstX + " " + y_index + " | " + (lastX - firstX + 1) + " " + (row - y_index));
//                                        rectangularPrisms.add(new RectangularPrism(firstX * scale, z_index * scale * y_upscale, (int3[0].length - y_index - 1) * scale, scale * (lastX - firstX + 1), scale * y_upscale, scale * (row - y_index)));
//                                        firstX = -1;
//                                        break;
//                                    }
//                                }
//                                if (firstX == -1) {
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return rectangularPrisms.toArray(new RectangularPrism[0]);
//    }

    public static void print2dIntegerArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
    }
}
