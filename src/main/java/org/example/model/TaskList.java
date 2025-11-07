package org.example.model;

import java.util.ArrayList;
import java.util.List;

//TODO create JUnit Class Testing
public final class TaskList implements TaskListInterface {
    private List<String> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<String> tasks){
        this.taskList = tasks;
    }

    @Override
    public List<String> getTasks() {
        return taskList;
    }

    @Override
    public void addTask(String task) {
        if(task != null && task.trim().isEmpty())
            return;
        taskList.add(task);
    }

    @Override
    public void removeTask(String task) {
        taskList.remove(task);
    }
}
