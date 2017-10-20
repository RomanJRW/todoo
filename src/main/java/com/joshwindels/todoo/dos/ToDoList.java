package com.joshwindels.todoo.dos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class ToDoList implements Serializable {

    private String identifier;
    private Set<Task> tasks;

    public ToDoList() {
        tasks = new HashSet<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Set<Task> getTasks(){
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

}
