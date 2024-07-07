package com.emerging5.omsapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

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
    
    public Trigger() {
    }
    
    public Trigger(Long id, String name, String observePath, String cronString) {
        this.id = id;
        this.name = name;
        this.observePath = observePath;
        this.cronString = cronString;
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

}
