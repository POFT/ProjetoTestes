package org.example.repository;

import org.example.model.Cost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CostRepositoryTest {

    private CostRepository costRepository;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        costRepository = new CostRepository();
    }

    @Test
    public void testgetCostArrayList() {
        ArrayList<Cost> costArrayList = costRepository.getCostArrayList();

        // Verifica se a lista não é nula ou vazia
        assertNotNull(costArrayList);
    }
}
