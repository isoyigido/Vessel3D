package camera;

public class Camera {
    public double x, y, z;

    public boolean forward, backward, right, left, up, down;

    public int focal_length;

    public Camera(double x_input, double y_input, double z_input, boolean forward_input, boolean backward_input, boolean right_input, boolean left_input, boolean up_input, boolean down_input, int focal_length_input) {
        x = x_input;
        y = y_input;
        z = z_input;

        forward = forward_input;
        backward = backward_input;
        right = right_input;
        left = left_input;
        up = up_input;
        down = down_input;

        focal_length = focal_length_input;
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
    }

    public static double calculateFocalLength(double fovDegrees, int  viewportWidth) {
        double fovRadians = Math.toRadians(fovDegrees);

        return (double) viewportWidth / (2 * Math.tan(fovRadians / 2));

    }
}
