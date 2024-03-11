package com.redbus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long busId;

    @Column(name = "bus_number" , unique = true)
    private String busNumber;
    private String busType;
    private double price;
    private int totalSeats;
    private int availableSeats;
    private String review;

    @OneToOne(mappedBy = "bus")
    private Route route;

//    @OneToOne
//    @JoinColumn(name = "driver_id")
//    private Driver driver;
}
