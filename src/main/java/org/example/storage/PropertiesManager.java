package org.example.storage;

import java.io.*;
import java.util.Properties;

public class PropertiesManager {
    public static final String CONFIG_FILE = "app.properties";
    public static final String APP_NAME = "app.name";
    public static final String APP_VERSION = "version";
    public static final String APP_DATA_PATH = "data.path";
    public static void createPropertiesFile() throws IOException {
        Properties config = new Properties();

        config.setProperty(APP_NAME,"SimpleTaskList");
        config.setProperty(APP_VERSION, "0.1.0");
        config.setProperty(APP_DATA_PATH, System.getProperty("user.home") + "/.tasklist-app/tasks.json");

        try (OutputStream output = new FileOutputStream(CONFIG_FILE)){
            config.store(output, "SimpleTaskList App - config");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Properties readPropertiesFile() throws IOException {
        Properties config = new Properties();

        try (InputStream input = new FileInputStream(CONFIG_FILE)){
          config.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    public static void main(String[] args){
        try {
            PropertiesManager.createPropertiesFile();
            PropertiesManager.readPropertiesFile();
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
