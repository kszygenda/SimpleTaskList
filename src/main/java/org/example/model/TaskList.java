package org.example.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

//TODO create JUnit Class Testing
public final class TaskList implements TaskListInterface {
    private List<TaskModel> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<TaskModel> tasks){
        this.taskList = tasks;
    }

    @Override
    public List<TaskModel> getTasks() {
        return taskList;
    }

    @Override
    public void addTask(String task, String group, String description) {
        if(task != null && task.trim().isEmpty())
            return;
        taskList.add(new TaskModel(getNewTaskId(), task, group, description));
    }

    @Override
    public boolean removeTask(TaskModel task) {
        boolean isRemoved = taskList.remove(task);
        if(isRemoved){
            reindexTasks();
        }
        return isRemoved;
    }

    private int getNewTaskId(){
        if (this.taskList == null || taskList.isEmpty()){
            return 0;
        }

        Optional<TaskModel> maxIdTask = taskList.stream()
                .max(Comparator.comparing(TaskModel::getId));

        return maxIdTask.get().getId() + 1;
    }

    private void reindexTasks() {
        for(int i = 0; i<taskList.size(); i++){
            taskList.get(i).setId(i+1);
        }
    }

}
