package main;

import camera.Camera;
import components.Text;
import data.Converter;
import data.Data;
import gui.InfoGUI;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import components.Point;
import objects.RectangularPrism;
import objects.Vessel3D;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ProjectPanel extends JPanel {
    InfoGUI infoGUI;

    public KeyboardInputs keyboardInputs;
    public MouseInputs mouseInputs;

    public Camera camera;

    public static final String source_directory = "res/data";
    BufferedImage[] slices;

    Vessel3D vessel3d;
    RectangularPrism floor;
    RectangularPrism rectangularPrism1;
    RectangularPrism rectangularPrism2;

    Text[] texts;

    public int FPS;
    public int UPS;

    public ProjectPanel() {
        setPanelSize();

        infoGUI = new InfoGUI(this);

        keyboardInputs = new KeyboardInputs(this);
        mouseInputs = new MouseInputs(this);

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        camera = new Camera(this, 0, 0, 0, 0, 0, 0, (int) Camera.calculateFocalLength(90, Constants.screenWidth), 1, 0.1);

        Data.indexDataIn(source_directory);
        slices = Data.loadPngSlices(source_directory);
        assert slices != null;
        vessel3d = new Vessel3D(0, 0, 0, Converter.convertToInt3(slices), 1, 3);

        floor = new RectangularPrism(-200, -25, -200, 400, 25, 400);
        rectangularPrism1 = new RectangularPrism(0, 0, 0, 512, 288, 512);
        rectangularPrism2 = new RectangularPrism(0, 0, 0, 512*3, 50, 512*3);

        FPS = 0;
        UPS = 0;
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

//        floor.renderEdges(camera, graphics2D);
//        rectangularPrism1.renderEdges(camera, graphics2D);
//        rectangularPrism2.renderEdges(camera, graphics2D);
        vessel3d.render(camera, graphics2D);
        infoGUI.render(camera, graphics2D);

    }

    public void updateProject() {
        camera.update();
    }
}