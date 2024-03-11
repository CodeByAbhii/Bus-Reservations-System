package com.redbus.service;


import com.redbus.entity.Bus;
import com.redbus.entity.Route;
import com.redbus.entity.SubRoute;
import com.redbus.exception.ResourceNotFoundException;
import com.redbus.payload.BusDto;
import com.redbus.payload.SubRouteDto;
import com.redbus.repository.BusRepository;
import com.redbus.repository.DriverRepository;
import com.redbus.repository.RouteRepository;
import com.redbus.repository.SubRouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {


    @Autowired
    private BusRepository busRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private SubRouteRepository subRouteRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ModelMapper modelMapper;

    // add Bus Details in Database !!
    public void addBus(BusDto busDto){

        // Create Route Entity
        Route route = new Route();
        route.setFromLocation(busDto.getRoute().getFromLocation());
        route.setToLocation(busDto.getRoute().getToLocation());
        route.setFromDate(busDto.getRoute().getFromDate());
        route.setToDate(busDto.getRoute().getToDate());
        route.setTotalDuration(busDto.getRoute().getTotalDuration());
        route.setFromTime(busDto.getRoute().getFromTime());
        route.setToTime(busDto.getRoute().getToTime());

        Route savedRoute = routeRepository.save(route);


        // Create Bus Entity
        Bus bus = new Bus();
        bus.setBusNumber(busDto.getBusNumber());
        bus.setBusType(busDto.getBusType());
        bus.setPrice(busDto.getPrice());
        bus.setTotalSeats(busDto.getTotalSeats());
        bus.setAvailableSeats(busDto.getAvailableSeats());
        bus.setReview(busDto.getReview());

        // Associate Bus with Route
        bus.setRoute(route);

        // save Bus Entity
        Bus savedBus = busRepository.save(bus);

        Route updateRoute = routeRepository.findById(savedRoute.getRouteId()).get();
        updateRoute.setBus(savedBus);

        if(busDto.getSubRoute()!= null && !busDto.getSubRoute().isEmpty()){
            for(SubRouteDto subRouteDto : busDto.getSubRoute()){
                SubRoute subRoute = getSubRoute(subRouteDto, route);

                // save SubRoute in Database
                subRouteRepository.save(subRoute);
            }
        }
    }

    private static SubRoute getSubRoute(SubRouteDto subRouteDto, Route route) {
        SubRoute subRoute = new SubRoute();
        subRoute.setFromLocation(subRouteDto.getFromLocation());
        subRoute.setToLocation(subRouteDto.getToLocation());
        subRoute.setFromDate(subRouteDto.getFromDate());
        subRoute.setToDate(subRouteDto.getToDate());
        subRoute.setTotalDuration(subRouteDto.getTotalDuration());
        subRoute.setFromTime(subRouteDto.getFromTime());
        subRoute.setToTime(subRouteDto.getToTime());

        // Associate SubRoute with Route
        subRoute.setRoute(route);
        return subRoute;
    }

    // get All Bus Details By BusId
    public List<BusDto> getAllBusDetails() {
        List<Bus> getAllBus = busRepository.findAll();
        List<BusDto> collect = getAllBus.stream().map(bus -> mapToDto(bus)).collect(Collectors.toList());
        return collect;
    }

     // get Bus Details By BusId
    public BusDto getDetailsByBusId(long busId) {
        Bus bus = busRepository.findById(busId).orElseThrow(
                ()-> new ResourceNotFoundException("Bus Details Not Found with this BusId 😡🤬👎:" +busId)
        );
        BusDto dto = mapToDto(bus);
        return dto;

    }

    // create Methods Bus obj converted into BusDto object
    public BusDto mapToDto(Bus bus) {
       BusDto dto = modelMapper.map(bus, BusDto.class);
        return dto;
    }

    // create Methods BusDto obj converted into Bus object
    public Bus mapToEntity(BusDto busDto) {
        Bus bus = modelMapper.map(busDto, Bus.class);
        return bus;
    }


    public void deleteBusByBusId(long busId) {
        Bus bus = busRepository.findById(busId).orElseThrow(
                ()-> new ResourceNotFoundException("Bus Details Not Found with this BusId 😡🤬👎:" +busId)
        );
     busRepository.deleteById(busId);
    }
}
