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
    public final TaskService taskService;
    
    public AgentService(AgentRepository agentRepository, TaskService taskService){
        this.agentRepository = agentRepository;
        this.taskService = taskService;
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
        Agent tempAgent = new Agent();
        agent.setRegistereddatetime(LocalDateTime.now());
        agent.setLastactivedatetime(LocalDateTime.now());
        try{
            tempAgent = agentRepository.save(agent);
            tempAgent.setTxnstatus(true);
            tempAgent.setMessage(CommonService.getMessage("created",agent.getClass(),""));
        }
        catch(Exception ex){
            tempAgent.setTxnstatus(false);
            tempAgent.setMessage(CommonService.getMessage("invalid",agent.getClass(),ex.getMessage()));
        }
        return tempAgent;
    }

    @Transactional
    public Agent updateAgent(Long id,String hostname,  String currentversion){
        Agent agent = agentRepository.findById(id).orElse(new Agent());
        if(hostname!=null && CommonService.isValidString(hostname) && !Objects.equals(agent.getHostname(),hostname)){
            agent.setHostname(hostname);
            agent.setLastupdatedatetime(LocalDateTime.now());
            agent.setTxnstatus(true);
            agent.setMessage(CommonService.getMessage("updated", agent.getClass(), ""));
        }
        if(currentversion!=null && CommonService.isValidString(currentversion) && !Objects.equals(agent.getCurrentversion(),currentversion)){
            agent.setCurrentversion(currentversion);
            agent.setLastupdatedatetime(LocalDateTime.now());
            agent.setTxnstatus(true);
            agent.setMessage(CommonService.getMessage("updated", agent.getClass(), ""));
        }
        else{
            agent.setTxnstatus(false);
            agent.setMessage(CommonService.getMessage("invalid", agent.getClass(), ""));
        }
        return agent;
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
    public Agent toggleActive(Long id){
        Agent agent = agentRepository.findById(id).orElse(null);
        if(agent!=null){
            agent.setLastupdatedatetime(LocalDateTime.now());
            agent.setActive(!agent.isActive());
        }
        return agent;
    }

    public List<Task> getTasks(Long id){
        Agent agent = getAgent(id);
        if(agent != null){
            return agent.getTasks();
        }
        return null;
    }

    @Transactional
    public List<Task> setTasks(Long id, List<Task> tasks){
        Agent agent = getAgent(id);
        if(agent!=null){
            for (Task task : tasks) {

                if(task.getId() != null){
                    task.setAgent(agent);
                }
                else{
                    Task tsk = taskService.getTaskByName(task.getName());
                    tsk = tsk!=null?tsk:taskService.addTask(task);
                    tsk.setAgent(agent);
                }
            }
            return agent.getTasks();
        }
        return null;
    }
}
