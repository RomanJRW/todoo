package com.joshwindels.todoo.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.services.TaskListService;
import org.springframework.stereotype.Repository;

@Repository
public class TaskListServiceImpl implements TaskListService {

    public void addTaskToList(Task task, TaskList taskList) {
        taskList.addTask(task);
        saveTaskList(taskList);
        //
        //        SqlParameterSource ps = new BeanPropertySqlParameterSource(this);
        //
        //        jdbcTemplate.execute("SELECT :jdbcTemplate FROM todo.\"public\".task");
    }

    public void removeTaskFromList(Task task, TaskList taskList) {
        taskList.removeTask(task);
        saveTaskList(taskList);
    }

    public TaskList getExistingTaskListFromFromFile(String fileName) {
        TaskList taskList = new TaskList();
        try {
            FileInputStream fis = new FileInputStream(new File("src/main/resources/saved_lists/" + fileName + ".txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            taskList = (TaskList) ois.readObject();

            ois.close();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        return taskList;
    }

    private void saveTaskList(TaskList taskList) {
        try {
            FileOutputStream fos = new FileOutputStream(new File("src/main/resources/saved_lists/" + taskList.getIdentifier() + ".txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(taskList);

            oos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

}
