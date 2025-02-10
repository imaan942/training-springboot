package com.training.fleet.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class MaintenanceDTO {
    private Long id;
    private Long truckId;
    private Date serviceDate;
    private String serviceType;
    private String details;

    public MaintenanceDTO(Long id, Long truckId, Date serviceDate, String serviceType, String details) {
        this.id = id;
        this.truckId = truckId;
        this.serviceDate = serviceDate;
        this.serviceType = serviceType;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTruckId() {
        return truckId;
    }

    public void setTruckId(Long truckId) {
        this.truckId = truckId;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
