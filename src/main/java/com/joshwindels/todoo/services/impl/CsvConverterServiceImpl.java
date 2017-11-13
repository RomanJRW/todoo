package com.joshwindels.todoo.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.services.CsvConverterService;
import org.springframework.stereotype.Service;

@Service
public class CsvConverterServiceImpl implements CsvConverterService {

    @Override
    public String convertTaskListToCsv(TaskList taskList) {
        List<Task> tl = taskList.getTasks();
        List<String> taskStrings = new ArrayList<>();
        for (int i = 0 ; i < tl.size() ; i ++) {
            Task task = tl.get(i);
            taskStrings.add(String.join(",",
                    String.valueOf(task.getId()), task.getDescription(),
                    String.valueOf(task.isCompleted())));
        }
        return String.join("\n", taskStrings);
    }

}
