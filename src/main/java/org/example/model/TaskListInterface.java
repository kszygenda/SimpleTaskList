package org.example.model;

import java.util.List;

public sealed interface TaskListInterface permits TaskList {
     List<String> getTasks();
     void addTask(String task);
     void removeTask(String task);
}
