package com.emerging5.omsapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emerging5.omsapi.model.Process;
import com.emerging5.omsapi.model.ProcessRepository;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;

    @Autowired
    public ProcessService(ProcessRepository processRepository){
        this.processRepository = processRepository;
    }
    
    public List<Process> getProcesses(){
        return processRepository.findAll();
    }
}
