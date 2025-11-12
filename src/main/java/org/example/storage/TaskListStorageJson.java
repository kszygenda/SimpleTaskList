package org.example.storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.core.TaskList;
import org.example.core.TaskModel;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class TaskListStorageJson {
    public void save(TaskList taskList) throws IOException {
        String filePath = getStoragePath();
        ensureDirectoryExists(filePath);
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(taskList.getTasks(), writer);
        }
    }

    public List<TaskModel> loadTaskList() {
        try {
            FileReader reader = new FileReader(getStoragePath());
            Gson gson = new Gson();
            Type taskListType = new TypeToken<List<TaskModel>>() {}.getType();
            List<TaskModel> tasks = gson.fromJson(reader, taskListType);
            if(tasks == null){
                return new ArrayList<>();
            }
            return tasks;
        } catch (IOException e) {
            System.out.println("Błąd wczytywania zadań");
        }
        return new ArrayList<>();
    }

    private String getStoragePath() throws IOException {
        Properties config = PropertiesManager.readPropertiesFile();
        return config.getProperty("data.path");
    }

    private void ensureDirectoryExists(String filePath) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean created = parentDir.mkdirs();
            if (!created && !parentDir.exists()) {
                throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
            }
        }
    }
}

