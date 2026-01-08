package fixedscreenshotutility.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AppConfig {

    private final Properties properties = new Properties();
    private static final Path CONFIG_PATH = Paths.get("config.properties");

    public AppConfig() {
        loadOrCreateConfig();
    }

    private void loadOrCreateConfig() {
        if (Files.exists(CONFIG_PATH)) {
            try (var reader = Files.newBufferedReader(CONFIG_PATH)) {
                properties.load(reader);
            } catch (IOException e) {
                System.err.println("Failed to load properties: " + e.getMessage());
            }
        } else {
            createDefaultConfig();
        }
    }

    private void createDefaultConfig() {
        properties.setProperty("x", "100");
        properties.setProperty("y", "100");
        properties.setProperty("width", "400");
        properties.setProperty("height", "300");

        try (var writer = Files.newBufferedWriter(CONFIG_PATH)) {
            properties.store(writer, "Screenshot Area Configuration");
        } catch (IOException e) {
            System.err.println("Failed to create default fixedscreenshotutility.config: " + e.getMessage());
        }
    }

    public int getValue(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            System.err.println("Value for key: " + key + " not exists");
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.err.println("Failed to parse value: " + value);
        }
        return 0;
    }
}
