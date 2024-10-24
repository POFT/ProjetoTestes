package org.example.repository;

import org.example.model.Sale;
import org.example.util.CSVSalesReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SaleRepository {

    private ArrayList<Sale> sales;
    public static SaleRepository instance;

    /**
     * Private constructor for SaleRepository to guarantee a unique instance according Singleton pattern
     * @throws FileNotFoundException if the file cannot be found with the defined path
     */
    public SaleRepository() throws FileNotFoundException {
        sales = CSVSalesReader.readCsvFileToSalesArray("src/main/resources/Cesaeland_vendas.csv");
    }

    /**
     * Provides an unique instance to SaleRepository
     * @return SalesRepository instance
     * @throws FileNotFoundException if the file called in constructor cannot be found with the defined path
     */
    public static SaleRepository getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new SaleRepository();
        }
        return instance;
    }

    // Getter
    public ArrayList<Sale> getSalesArray() {
        return sales;
    }

}
