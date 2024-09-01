package com.emerging5.omsapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Trigger {

    @Id
    @SequenceGenerator(
        name="trigger_id_sequence",
        sequenceName = "trigger_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "trigger_id_sequence"
    )
    private Long id;
    private String name;
    private String observePath;
    private String cronString;
    private boolean active;
    private LocalDateTime createddatetime;
    private LocalDateTime modifiedatetime;
    private LocalDateTime lastrundatetime;

    @Transient
    private String message;
    @Transient
    private boolean txnstatus;
    
    public Trigger() {
    }
    
    public Trigger(Long id, String name, String observePath, String cronString) {
        this.id = id;
        this.name = name;
        this.observePath = observePath;
        this.cronString = cronString;
        this.active = true;
    }

    public Trigger(String message, boolean status){
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
    public String getObservePath() {
        return observePath;
    }
    public void setObservePath(String observePath) {
        this.observePath = observePath;
    }
    public String getCronString() {
        return cronString;
    }
    public void setCronString(String cronString) {
        this.cronString = cronString;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
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
