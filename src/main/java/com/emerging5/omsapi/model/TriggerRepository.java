package com.emerging5.omsapi.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TriggerRepository extends JpaRepository<Trigger, Long>{
    List<Trigger> findByName(String name);
}
