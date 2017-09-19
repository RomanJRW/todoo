package com.joshwindels.todoo.dos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ToDoList implements Serializable {

    private String identifier;
    private Set<String> tasks;

    public ToDoList(String listIdentifier) {
        identifier = listIdentifier;
        tasks = new HashSet<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public Set<String> getTasks(){
        return tasks;
    }

}
