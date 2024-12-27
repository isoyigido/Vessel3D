package camera;

public class Camera {
    public double x, y, z, yaw, pitch, roll;

    public boolean forward, backward, right, left, up, down, yaw_pos, yaw_neg, pitch_pos, pitch_neg, roll_pos, roll_neg;

    public int focal_length;

    public double speed, sensitivity;

    public boolean gui_enabled = false;

    public Camera(double x_input, double y_input, double z_input, double yaw_input, double pitch_input, double roll_input, int focal_length_input, int speed_input, int sensitivity_input) {
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
    }

    public void update() {
        if(forward) {
            z+=speed;
        }
        if(backward) {
            z-=speed;
        }
        if(right) {
            x+=speed;
        }
        if(left) {
            x-=speed;
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

    private double[] worldToLocal(double worldX, double worldY, double worldZ) {
        double xRelative = worldX - x;
        double yRelative = worldY - y;
        double zRelativeToCamera = worldZ - z;

        double yawRadians = Math.toRadians(yaw);
        double pitchRadians = Math.toRadians(pitch);
        double rollRadians = Math.toRadians(roll);

        double radius, radian_diff, tempX, tempY, tempZ;

        // Rotate around Y-axis (Yaw)
        radius = Math.sqrt(xRelative*xRelative+zRelativeToCamera*zRelativeToCamera);
        radian_diff = yawRadians-Math.atan(-zRelativeToCamera/xRelative);
        if(xRelative<0) {
            radian_diff+=Math.PI;
        }
        tempX = Math.cos(radian_diff) * radius;
        tempZ = Math.sin(radian_diff) * radius;

        xRelative = tempX;
        zRelativeToCamera = tempZ;

        // Rotate around X-axis (Pitch)
        radius = Math.sqrt(yRelative*yRelative+zRelativeToCamera*zRelativeToCamera);
        radian_diff = pitchRadians-Math.atan(-zRelativeToCamera/yRelative);
        if(yRelative<0) {
            radian_diff+=Math.PI;
        }
        tempY = Math.cos(radian_diff) * radius;
        tempZ = Math.sin(radian_diff) * radius;

        yRelative = tempY;
        zRelativeToCamera = tempZ;

        // Rotate around Z-axis (Roll)
        tempX = xRelative * Math.cos(rollRadians) - yRelative * Math.sin(rollRadians);
        tempY = xRelative * Math.sin(rollRadians) + yRelative * Math.cos(rollRadians);
        xRelative = tempX;
        yRelative = tempY;

        double zRelative = zRelativeToCamera - focal_length;

        return new double[]{xRelative, yRelative, zRelative, zRelativeToCamera};
    }
}
