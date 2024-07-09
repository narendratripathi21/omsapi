package com.emerging5.omsapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.emerging5.omsapi.service.ProcessService;
import com.emerging5.omsapi.model.Process;
import com.emerging5.omsapi.model.Trigger;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class ProcessController {
    private final ProcessService processService;

    public ProcessController(ProcessService processService){
        this.processService = processService;
    }

    @GetMapping(path="api/process/getProcesses")
    public List<Process> getProcesses() {
        return processService.getProcesses();
    }

    @PutMapping(path="api/process/updateProcess/{id}")
    public void updateProcess(@PathVariable Long id
    , @RequestParam String name,@RequestBody Trigger trigger) {
        
    }

    @PostMapping("api/process")
    public Process addProcess(@RequestBody Process process) {
        
        
        return process;
    }
    
}
