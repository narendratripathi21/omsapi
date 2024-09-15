package com.emerging5.omsapi.service;

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

    public Trigger addTrigger(Trigger trigger){
        Trigger tempTrigger = new Trigger();
        trigger.setCreateddatetime(LocalDateTime.now());
        trigger.setActive(true);
        try{
            tempTrigger = triggerRepository.save(trigger);
            tempTrigger.setTxnstatus(true);
            tempTrigger.setMessage(CommonService.getMessage("added", trigger.getClass(), ""));
        }
        catch(Exception ex){
            tempTrigger.setTxnstatus(false);
            tempTrigger.setMessage(CommonService.getMessage("failed", trigger.getClass(), ex.getMessage()));
        }
        return tempTrigger;
    }

    @Transactional
    public void toggleActive(Long id){
        Trigger trigger = triggerRepository.findById(id).orElse(null);
        if(trigger!=null ){
            trigger.setModifiedatetime(LocalDateTime.now());
        }
    }
}
