package org.example.model;

public class Cost {
    private Integer idAtraction;
    private Double costMaintenanceTicket;
    private Double costFixMonth;

    // Constructor
    public Cost(Integer idAtraction, Double costMaintenanceTicket, Double costFixMonth) {
        this.idAtraction = idAtraction;
        this.costMaintenanceTicket = costMaintenanceTicket;
        this.costFixMonth = costFixMonth;
    }

    // Getters
    public Integer getIdAtraction() {
        return idAtraction;
    }

    public Double getCostMaintenanceTicket() {
        return costMaintenanceTicket;
    }

    public Double getCostFixMonth() {
        return costFixMonth;
    }

}
