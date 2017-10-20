package com.joshwindels.todoo.services;

import com.joshwindels.todoo.dos.ToDoList;
import org.springframework.stereotype.Component;

@Component
public interface CsvConverterService {

    String convertTaskListToCsv(ToDoList toDoList);

}
