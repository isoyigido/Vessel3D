package components;

import camera.Camera;
import main.Constants;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;

public class Text {
    public double x, y, z;
    public String text;
    public Font font;

    public Text(double x_input, double y_input, double z_input, String text_input, Font font_input) {
        x = x_input;
        y = y_input;
        z = z_input;

        text = text_input;
        font = font_input;
    }

    public void render(Camera camera, Graphics2D graphics2D) {
        double z_relative = camera.zRelative(x, y, z);

        if (z_relative > 0) {
            int x_projected = camera.projectX(x, y, z);
            int y_projected = camera.projectY(x, y, z);

            graphics2D.setFont(font);
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2D.drawString(text, Constants.xOffset + x_projected, Constants.yOffset - y_projected);
        }
    }
}