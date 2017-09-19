package com.joshwindels.todoo.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.joshwindels.todoo.dos.ToDoList;
import org.springframework.stereotype.Component;

@Component
public class TaskListService {

    public void addTaskToList(String task, ToDoList taskList) {
        taskList.addTask(task);
        saveTaskList(taskList);
    }

    public void removeTaskFromList(String task, ToDoList taskList) {
        taskList.removeTask(task);
        saveTaskList(taskList);
    }

    public ToDoList getExistingTaskListFromFromFile(String fileName) {
        ToDoList taskList = new ToDoList(fileName);
        try {
            FileInputStream fis = new FileInputStream(new File("src/main/resources/saved_lists/" + fileName + ".txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);

            taskList = (ToDoList) ois.readObject();

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

    private void saveTaskList(ToDoList taskList) {
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
