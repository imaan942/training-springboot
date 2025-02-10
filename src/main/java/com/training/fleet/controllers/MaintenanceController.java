package com.training.fleet.controllers;

import com.training.fleet.dto.MaintenanceDTO;
import com.training.fleet.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/maintenance")
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Welcome to Maintenance System";
    }

    @GetMapping()
    public ResponseEntity<List<MaintenanceDTO> > getAllTrucks(){
        // return truckService.getAllTrucks();
        return ResponseEntity.ok(
                maintenanceService.getAllMaintenances()
        );
    }
}
