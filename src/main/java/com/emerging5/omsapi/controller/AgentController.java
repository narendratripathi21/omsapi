package com.emerging5.omsapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.emerging5.omsapi.model.Agent;
import com.emerging5.omsapi.model.Task;
import com.emerging5.omsapi.service.AgentService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path="/api/agent")
public class AgentController {

    private Logger logger = LoggerFactory.getLogger(AgentController.class);
    private final AgentService agentService;

    public AgentController(AgentService agentService){
        this.agentService = agentService;
    }

    @GetMapping(path="/getAgent/{id}")
    public Agent getAgent(@PathVariable Long id) {
        return agentService.getAgent(id);
    }

    @GetMapping(path="/getAgentByHostName/{hostname}")
    public Agent getAgent(@PathVariable String hostname) {
        return agentService.getAgentByHostname(hostname);
    }

    @GetMapping(path="/getTasks/{id}")
    public List<Task> getTasks(@PathVariable Long id) {
        return agentService.getTasks(id);
    }
    
    @GetMapping(path="/getAgents")
    public List<Agent> getAgents() {
        return agentService.getAgents();
    }
    
    @PutMapping(path="/addAgent")
    public Agent addAgent(@RequestBody Agent agent){
        return agentService.addAgent(agent);
    }

    @PostMapping(path="/updateAgent/{id}")
    public void updateAgent(@PathVariable Long id, @RequestParam(required = false) String hostname, @RequestParam(required = false) String currentversion){
        logger.info("Hostname:{}|CurrentVersion:{}",hostname,currentversion);
        agentService.updateAgent(id, hostname, currentversion);
    }
    
    @PostMapping(path="/setAgentProps/{id}")
    public void setAgentProps(@PathVariable Long id,@RequestBody Agent agent){
        agentService.setAgentProps(id, agent.getTaskscompleted(), agent.getTasksfailed(), agent.getTaskacpu(), agent.getTaskaram(), agent.getStorage());
    }

    @PostMapping(path="/toggleActive/{id}")
    public void toggleActive(@PathVariable Long id){
        agentService.toggleActive(id);
    }
    
}
