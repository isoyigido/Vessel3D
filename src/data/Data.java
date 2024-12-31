package data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;

public class Data {
    public static void indexDataIn(String directoryPath) {
        try {
            File directory = new File(directoryPath);

            if (!directory.exists() || !directory.isDirectory()) {
                System.err.println("The specified directory does not exist or is not a directory.");
                return;
            }

            File[] files = directory.listFiles((dir, name) -> name.matches("\\d+\\.png"));

            if (files == null || files.length == 0) {
                return;
            }

            java.util.Arrays.sort(files, (f1, f2) -> {
                int num1 = Integer.parseInt(f1.getName().replace(".png", ""));
                int num2 = Integer.parseInt(f2.getName().replace(".png", ""));
                return Integer.compare(num1, num2);
            });

            boolean alreadyRenamed = true;
            for (int i = 0; i < files.length; i++) {
                String expectedFileName = (i + 1) + ".png";
                if (!files[i].getName().equals(expectedFileName)) {
                    alreadyRenamed = false;
                    break;
                }
            }

            if (alreadyRenamed) {
                System.out.println("Files are already named correctly. No action needed.");
            } else {
                int counter = 0;
                for (File file : files) {
                    String newFileName = counter + ".png";
                    File newFile = new File(directory, newFileName);
                    if (file.renameTo(newFile)) {
                        System.out.println("Renamed " + file.getName() + " to " + newFileName);
                    } else {
                        System.err.println("Failed to rename " + file.getName());
                    }
                    counter++;
                }
            }

        } catch (Exception e) {
            System.err.println("An error occurred while renaming the files: " + e.getMessage());
        }
    }

    public static BufferedImage[] loadPngSlices(String directoryPath) {
        List<BufferedImage> imageList = new ArrayList<>();

        try {
            File directory = new File(directoryPath);

            if (!directory.exists() || !directory.isDirectory()) {
                System.err.println("The specified directory does not exist or is not a directory.");
                return null;
            }

            File[] files = directory.listFiles((dir, name) -> name.matches("\\d+\\.png"));

            if (files == null || files.length == 0) {
                System.err.println("No matching PNG files found in the directory.");
                return null;
            }

            List<File> sortedFiles = new ArrayList<>();
            Collections.addAll(sortedFiles, files);
            sortedFiles.sort((f1, f2) -> {
                int num1 = Integer.parseInt(f1.getName().replace(".png", ""));
                int num2 = Integer.parseInt(f2.getName().replace(".png", ""));
                return Integer.compare(num1, num2);
            });

            for (File file : sortedFiles) {
                BufferedImage img = ImageIO.read(file);
                if (img != null) {
                    imageList.add(img);
                }
            }

        } catch (IOException e) {
            System.err.println("An error occurred while loading the images: " + e.getMessage());
            return null;
        }

        return imageList.toArray(new BufferedImage[0]);
    }
}
