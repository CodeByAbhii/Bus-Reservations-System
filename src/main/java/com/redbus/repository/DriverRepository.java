package com.redbus.repository;

import com.redbus.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver , Long> {
}
