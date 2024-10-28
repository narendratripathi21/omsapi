package com.emerging5.omsapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emerging5.omsapi.model.Task;
import com.emerging5.omsapi.model.TaskRepository;
import com.emerging5.omsapi.model.Triggar;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final TriggarService triggerService;

    public TaskService(TaskRepository taskRepository, TriggarService triggerService){
        this.taskRepository = taskRepository;
        this.triggerService = triggerService;
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
            tempTask.setMessage(CommonService.getMessage("created", "task", ""));
        }
        catch(Exception ex){
            tempTask.setTxnstatus(false);
            tempTask.setMessage(CommonService.getMessage("invalid", "task", ex.getMessage()));
        }
        return tempTask;
    }

    @Transactional
    public Task update(Long id, String name, Triggar trigger){
        Task task = taskRepository.findById(id).orElse(null);
        if(task!=null && CommonService.isValidString(name) && !task.getName().equals(name)){
            task.setName(name);
        }
        if(task!=null && (CommonService.isValidCron(trigger.getCronString()) || CommonService.isValidString(trigger.getObservePath()))){
            if(trigger.getId()!=null){
                task.setTrigger(trigger);
            }
            else{
                Triggar trig = triggerService.getTriggerByName(trigger.getName());
                trig = trig!=null?trig:triggerService.addTrigger(trig);
                task.setTrigger(trig);
            }
        }
        return task;
    }
}
