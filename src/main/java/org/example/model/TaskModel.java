package org.example.model;

public class TaskModel {
    private int id;
    private String taskName;
    private String taskDescription;
    private String group;
    public TaskModel(int id, String task, String group, String taskDescription){
        this.id = id;
        this.taskName = task;
        this.group = group;
        this.taskDescription = taskDescription;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
