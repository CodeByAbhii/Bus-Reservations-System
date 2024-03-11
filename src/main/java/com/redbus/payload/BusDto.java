package com.redbus.payload;

import com.redbus.entity.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {

    private long BusId;
    private String busNumber;
    private String busType;
    private double price;
    private int totalSeats;
    private int availableSeats;
    private String review;
    private RouteDto route;
    private List<SubRouteDto> subRoute;
//    private Driver driver;
}
