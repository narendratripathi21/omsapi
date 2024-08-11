package com.emerging5.omsapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emerging5.omsapi.model.Process;
import com.emerging5.omsapi.model.ProcessRepository;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;
    
    public ProcessService(ProcessRepository processRepository){
        this.processRepository = processRepository;
    }
    
    public List<Process> getProcesses(){
        return processRepository.findAll();
    }

    public Process getProcess(Long id){
        return processRepository.findById(id).orElse(null);
    }

    public Process getProcessByName(String name){
        return processRepository.findByName(name).stream().findFirst().orElse(new Process());
    }

    public Process addProcess(Process process){
        try{
            if(CommonService.isValidString(process.getName())
                && !process.getTasks().isEmpty() && getProcessByName(process.getName()).getId()==null){
                process.setCreateddatetime(LocalDateTime.now());
                return processRepository.save(process);
            }
            else{
                return null;
            }
        }
        catch(Exception ex){
            return null;
        }
    }

    public void updateProcess(Long id, String name){
        Process temProcess = processRepository.findById(id).orElse(null);
        if(temProcess != null){

        }
    }

    public void deleteProcess(Long id){
        if(getProcess(id)!=null){
            processRepository.deleteById(id);
        }
        else{
            throw new IllegalStateException("Process with id:"+id+", does not exist.");
        }
    }
}
