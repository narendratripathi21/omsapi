package com.emerging5.omsapi.service;

import java.util.List;

import com.emerging5.omsapi.model.Task;
import com.emerging5.omsapi.model.TaskRepository;
import com.emerging5.omsapi.model.Trigger;

import jakarta.transaction.Transactional;

public class TaskService {
    
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }
    
    public Task addTask(Task task){
        Task tempTask = null;
        tempTask = taskRepository.save(task);
        return tempTask;
    }

    @Transactional
    public Task update(Long id, String name, Trigger trigger){
        Task task = taskRepository.findById(id).orElse(null);
        if(task!=null && CommonService.isValidString(name) && !task.getName().equals(name)){
            task.setName(name);
        }
        return task;
    }
}
