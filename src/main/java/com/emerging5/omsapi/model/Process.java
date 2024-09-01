package com.emerging5.omsapi.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Process {
    @Id
    @SequenceGenerator(
        name="process_id_sequence",
        sequenceName = "process_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "process_id_sequence"
    )
    private Long id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Trigger trigger;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="processid",referencedColumnName = "id")
    private List<Task> tasks; 
    private boolean status;
    private boolean active;
    private String msg;
    private LocalDateTime createddatetime;
    private LocalDateTime modifiedatetime;
    private LocalDateTime lastrundatetime;
    private int numruns;
    private int numsuccess;
    private int runtimeminutes;

    @Transient
    private String message;
    @Transient
    private boolean txnstatus;

    public Process() {
    }

    public Process(Long id, String name, Trigger trigger, List<Task> tasks, boolean status, String msg) {
        this.id = id;
        this.name = name;
        this.trigger = trigger;
        this.tasks = tasks;
        this.status = status;
        this.msg = msg;
    }

    public Process(String message, boolean status){
        this.message = message;
        this.txnstatus = status;
    }
    
    public Long getId() {
        return id;
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
    public Trigger getTrigger() {
        return trigger;
    }
    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public LocalDateTime getCreateddatetime() {
        return createddatetime;
    }
    public void setCreateddatetime(LocalDateTime createddatetime) {
        this.createddatetime = createddatetime;
    }
    public LocalDateTime getModifiedatetime() {
        return modifiedatetime;
    }
    public void setModifiedatetime(LocalDateTime modifiedatetime) {
        this.modifiedatetime = modifiedatetime;
    }
    public LocalDateTime getLastrundatetime() {
        return lastrundatetime;
    }
    public void setLastrundatetime(LocalDateTime lastrundatetime) {
        this.lastrundatetime = lastrundatetime;
    }
    public int getNumruns() {
        return numruns;
    }
    public void setNumruns(int numruns) {
        this.numruns = numruns;
    }
    public int getNumsuccess() {
        return numsuccess;
    }
    public void setNumsuccess(int numsuccess) {
        this.numsuccess = numsuccess;
    }
    public int getRuntimeminutes() {
        return runtimeminutes;
    }
    public void setRuntimeminutes(int runtimeminutes) {
        this.runtimeminutes = runtimeminutes;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
}
