package org.example.core;

import java.util.List;

public sealed interface TaskListInterface permits TaskList {
    List<TaskModel> getTasks();
    void addTask(String task, String Group, String description);
    boolean removeTask(TaskModel task);
}
