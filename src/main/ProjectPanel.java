package main;

import camera.Camera;
import gui.InfoGUI;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import objects.Cube;
import objects.Pyramid;

import javax.swing.*;
import java.awt.*;

public class ProjectPanel extends JPanel {

    private KeyboardInputs keyboardInputs;
    private MouseInputs mouseInputs;

    public static Camera camera;
    Cube[] cubes;
    Pyramid[] pyramids;

    public ProjectPanel() {
        setPanelSize();

        keyboardInputs = new KeyboardInputs(this);
        mouseInputs = new MouseInputs(this);

        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        camera = new Camera(0, 0, 0, false, false, false, false, false, false, (int) Camera.calculateFocalLength(90, Constants.screenWidth));

        cubes = new Cube[2];

        cubes[0] = new Cube(0, 0, 50, 100);
        cubes[1] = new Cube(100, 0, 50, 500);



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

//        for(Cube cube : cubes) {
//            cube.renderEdges(camera, graphics2D);
//        }

        int index = 0;
        for (Cube cube : cubes) {
            if (cube == null) {
                System.out.println("Index of null cube: " + index);
            }
            index++;
            cube.renderEdges(camera, graphics2D);
        }

        InfoGUI.render(graphics2D);
    }

    public void updateProject() {
        camera.update();
    }
}