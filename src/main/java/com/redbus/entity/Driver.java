package com.redbus.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long driverId;
    private String driverName;
    private String licenseNumber;
    private String adharNumber;
    private String address;
    private long contectNumber;
    private long alternateContactNumber;
    private String email;
}
