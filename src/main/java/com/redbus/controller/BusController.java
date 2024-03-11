package com.redbus.controller;


import com.redbus.entity.Bus;
import com.redbus.payload.BusDto;
import com.redbus.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi/bus")
public class BusController {


    @Autowired
    private BusService busService;


    // http://localhost:8080/api/vi/bus
    @PostMapping
    public ResponseEntity<?> addBus(@RequestBody BusDto busDto){
        busService.addBus(busDto);
        return new ResponseEntity<>("Bus Details Added" , HttpStatus.CREATED);
    }

    // http://localhost:8080/api/vi/bus
    @GetMapping
    public ResponseEntity<List<BusDto>>getAllBusDetails(){
       List<BusDto>  dto =  busService.getAllBusDetails();
        return  new ResponseEntity<>(dto , HttpStatus.OK);
    }

    // http://localhost:8080/api/vi/bus/1
    @GetMapping("/{busId}")
    public ResponseEntity<BusDto> getDetailsByBusId(@PathVariable long busId){
        BusDto dto =  busService.getDetailsByBusId(busId);
        return  new ResponseEntity<>(dto , HttpStatus.OK);
    }

    // http://localhost:8080/api/vi/bus/1
    @DeleteMapping("/{busId}")
    public ResponseEntity<String> deleteBusByBusId(@PathVariable long busId){
        busService.deleteBusByBusId(busId);
      return new ResponseEntity<>("Record is Deleted!!" , HttpStatus.OK);
    }
}
