package com.redbus.repository;

import com.redbus.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus , Long> {
}
