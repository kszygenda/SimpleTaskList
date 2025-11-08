package org.example.model;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String,Object> getTaskAsMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("taskName", taskName);
        map.put("taskDescription",taskDescription);
        map.put("group",group);
        return map;
    }

}
