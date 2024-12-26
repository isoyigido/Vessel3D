package inputs;

import main.ProjectPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private ProjectPanel projectPanel;

    public KeyboardInputs(ProjectPanel projectPanel) {
        this.projectPanel = projectPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            ProjectPanel.camera.forward = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            ProjectPanel.camera.backward = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            ProjectPanel.camera.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            ProjectPanel.camera.left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            ProjectPanel.camera.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            ProjectPanel.camera.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            ProjectPanel.camera.forward = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            ProjectPanel.camera.backward = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            ProjectPanel.camera.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            ProjectPanel.camera.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            ProjectPanel.camera.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            ProjectPanel.camera.down = false;
        }
    }
}