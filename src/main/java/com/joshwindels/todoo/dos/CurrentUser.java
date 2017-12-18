package com.joshwindels.todoo.dos;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
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
