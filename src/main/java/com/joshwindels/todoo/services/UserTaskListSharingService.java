package com.joshwindels.todoo.services;

import java.util.Set;

public interface UserTaskListSharingService {

    void shareTaskListWithUser(int taskListId, int userId, boolean isOwner);

    void unshareTaskListFromUser(int taskListId, int userId);

    Set<Integer> getOwnerIdsForTaskList(int taskListId);

}
