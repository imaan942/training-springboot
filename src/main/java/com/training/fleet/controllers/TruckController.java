package com.training.fleet.controllers;

import com.training.fleet.dto.TruckDTO;
import com.training.fleet.service.TruckService;
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
@RequestMapping("/api/trucks")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @GetMapping("/hello")
    public String sayHello(){
        return "Welcome to Fleet System";
    }

    @GetMapping
    public ResponseEntity<List<TruckDTO> > getAllTrucks(){
        // return truckService.getAllTrucks();
        return ResponseEntity.ok(
                truckService.getAllTrucks()
        );
    }

//    @GetMapping("/{id}/maintenance")
//    public ResponseEntity<List<TruckDTO> > getAllTrucks(){
//        // return truckService.getAllTrucks();
//        return ResponseEntity.ok(
//                truckService.getMaintenancebyId()
//        );
//    }

    //Create Truck
    @PostMapping
    public ResponseEntity<TruckDTO> createTruck(@RequestBody TruckDTO truckDTO){

        TruckDTO createdTruck = truckService.createTruck(truckDTO);

        return ResponseEntity.ok(createdTruck);

    }
    //Get truck by id
    @GetMapping("/{id}")
    public ResponseEntity<TruckDTO> getTruckById(@PathVariable Long id){
        TruckDTO truck = truckService.getTruckById(id);

        return ResponseEntity.ok(truck);
    }


    //Update Truck
    @PutMapping("/{id}")
    public ResponseEntity<TruckDTO> updateTruck(
            @PathVariable Long id,
            @RequestBody TruckDTO truckDTO
    ){
        TruckDTO updateTruck = truckService.updateTruck(id, truckDTO);
        return ResponseEntity.ok(updateTruck);
    }

    //Delete Truck
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTruck(
            @PathVariable Long id
    ) {
        truckService.deleteTruck(id);
        return ResponseEntity.ok("Truck Deleted Successfully");
    }

    @GetMapping("/paginated")
    public ResponseEntity<Object> getPaginatedTrucks(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue= "id, asc") String[] sort
    ){
        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        Page<TruckDTO> paginatedTrucks = truckService.getTrucks(pageable);
        return ResponseEntity.ok(paginatedTrucks);
    }

}

