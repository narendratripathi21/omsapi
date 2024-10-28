package com.emerging5.omsapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emerging5.omsapi.model.Triggar;
import com.emerging5.omsapi.model.TriggarRepository;

import jakarta.transaction.Transactional;

@Service
public class TriggarService {
    
    private final TriggarRepository triggerRepository;

    public TriggarService(TriggarRepository triggerRepository){
        this.triggerRepository = triggerRepository;
    }

    public List<Triggar> getTriggers(){
        return triggerRepository.findAll();
    }

    public Triggar getTrigger(Long id){
        return triggerRepository.findById(id).orElse(null);
    }

    public Triggar getTriggerByName(String name){
        return triggerRepository.findByName(name).stream().findFirst().orElse(null);
    }

    public Triggar addTrigger(Triggar trigger){
        Triggar tempTrigger = new Triggar();
        trigger.setCreateddatetime(LocalDateTime.now());
        trigger.setActive(true);
        try{
            tempTrigger = triggerRepository.save(trigger);
            tempTrigger.setTxnstatus(true);
            tempTrigger.setMessage(CommonService.getMessage("added", "trigger", ""));
        }
        catch(Exception ex){
            tempTrigger.setTxnstatus(false);
            tempTrigger.setMessage(CommonService.getMessage("failed", "trigger", ex.getMessage()));
        }
        return tempTrigger;
    }

    @Transactional
    public void toggleActive(Long id){
        Triggar trigger = triggerRepository.findById(id).orElse(null);
        if(trigger!=null ){
            trigger.setModifiedatetime(LocalDateTime.now());
        }
    }
}
