package org.example.model;

import java.util.List;

public interface TaskListInterface {
     List<String> getTasks();
     void addTask(String task);
     void removeTask(String task);
}
