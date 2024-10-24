package org.example.repository;

import org.example.model.Attraction;
import org.example.util.CSVAttractionsReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AttractionRepository {
    private ArrayList<Attraction> attractionArray;

    // Constructor
    public AttractionRepository() throws FileNotFoundException {
        attractionArray = CSVAttractionsReader.readAttractionsFile("src/main/resources/Cesaeland_atracoes.csv");
    }

    // Getter
    public ArrayList<Attraction> getAttractionArray() {
        return attractionArray;
    }
}
