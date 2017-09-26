package com.joshwindels.todoo.dos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session")
public class ToDoList implements Serializable {

    private String identifier;
    private Set<String> tasks;

    public ToDoList() {
        tasks = new HashSet<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Set<String> getTasks(){
        return tasks;
    }

    public void setTasks(Set<String> tasks) {
        this.tasks = tasks;
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

}
