package com.emerging5.omsapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.emerging5.omsapi.model.Agent;
import com.emerging5.omsapi.model.Task;
import com.emerging5.omsapi.model.AgentRepository;

import jakarta.transaction.Transactional;

@Service
public class AgentService {
    
    public final AgentRepository agentRepository;
    
    public AgentService(AgentRepository agentRepository){
        this.agentRepository = agentRepository;
    }
  
    public List<Agent> getAgents(){
        return agentRepository.findAll();
    }

    public Agent getAgent(Long id){
        return agentRepository.findById(id).orElse(null);
    }

    public Agent getAgentByHostname(String hostname){
        return agentRepository.findByHostname(hostname).stream().findFirst().orElse(new Agent(hostname,""));
    }

    public Agent addAgent(Agent agent){
        Agent tempAgent = null;
        agent.setRegistereddatetime(LocalDateTime.now());
        agent.setLastactivedatetime(LocalDateTime.now());
        tempAgent = agentRepository.save(agent);
        return tempAgent;
    }

    @Transactional
    public void updateAgent(Long id,String hostname,  String currentversion){
        Agent agent = agentRepository.findById(id).orElseThrow(()->new IllegalStateException("Agent with id "+ id +" does not exists"));
        if(hostname!=null && CommonService.isValidString(hostname) && !Objects.equals(agent.getHostname(),hostname)){
            agent.setHostname(hostname);
            agent.setLastupdatedatetime(LocalDateTime.now());
        }
        if(currentversion!=null && CommonService.isValidString(currentversion) && !Objects.equals(agent.getCurrentversion(),currentversion)){
            agent.setCurrentversion(currentversion);
            agent.setLastupdatedatetime(LocalDateTime.now());
        }
    }
    
    @Transactional
    public void setAgentProps(Long id,int taskscompleted, int tasksfailed, float taskacpu, float taskaram, float storage){
        Agent agent = agentRepository.findById(id).orElseThrow(()->new IllegalStateException("Agent with id "+ id +" does not exists"));
        if(agent.getTaskscompleted()!=taskscompleted) agent.setTaskscompleted(taskscompleted);
        if(agent.getTasksfailed()!=tasksfailed) agent.setTasksfailed(tasksfailed);
        if(agent.getTaskacpu()!=taskacpu && taskacpu>0) agent.setTaskacpu((taskacpu+agent.getTaskacpu())/2);
        if(agent.getTaskaram()!=taskaram && taskaram>0) agent.setTaskaram((taskaram+agent.getTaskaram())/2);
        if(agent.getStorage()!=storage && storage>0) agent.setStorage(storage);
        agent.setLastactivedatetime(LocalDateTime.now());
    }
    
    @Transactional
    public void toggleActive(Long id){
        Agent agent = agentRepository.findById(id).orElseThrow(()->new IllegalStateException("Agent with id "+ id +" does not exists"));
        agent.setLastupdatedatetime(LocalDateTime.now());
        agent.setActive(!agent.isActive());
    }

    public List<Task> getTasks(Long id){
        Agent agent = getAgent(id);
        if(agent != null){
            return agent.getTasks();
        }
        return null;
    }

}
