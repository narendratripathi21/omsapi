package com.emerging5.omsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    
}
