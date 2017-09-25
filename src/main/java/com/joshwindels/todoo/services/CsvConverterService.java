package com.joshwindels.todoo.services;

import java.util.List;

import com.joshwindels.todoo.dos.ToDoList;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CsvConverterService {

    public String convertTaskListToCsv(ToDoList toDoList) {

        return StringUtils.join(toDoList.getTasks(), ',');

    }

}
