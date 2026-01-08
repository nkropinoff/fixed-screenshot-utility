package fixedscreenshotutility.service;

import fixedscreenshotutility.config.AppConfig;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenshotService {

    private final AppConfig config;
    private final Robot robot;

    public ScreenshotService(AppConfig config) {
        this.config = config;

        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            throw new IllegalStateException("Failed to initialize Robot.");
        }
    }

    public void captureToClipboard() {
        try {
            Rectangle captureArea = new Rectangle(
                config.getValue("x"),
                config.getValue("y"),
                config.getValue("width"),
                config.getValue("height")
            );

            BufferedImage screenCapture = robot.createScreenCapture(captureArea);

            TransferableImage transferableImage = new TransferableImage(screenCapture);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(transferableImage, null);
        } catch (Exception e) {
            System.err.println("Capture failed: " + e.getMessage());
        }
    }
}
