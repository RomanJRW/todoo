package com.joshwindels.todoo.dos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TaskList implements Serializable {

    private int identifier;
    private String name;
    private Set<Integer> tasks;

    public TaskList() {
        tasks = new HashSet<>();
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public Set<Integer> getTasks(){
        return tasks;
    }

    public void setTasks(Set<Integer> tasks) {
        this.tasks = tasks;
    }

    public void addTask(int taskId) {
        tasks.add(taskId);
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
