package com.joshwindels.todoo.services.impl;

import java.util.Set;

import com.joshwindels.todoo.repositories.TaskListRepository;
import com.joshwindels.todoo.services.UserTaskListSharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTaskListSharingServiceImpl implements UserTaskListSharingService {

    @Autowired
    private TaskListRepository taskListRepository;

    @Override
    public void shareTaskListWithUser(int taskListId, int userId, boolean isOwner) {
        taskListRepository.addTaskListForUser(taskListId, userId, isOwner);
    }

    @Override
    public void unshareTaskListFromUser(int taskListId, int userId) {
        taskListRepository.removeTaskListForUser(taskListId, userId);
    }

    @Override
    public Set<Integer> getOwnerIdsForTaskList(int taskListId) {

        return null;
    }
}
