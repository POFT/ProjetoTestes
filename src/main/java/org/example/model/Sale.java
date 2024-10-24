package org.example.model;

import java.time.YearMonth;

public class Sale {

    private int attraction;
    private YearMonth date;
    private String clientType;

    /**
     * Construcs an Sale object
     * @param attraction The name of the attraction
     * @param date The date of the sale occurrence, representing by month and year in the pattern "MM/yyyy"
     * @param clientType Type of customer to whom the sale was made
     */
    public Sale(int attraction, YearMonth date, String clientType) {
        this.attraction = attraction;
        this.date = date;
        this.clientType = clientType;
    }

    // Getters
    public int getAttraction() {
        return attraction;
    }

    public YearMonth getDate() {
        return date;
    }

    public String getClientType() {
        return clientType;
    }
}


