package org.example.repository;

import org.example.model.Cost;
import org.example.util.CSVCostsReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CostRepository {
    private ArrayList<Cost> costArrayList;

    // Constructor
    public CostRepository() throws FileNotFoundException {
        costArrayList = CSVCostsReader.readCostsFile("src/main/resources/Cesaeland_custos.csv");
    }

    // Getter
    public ArrayList<Cost> getCostArrayList() {
        return costArrayList;
    }
}
