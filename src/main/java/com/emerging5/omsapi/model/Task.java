package com.emerging5.omsapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long dependsonTaskid;
    private boolean status;
    private String msg;

    public Long getId() {
        return id;
    }
    
    public Task(Long id, String name, Long dependsonTaskid, boolean status, String msg) {
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