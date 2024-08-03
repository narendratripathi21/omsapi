package com.emerging5.omsapi.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"hostname"})
)
public class Agent {
            
    @SequenceGenerator(
        name="agent_id_sequence",
        sequenceName = "agent_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "agent_id_sequence"
    )
    @Id
    private Long id;
    private String hostname;
    private boolean active;
    private int taskscompleted;
    private int tasksfailed;                                                                        
    private float taskacpu;
    private float taskaram;
    private float storage; 
    private String currentversion;
    private LocalDateTime registereddatetime;
    private LocalDateTime lastactivedatetime;
    private LocalDateTime lastupdatedatetime;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="agentid",referencedColumnName = "id")
    private List<Task> tasks; 

    public Agent() {
    }

    public Agent(String hostname, String currentversion) {
        this.hostname = hostname;   
        this.active = true;
        this.currentversion = currentversion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTaskscompleted() {
        return taskscompleted;
    }

    public void setTaskscompleted(int taskscompleted) {
        this.taskscompleted = taskscompleted;
    }

    public int getTasksfailed() {
        return tasksfailed;
    }

    public void setTasksfailed(int tasksfailed) {
        this.tasksfailed = tasksfailed;
    }

    public float getTaskacpu() {
        return taskacpu;
    }

    public void setTaskacpu(float taskacpu) {
        this.taskacpu = taskacpu;
    }

    public float getTaskaram() {
        return taskaram;
    }

    public void setTaskaram(float taskaram) {
        this.taskaram = taskaram;
    }

    public float getStorage() {
        return storage;
    }

    public void setStorage(float storage) {
        this.storage = storage;
    }

    public String getCurrentversion() {
        return currentversion;
    }

    public void setCurrentversion(String currentversion) {
        this.currentversion = currentversion;
    }

    public LocalDateTime getRegistereddatetime() {
        return registereddatetime;
    }

    public void setRegistereddatetime(LocalDateTime registereddatetime) {
        this.registereddatetime = registereddatetime;
    }

    public LocalDateTime getLastactivedatetime() {
        return lastactivedatetime;
    }

    public void setLastactivedatetime(LocalDateTime lastactivedatetime) {
        this.lastactivedatetime = lastactivedatetime;
    }

    public LocalDateTime getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(LocalDateTime lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    
}
