package org.example.repository;

import org.example.model.Attraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AttractionRepositoryTest {

    private AttractionRepository attractionRepository;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        attractionRepository = new AttractionRepository();
    }


    @Test
    public void testGetAttractionArray() {
        ArrayList<Attraction> attractions = attractionRepository.getAttractionArray();

        // Verifica se a lista não é nula ou vazia
        assertNotNull(attractions);
    }
}
