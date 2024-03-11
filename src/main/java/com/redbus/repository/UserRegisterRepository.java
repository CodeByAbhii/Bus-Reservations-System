package com.redbus.repository;

import com.redbus.entity.UserRegister;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegisterRepository extends JpaRepository<UserRegister , Long> {
}
