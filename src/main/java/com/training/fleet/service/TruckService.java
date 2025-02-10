package com.training.fleet.service;

import com.training.fleet.dto.TruckDTO;
import com.training.fleet.entity.Truck;
import com.training.fleet.exceptions.TruckNotFoundException;
import com.training.fleet.repository.TruckRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class TruckService {

    @Autowired
    private TruckRepository truckRepository;

//    public TruckService(TruckRepository truckRepository){
//        this.truckRepository = truckRepository;
//    }

    public List<TruckDTO> getAllTrucks(){
        return truckRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //Get the Truck By ID
    public TruckDTO getTruckById(Long id){
        Truck truck =  truckRepository.findById(id).orElseThrow(
                () -> new TruckNotFoundException("Truck not found with ID: " + id)
        );

        return mapToDTO(truck);
    }
    //Create Truck
    public TruckDTO createTruck(TruckDTO truckDTO){
        Truck truck = new Truck();
        truck.setModel(truckDTO.getModel());
        truck.setStatus(Truck.Status.valueOf(truckDTO.getStatus().toUpperCase()));
        truck.setDetails(truckDTO.getDetails());

        Truck saveTruck = truckRepository.save(truck);

        return mapToDTO(saveTruck);
    }

    //Update Truck
    public TruckDTO updateTruck(Long id, TruckDTO truckDTO){

        Truck truck =  truckRepository.findById(id).orElseThrow(
                () -> new TruckNotFoundException("Truck not found with ID: " + id));

        // Truck truck = new Truck();
        truck.setModel(truckDTO.getModel());
        truck.setStatus(Truck.Status.valueOf(truckDTO.getStatus().toUpperCase()));
        truck.setDetails(truckDTO.getDetails());

        Truck updatedTruck = truckRepository.save(truck);

        return mapToDTO(updatedTruck);
    }


    //Delete Truck
    public void deleteTruck(Long id){
        if(!truckRepository.existsById(id)){
            throw new TruckNotFoundException("Truck not found with ID: " + id);
        }
        truckRepository.deleteById(id);
    }

    private  TruckDTO mapToDTO(Truck truck){
        return new TruckDTO(
                truck.getId(),
                truck.getModel(),
                truck.getStatus().name().toUpperCase(),
                truck.getDetails()
        );
    }

    public Page<TruckDTO> getTrucks(Pageable pageable) {
        // Fetch the page of trucks from the repository
        Page<Truck> trucksPage = truckRepository.findAll(pageable);

        // Convert each Truck entity to TruckDTO using mapToDTO method
        Page<TruckDTO> truckDTOPage = trucksPage.map(this::mapToDTO);

        return truckDTOPage;
    }
//    public getMaintenancebyId()
}