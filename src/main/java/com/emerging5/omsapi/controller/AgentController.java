package com.emerging5.omsapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.emerging5.omsapi.model.Agent;
import com.emerging5.omsapi.model.Task;
import com.emerging5.omsapi.service.AgentService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(path="/api/agent")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService){
        this.agentService = agentService;
    }

    @GetMapping(path="/getAgent/{id}")
    public Agent getAgent(@PathVariable("id") Long id) {
        return agentService.getAgent(id);
    }

    @GetMapping(path="/getAgentByHostName/{hostname}")
    public Agent getAgent(@PathVariable("hostname") String hostname) {
        return agentService.getAgentByHostname(hostname);
    }

    @GetMapping(path="/getTasks/{id}")
    public List<Task> getTasks(@PathVariable("id") Long id) {
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
    public void updateAgent(@PathVariable("id") Long id, @RequestBody(required = false) String hostname, @RequestBody(required = false) String currentversion){
        agentService.updateAgent(id, hostname, currentversion);
    }
    
    @PostMapping(path="/setAgentProps/{id}")
    public void setAgentProps(@PathVariable("id") Long id,@RequestBody Agent agent){
        agentService.setAgentProps(id, agent.getTaskscompleted(), agent.getTasksfailed(), agent.getTaskacpu(), agent.getTaskaram(), agent.getStorage());
    }

    @DeleteMapping(path="/deleteAgent/{id}")
    public void deleteAgent(@PathVariable("id") Long id){
        agentService.setActive(id, false);
    }
    
}
