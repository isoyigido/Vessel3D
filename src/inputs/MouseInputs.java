package inputs;

import main.Constants;
import main.ProjectPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private ProjectPanel projectPanel;

    public int lastMouseX;
    public int lastMouseY;

    public Robot robot;

    public MouseInputs(ProjectPanel projectPanel) {
        this.projectPanel = projectPanel;

        lastMouseX = 0;
        lastMouseY = 0;

        try {
            this.robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        projectPanel.camera.toggleMouse();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (projectPanel.camera.mouse_enabled) {
            int deltaX = e.getXOnScreen() - lastMouseX;
            int deltaY = e.getYOnScreen() - lastMouseY;

            projectPanel.camera.yaw += deltaX * projectPanel.camera.sensitivity;
            projectPanel.camera.pitch -= deltaY * projectPanel.camera.sensitivity;

            projectPanel.camera.pitch = Math.max(-90, Math.min(90, projectPanel.camera.pitch));

            robot.mouseMove(Constants.screenWidth/2, Constants.screenHeight/2);
        } else {
            lastMouseX = e.getXOnScreen();
            lastMouseY = e.getYOnScreen();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }
}