package fixedscreenshotutility;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import fixedscreenshotutility.config.AppConfig;
import fixedscreenshotutility.listener.GlobalHotkeyListener;
import fixedscreenshotutility.service.ScreenshotService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) {
        try {
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.WARNING);
            logger.setUseParentHandlers(false);

            AppConfig config = new AppConfig();
            ScreenshotService screenshotService = new ScreenshotService(config);
            GlobalHotkeyListener listener = new GlobalHotkeyListener(screenshotService);

            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(listener);

            setupSystemTray();

            System.out.println("App is running");
        } catch (NativeHookException | AWTException e) {
            System.err.println("Startup failed: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void setupSystemTray() throws AWTException {
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported. App will run without tray icon.");
            return;
        }

        SystemTray tray = SystemTray.getSystemTray();
        PopupMenu popup = new PopupMenu();

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(event -> shutdown());
        popup.add(exitItem);

        Image iconImage  = createDefaultIcon();

        TrayIcon trayIcon = new TrayIcon(iconImage, "Fixed Screenshot Utility", popup);
        trayIcon.setImageAutoSize(true);

        tray.add(trayIcon);
    }

    private static Image createDefaultIcon() {
        int size = 16;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(new Color(30, 144, 255));
        g.setStroke(new BasicStroke(2));

        g.drawLine(1, 1, 6, 1);
        g.drawLine(1, 1, 1, 6);

        g.drawLine(size - 2, 1, size - 7, 1);
        g.drawLine(size - 2, 1, size - 2, 6);

        g.drawLine(1, size - 2, 6, size - 2);
        g.drawLine(1, size - 2, 1, size - 7);

        g.drawLine(size - 2, size - 2, size - 7, size - 2);
        g.drawLine(size - 2, size - 2, size - 2, size - 7);

        g.setColor(Color.RED);
        g.fillOval(size/2 - 2, size/2 - 2, 4, 4);

        g.dispose();
        return image;
    }

private static void shutdown() {
    try {
        GlobalScreen.unregisterNativeHook();
    } catch (NativeHookException e) {
        e.printStackTrace();
    }
    System.exit(0);
}
}
