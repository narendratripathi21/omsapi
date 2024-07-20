package com.emerging5.omsapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

import com.emerging5.omsapi.model.Agent;
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
        return agentRepository.findById(id).orElseThrow(()-> new IllegalStateException("Agent with id "+id+" does not exist"));
    }

    public Agent getAgentByHostname(String hostname){
        List<Agent> agentsByHostname = agentRepository.findByHostname(hostname).stream().filter(x-> x.isActive()).toList();
        if(agentsByHostname.isEmpty()) throw new IllegalStateException("Agent with hostname "+hostname+" does not exist");
        return agentsByHostname.get(0);
    }

    public Agent addAgent(Agent agent){
        agent.setRegistereddatetime(LocalDateTime.now());
        agent.setLastactivedatetime(LocalDateTime.now());
        return agentRepository.save(agent);
    }

    @Transactional
    public void updateAgent(Long id,String hostname,  String currentversion){
        Agent agent = agentRepository.findById(id).orElseThrow(()->new IllegalStateException("Agent with id "+ id +" does not exists"));
        if(hostname!=null && CommonService.isValidString(hostname) && !Objects.equals(agent.getHostname(),hostname)){
            agent.setHostname(hostname);
        }
        if(currentversion!=null && CommonService.isValidString(currentversion) && !Objects.equals(agent.getCurrentversion(),currentversion)){
            agent.setCurrentversion(currentversion);
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
    public void setActive(Long id,boolean active){
        Agent agent = agentRepository.findById(id).orElseThrow(()->new IllegalStateException("Agent with id "+ id +" does not exists"));
        if(agent.isActive()!=active){
            agent.setActive(active);
        }
    }

}
