package com.emerging5.omsapi.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Task {
    @Id
    @SequenceGenerator(
        name="task_id_sequence",
        sequenceName = "task_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "task_id_sequence"
    )
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="agentid")
    private Agent agent;
    @ManyToMany
    private List<Process> processes;
    private Long dependsonTaskid;
    private int status;
    private String msg;
    private boolean active;

    public Long getId() {
        return id;
    }
    
    public Task(Long id, String name, Long dependsonTaskid, int status, String msg) {
        this.id = id;
        this.name = name;
        this.dependsonTaskid = dependsonTaskid;
        this.status = status;
        this.msg = msg;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getDependsonTaskid() {
        return dependsonTaskid;
    }
    public void setDependsonTaskid(Long dependsonTaskid) {
        this.dependsonTaskid = dependsonTaskid;
    }
    
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
        
}