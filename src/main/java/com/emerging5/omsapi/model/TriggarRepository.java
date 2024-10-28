package com.emerging5.omsapi.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriggarRepository extends JpaRepository<Triggar, Long>{
    List<Triggar> findByName(String name);
}
