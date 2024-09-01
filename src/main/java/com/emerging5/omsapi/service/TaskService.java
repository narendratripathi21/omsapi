package com.emerging5.omsapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emerging5.omsapi.model.Task;
import com.emerging5.omsapi.model.TaskRepository;
import com.emerging5.omsapi.model.Trigger;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskByName(String name){
        return taskRepository.findByName(name).stream().findFirst().orElse(null);
    }
    
    public Task addTask(Task task){
        Task tempTask = new Task();
        task.setCreateddatetime(LocalDateTime.now());
        try{
            tempTask = taskRepository.save(task);
            tempTask.setTxnstatus(true);
            tempTask.setMessage(CommonService.getMessage("created", task.getClass(), ""));
        }
        catch(Exception ex){
            tempTask.setTxnstatus(false);
            tempTask.setMessage(CommonService.getMessage("invalid", task.getClass(), ex.getMessage()));
        }
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
