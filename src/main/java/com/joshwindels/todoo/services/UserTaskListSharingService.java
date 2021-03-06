package com.joshwindels.todoo.services;

public interface UserTaskListSharingService {

    void shareTaskListWithUser(int taskListId, int userId, boolean isOwner);

    void unshareTaskListFromUser(int taskListId, int userId);

}
