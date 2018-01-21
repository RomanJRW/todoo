package com.joshwindels.todoo.services.impl;

import com.joshwindels.todoo.services.UserTaskListSharingService;
import org.springframework.stereotype.Service;

@Service
public class UserTaskListSharingServiceImpl implements UserTaskListSharingService {

    @Override
    public void shareTaskListWithUser(int taskListId, int userId) {
        
    }

    @Override
    public void unshareTaskListFromUser(int taskListId, int userId) {

    }
}
