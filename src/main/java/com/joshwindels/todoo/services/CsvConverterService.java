package com.joshwindels.todoo.services;

import com.joshwindels.todoo.dos.TaskList;
import org.springframework.stereotype.Component;

@Component
public interface CsvConverterService {

    String convertTaskListToCsv(TaskList taskList);

}
