package com.emerging5.omsapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.emerging5.omsapi.service.ProcessService;
import com.emerging5.omsapi.model.Process;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

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
}
