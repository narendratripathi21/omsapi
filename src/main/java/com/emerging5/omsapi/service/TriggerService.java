package com.emerging5.omsapi.service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emerging5.omsapi.model.Trigger;
import com.emerging5.omsapi.model.TriggerRepository;

import jakarta.transaction.Transactional;

@Service
public class TriggerService {
    
    private final TriggerRepository triggerRepository;

    public TriggerService(TriggerRepository triggerRepository){
        this.triggerRepository = triggerRepository;
    }

    public List<Trigger> getTriggers(){
        return triggerRepository.findAll();
    }

    public Trigger getTrigger(Long id){
        return triggerRepository.findById(id).orElse(null);
    }

    public Trigger getTriggerByName(String name){
        return triggerRepository.findByName(name).stream().findFirst().orElse(null);
    }

    @Transactional
    public void toggleActive(Long id){
        Trigger trigger = triggerRepository.findById(id).orElse(null);
        if(trigger!=null ){
            trigger.setModifiedatetime(LocalDateTime.now());
        }
    }
}
