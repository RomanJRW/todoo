package com.joshwindels.todoo.dos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TaskList implements Serializable {

    private int id;
    private String name;
    private List<Task> tasks;
    private Set<Integer> ownerIds;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int identifier) {
        this.id = identifier;
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

    public Set<Integer> getOwnerIds() {
        return ownerIds;
    }

    public void setOwnerIds(Set<Integer> ownerIds) {
        this.ownerIds = ownerIds;
    }

    public void addOwnerId(Integer ownerId) {
        ownerIds.add(ownerId);
    }
}
