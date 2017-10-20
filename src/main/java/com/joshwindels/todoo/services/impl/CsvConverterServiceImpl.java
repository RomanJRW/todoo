package com.joshwindels.todoo.services.impl;

import com.joshwindels.todoo.dos.ToDoList;
import com.joshwindels.todoo.services.CsvConverterService;
import org.apache.tomcat.util.buf.StringUtils;

public class CsvConverterServiceImpl implements CsvConverterService {

    public String convertTaskListToCsv(ToDoList toDoList) {

        return StringUtils.join(toDoList.getTasks(), ',');

    }

}
