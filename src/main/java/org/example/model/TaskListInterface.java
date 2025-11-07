package org.example.model;

import java.util.List;

public sealed interface TaskListInterface permits TaskList {
    List<TaskModel> getTasks();
    void addTask(String task, String Group, String description);
    boolean removeTask(TaskModel task);
}
