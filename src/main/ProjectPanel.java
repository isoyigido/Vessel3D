package main;

import camera.Camera;
import data.Converter;
import data.Data;
import gui.InfoGUI;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import components.Point;
import objects.RectangularPrism;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ProjectPanel extends JPanel {

    private KeyboardInputs keyboardInputs;
    private MouseInputs mouseInputs;

    public Camera camera;

    public static final String source_directory = "res/data";
    BufferedImage[] slices;

    Point[] points;
    RectangularPrism rectangularPrism;

    public ProjectPanel() {
        setPanelSize();

        keyboardInputs = new KeyboardInputs(this);
        mouseInputs = new MouseInputs(this);

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        camera = new Camera(0, 0, 0, 0, 0, 0, (int) Camera.calculateFocalLength(90, Constants.screenWidth));

        Data.indexDataIn(source_directory);
        slices = Data.loadPngSlices(source_directory);
        assert slices != null;
        points = Converter.convertToPoints(Converter.convertToInt3(slices));

        rectangularPrism = new RectangularPrism(0, 0, 50, 50, 100, 25);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(Constants.screenWidth, Constants.screenHeight);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        rectangularPrism.renderEdges(camera, graphics2D);
//        for(Point point : points) {
//            point.render(camera, graphics2D);
//        }
        InfoGUI.render(camera, graphics2D);
    }

    public void updateProject() {
        camera.update();
    }
}