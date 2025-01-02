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
            projectPanel.camera.forward = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            projectPanel.camera.backward = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            projectPanel.camera.right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            projectPanel.camera.left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            projectPanel.camera.up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            projectPanel.camera.down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            projectPanel.camera.yaw_pos = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            projectPanel.camera.yaw_neg = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            projectPanel.camera.pitch_pos = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            projectPanel.camera.pitch_neg = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            projectPanel.camera.roll_pos = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            projectPanel.camera.roll_neg = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_R) {
            projectPanel.camera.yaw = 0;
            projectPanel.camera.pitch = 0;
            projectPanel.camera.roll = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_O) {
            projectPanel.camera.x = 0;
            projectPanel.camera.y = 0;
            projectPanel.camera.z = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_PLUS || e.getKeyCode() == 107) {
            projectPanel.camera.focal_length+=5;
        }
        if (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == 109) {
            projectPanel.camera.focal_length-=5;
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
            projectPanel.camera.gui_enabled = !projectPanel.camera.gui_enabled;
        }
        if (e.getKeyCode() == KeyEvent.VK_M) {
            projectPanel.camera.toggleMouse();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            projectPanel.camera.disableMouse();
        }
        if(e.getKeyCode() == KeyEvent.VK_1) {
            projectPanel.liver.liver_enabled = true;
            projectPanel.liver.vessel_enabled = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_2) {
            projectPanel.liver.liver_enabled = false;
            projectPanel.liver.vessel_enabled = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_3) {
            projectPanel.liver.liver_enabled = true;
            projectPanel.liver.vessel_enabled = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
            projectPanel.liver.liver_enabled = true;
            projectPanel.liver.vessel_enabled = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
            projectPanel.liver.liver_enabled = false;
            projectPanel.liver.vessel_enabled = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
            projectPanel.liver.liver_enabled = true;
            projectPanel.liver.vessel_enabled = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            projectPanel.camera.forward = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            projectPanel.camera.backward = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            projectPanel.camera.right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            projectPanel.camera.left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            projectPanel.camera.up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            projectPanel.camera.down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            projectPanel.camera.yaw_pos = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            projectPanel.camera.yaw_neg = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            projectPanel.camera.pitch_pos = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            projectPanel.camera.pitch_neg = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_L) {
            projectPanel.camera.roll_pos = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            projectPanel.camera.roll_neg = false;
        }
    }
}