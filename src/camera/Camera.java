package camera;

public class Camera {
    public double x, y, z, yaw, pitch, roll;

    public boolean forward, backward, right, left, up, down, yaw_pos, yaw_neg, pitch_pos, pitch_neg, roll_pos, roll_neg;

    public int focal_length;

    public boolean gui_enabled = false;

    public Camera(double x_input, double y_input, double z_input, double yaw_input, double pitch_input, double roll_input, int focal_length_input) {
        x = x_input;
        y = y_input;
        z = z_input;

        yaw = yaw_input;
        pitch = pitch_input;
        roll = roll_input;

        focal_length = focal_length_input;

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
            z+=5;
        }
        if(backward) {
            z-=5;
        }
        if(right) {
            x+=5;
        }
        if(left) {
            x-=5;
        }
        if(up) {
            y+=5;
        }
        if(down) {
            y-=5;
        }
        if(yaw_pos) {
            yaw+=5;
        }
        if(yaw_neg) {
            yaw-=5;
        }
        if(pitch_pos) {
            pitch+=5;
        }
        if(pitch_neg) {
            pitch-=5;
        }
        if(roll_pos) {
            roll+=5;
        }
        if(roll_neg) {
            roll-=5;
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

    public double zRelative(double worldZ) {
        return worldZ - z;
    }

    private double[] worldToLocal(double worldX, double worldY, double worldZ) {
        //pitch ve yaw için düzelt
        double xRelative = worldX - x;
        double yRelative = worldY - y;
        double zRelative = worldZ - z;

        double yawRadians = Math.toRadians(yaw);
        double pitchRadians = Math.toRadians(pitch);
        double rollRadians = Math.toRadians(roll);

        // Rotate around Y-axis (Yaw)
        double tempX = xRelative - Math.cos(-yawRadians) * zRelative;
        double tempZ = xRelative * Math.sin(-yawRadians) + zRelative * Math.cos(yawRadians);
        xRelative = tempX;
        zRelative = tempZ;

        // Rotate around X-axis (Pitch)
        double tempY = yRelative * Math.cos(pitchRadians) - zRelative * Math.sin(pitchRadians);
        tempZ = yRelative * Math.sin(pitchRadians) + zRelative * Math.cos(pitchRadians);
        yRelative = tempY;
        zRelative = tempZ;

        // Rotate around Z-axis (Roll)
        tempX = xRelative * Math.cos(rollRadians) - yRelative * Math.sin(rollRadians);
        tempY = xRelative * Math.sin(rollRadians) + yRelative * Math.cos(rollRadians);
        xRelative = tempX;
        yRelative = tempY;

        return new double[]{xRelative, yRelative, zRelative};
    }
}
