package com.emerging5.omsapi.service;

import java.util.List;

import com.emerging5.omsapi.model.Task;
import com.emerging5.omsapi.model.TaskRepository;

public class TaskService {
    
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }
    
    
}
