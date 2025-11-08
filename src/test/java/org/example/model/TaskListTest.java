package org.example.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    @DisplayName("Konkstruktor powinien poprawnie ustawiać wszystkie pola")
    void constructorShouldSetAllFieldsCorrectly(){
        int id = 10;
        String task = "essa";
        String group = "dom";
        String description = "siema siemanko";

        TaskModel taskModel = new TaskModel(id,task,group,description);

        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        map.put("taskName", task);
        map.put("taskDescription",description);
        map.put("group",group);
        boolean areEqual = map.equals(taskModel.getTaskAsMap());
        assertTrue(areEqual);
    }

    @Test
    @DisplayName("Poprawna zmiana ID")
    void IdShouldBeChanged(){
        TaskModel taskModel = new TaskModel(1,"t","g","d");
        taskModel.setId(2);
        assertEquals(2, taskModel.getId());
    }

}
