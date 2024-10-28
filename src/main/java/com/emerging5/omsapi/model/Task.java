package com.emerging5.omsapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Task {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="agentid")
    private Agent agent;
    @ManyToOne
    @JoinColumn(name="processid")
    private Process process;
    private Long dependsonTaskid;
    private int status;
    private String msg;
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL)
    private Triggar trigger;
    private LocalDateTime createddatetime;
    private LocalDateTime modifieddatetime;
    private LocalDateTime lastrundatetime;

    @Transient
    private String message;
    @Transient
    private boolean txnstatus;

    public Long getId() {
        return id;
    }
    
    public Task(){

    }
    public Task(Long id, String name, Long dependsonTaskid, int status, String msg) {
        this.id = id;
        this.name = name;
        this.dependsonTaskid = dependsonTaskid;
        this.status = status;
        this.msg = msg;
    }

    public Task(String message, boolean status){
        this.message = message;
        this.txnstatus = status;
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
    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    public void setProcess(Process process) {
        this.process = process;
    }
    public LocalDateTime getCreateddatetime() {
        return createddatetime;
    }

    public void setCreateddatetime(LocalDateTime createddatetime) {
        this.createddatetime = createddatetime;
    }

    public LocalDateTime getModifieddatetime() {
        return modifieddatetime;
    }

    public void setModifieddatetime(LocalDateTime modifieddatetime) {
        this.modifieddatetime = modifieddatetime;
    }

    public LocalDateTime getLastrundatetime() {
        return lastrundatetime;
    }

    public void setLastrundatetime(LocalDateTime lastrundatetime) {
        this.lastrundatetime = lastrundatetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isTxnstatus() {
        return txnstatus;
    }

    public void setTxnstatus(boolean txnstatus) {
        this.txnstatus = txnstatus;
    }

    public Triggar getTrigger() {
        return trigger;
    }

    public void setTrigger(Triggar trigger) {
        this.trigger = trigger;
    }   
    
    
}