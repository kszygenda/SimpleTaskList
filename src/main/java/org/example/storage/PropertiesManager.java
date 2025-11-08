package org.example.storage;

import java.io.*;
import java.util.Properties;

public class PropertiesManager {
    private static final String CONFIG_FILE = "app.properties";

    public void createPropertiesFile() throws IOException {
        Properties config = new Properties();

        config.setProperty("app.name","SimpleTaskList");
        config.setProperty("version", "0.1.0");
        config.setProperty("data.path", System.getProperty("user.home") + "/.tasklist-app/tasks.json");

        try (OutputStream output = new FileOutputStream(CONFIG_FILE)){
            config.store(output, "SimpleTaskList App - config");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Properties readPropertiesFile() throws IOException {
        Properties config = new Properties();

        try (InputStream input = new FileInputStream(CONFIG_FILE)){
          config.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    public static void main(String[] args){
        PropertiesManager manager = new PropertiesManager();
        try {
            manager.createPropertiesFile();
            manager.readPropertiesFile();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
