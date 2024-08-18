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
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path="/api/process")
public class ProcessController {
    private final ProcessService processService;

    public ProcessController(ProcessService processService){
        this.processService = processService;
    }

    @GetMapping(path="/getProcesses")
    public List<Process> getProcesses() {
        return processService.getProcesses();
    }

    @PutMapping(path="/updateProcess/{id}")
    public void updateProcess(@PathVariable Long id
    , @RequestParam String name,@RequestBody Trigger trigger) {
        
    }

    @PostMapping("/addProcess")
    public Process addProcess(@RequestBody Process process) {
        return processService.addProcess(process);
    }

    @PostMapping("/toggleActive/{id}")
    public Process toggleActive(@PathVariable Long id) {
        return processService.toggleActive(id);
    }

}
