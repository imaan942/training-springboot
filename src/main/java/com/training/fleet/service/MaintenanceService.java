package com.training.fleet.service;

import com.training.fleet.dto.MaintenanceDTO;
import com.training.fleet.dto.TruckDTO;
import com.training.fleet.entity.Maintenance;
import com.training.fleet.entity.Truck;
import com.training.fleet.exceptions.TruckNotFoundException;
import com.training.fleet.repository.MaintenanceRepository;
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
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    public List<MaintenanceDTO> getAllMaintenances(){
        return maintenanceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private MaintenanceDTO mapToDTO(Maintenance maintenance){
        return new MaintenanceDTO(
                maintenance.getId(),
                maintenance.getTruckId(),
                maintenance.getServiceDate(),
                maintenance.getServiceType(),
                maintenance.getDetails()
        );
    }


}
