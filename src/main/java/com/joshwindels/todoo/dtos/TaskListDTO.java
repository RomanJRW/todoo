package com.joshwindels.todoo.dtos;

import java.util.ArrayList;
import java.util.List;

import com.joshwindels.todoo.dos.Task;

public class TaskListDTO {

    private int id;
    private String name;
    private List<Task> tasks;

    public TaskListDTO() {
        tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int task) {
        tasks.remove(task);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
