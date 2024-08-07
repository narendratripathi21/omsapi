package com.emerging5.omsapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emerging5.omsapi.model.Process;
import com.emerging5.omsapi.model.ProcessRepository;
import com.emerging5.omsapi.model.Trigger;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;
    
    public ProcessService(ProcessRepository processRepository){
        this.processRepository = processRepository;
    }
    
    public List<Process> getProcesses(){
        return processRepository.findAll();
    }

    public Optional<Process> getProcess(Long id){
        return processRepository.findById(id);
    }

    public Process addProcess(Process process){
        try{
            if(CommonService.isValidString(process.getName())
                && !process.getTasks().isEmpty())
                return processRepository.save(process);
            else
                return null;
        }
        catch(Exception ex){
            return null;
        }
    }

    public void updateProcess(Long id, String name, Trigger trigger,  ){

    }
    public void deleteProcess(Long id){
        if(getProcess(id).isPresent()){
            processRepository.deleteById(id);
        }
        else{
            throw new IllegalStateException("Process with id:"+id+", does not exist.");
        }
    }
}
