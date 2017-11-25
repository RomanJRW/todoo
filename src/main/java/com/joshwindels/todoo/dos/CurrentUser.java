package com.joshwindels.todoo.dos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private Integer id;
    private List<Integer> taskListIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getTaskListIds() {
        return taskListIds;
    }

    public void setTaskListIds(List<Integer> taskListIds) {
        this.taskListIds = taskListIds;
    }
}
