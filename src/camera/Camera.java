package camera;

import components.Edge;
import components.Vertex;
import main.Constants;
import main.ProjectPanel;

import java.awt.*;

public class Camera {
    private ProjectPanel projectPanel;

    public double x, y, z, yaw, pitch, roll;

    public boolean forward, backward, right, left, up, down, yaw_pos, yaw_neg, pitch_pos, pitch_neg, roll_pos, roll_neg;

    public int focal_length;

    public double speed, sensitivity;

    public boolean gui_enabled;

    public boolean mouse_enabled;

    public Camera(ProjectPanel projectPanel_input, double x_input, double y_input, double z_input, double yaw_input, double pitch_input, double roll_input, int focal_length_input, double speed_input, double sensitivity_input) {
        projectPanel = projectPanel_input;

        x = x_input;
        y = y_input;
        z = z_input;

        yaw = yaw_input;
        pitch = pitch_input;
        roll = roll_input;

        focal_length = focal_length_input;

        speed = speed_input;
        sensitivity = sensitivity_input;

        {
            forward = false;
            backward = false;
            right = false;
            left = false;
            up = false;
            down = false;
            yaw_pos = false;
            yaw_neg = false;
            pitch_pos = false;
            pitch_neg = false;
            roll_pos = false;
            roll_neg = false;
        }

        gui_enabled = false;
        mouse_enabled = false;
    }

    public void update() {
        double yawRadians = Math.toRadians(yaw);

        if(forward) {
            x+=Math.sin(yawRadians)*speed;
            z+=Math.cos(yawRadians)*speed;
        }
        if(backward) {
            x-=Math.sin(yawRadians)*speed;
            z-=Math.cos(yawRadians)*speed;
        }
        if(right) {
            x+=Math.cos(yawRadians)*speed;
            z-=Math.sin(yawRadians)*speed;
        }
        if(left) {
            x-=Math.cos(yawRadians)*speed;
            z+=Math.sin(yawRadians)*speed;
        }
        if(up) {
            y+=speed;
        }
        if(down) {
            y-=speed;
        }
        if(yaw_pos) {
            yaw+=sensitivity;
        }
        if(yaw_neg) {
            yaw-=sensitivity;
        }
        if(pitch_pos) {
            pitch+=sensitivity;
        }
        if(pitch_neg) {
            pitch-=sensitivity;
        }
        if(roll_pos) {
            roll+=sensitivity;
        }
        if(roll_neg) {
            roll-=sensitivity;
        }
    }

    public static double calculateFocalLength(double fovDegrees, int viewportWidth) {
        double fovRadians = Math.toRadians(fovDegrees);
        return (double) viewportWidth * 0.5 / (Math.tan(fovRadians * 0.5));
    }

    public int projectX(double worldX, double worldY, double worldZ) {
        double[] localCoords = worldToLocal(worldX, worldY, worldZ);
        double xRelative = localCoords[0];
        double zRelative = localCoords[2];
        return (int) ((xRelative * focal_length) / (focal_length + zRelative));
    }

    public int projectY(double worldX, double worldY, double worldZ) {
        double[] localCoords = worldToLocal(worldX, worldY, worldZ);
        double yRelative = localCoords[1];
        double zRelative = localCoords[2];
        return (int) ((yRelative * focal_length) / (focal_length + zRelative));
    }

    public double zRelative(double worldX, double worldY, double worldZ) {
        double[] localCoords = worldToLocal(worldX, worldY, worldZ);
        return localCoords[3];
    }

    public double[] worldToLocal(double worldX, double worldY, double worldZ) {
        double xRelative = worldX - x;
        double yRelative = worldY - y;
        double zRelativeToCamera = worldZ - z;

        double yawRadians = Math.toRadians(yaw);
        double pitchRadians = Math.toRadians(pitch);
        double rollRadians = Math.toRadians(roll);

        double tempX, tempY, tempZ;
        double sin_val, cos_val;

        // Rotate around Y-axis (Yaw)
        sin_val = Math.sin(-yawRadians);
        cos_val = Math.cos(-yawRadians);
        tempX = xRelative * cos_val + zRelativeToCamera * sin_val;
        tempZ = -xRelative * sin_val + zRelativeToCamera * cos_val;
        xRelative = tempX;
        zRelativeToCamera = tempZ;

        // Rotate around X-axis (Pitch)
        sin_val = Math.sin(pitchRadians);
        cos_val = Math.cos(pitchRadians);
        tempY = yRelative * cos_val - zRelativeToCamera * sin_val;
        tempZ = yRelative * sin_val + zRelativeToCamera * cos_val;
        yRelative = tempY;
        zRelativeToCamera = tempZ;

        // Rotate around Z-axis (Roll)
        sin_val = Math.sin(rollRadians);
        cos_val = Math.cos(rollRadians);
        tempX = xRelative * cos_val - yRelative * sin_val;
        tempY = xRelative * sin_val + yRelative * cos_val;
        xRelative = tempX;
        yRelative = tempY;

        double zRelative = zRelativeToCamera - focal_length;

        return new double[]{xRelative, yRelative, zRelative, zRelativeToCamera};
    }

    public void toggleMouse() {
        if(projectPanel.camera.mouse_enabled) {
            projectPanel.setCursor(Cursor.getDefaultCursor());
            mouse_enabled = false;
        } else {
            projectPanel.setCursor(projectPanel.getToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new Point(0, 0), "invisibleCursor"));

            projectPanel.mouseInputs.lastMouseX = Constants.screenWidth / 2;
            projectPanel.mouseInputs.lastMouseY = Constants.screenHeight / 2;
            projectPanel.mouseInputs.robot.mouseMove(projectPanel.mouseInputs.lastMouseX, projectPanel.mouseInputs.lastMouseY);

            mouse_enabled = true;
        }
    }

    public void disableMouse() {
        if(projectPanel.camera.mouse_enabled) {
            projectPanel.setCursor(Cursor.getDefaultCursor());
            mouse_enabled = false;
        }
    }
}
