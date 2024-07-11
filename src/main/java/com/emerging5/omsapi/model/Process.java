package com.emerging5.omsapi.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private Trigger trigger;
    
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Task> tasks;
    private boolean status;
    private String msg;

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

}
