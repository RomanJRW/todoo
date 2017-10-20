package com.joshwindels.todoo.services.impl;

import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.services.CsvConverterService;

public class CsvConverterServiceImpl implements CsvConverterService {

    @Override
    public String convertTaskListToCsv(TaskList taskList) {

        return String.join("\n", taskList.getTasks().toString());

    }

}
