package com.joshwindels.todoo.dos;

import org.springframework.stereotype.Component;

@Component
public class Task {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
