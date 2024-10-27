package org.example.repository;

import org.example.model.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SaleRepositoryTest {

    private SaleRepository saleRepository;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        saleRepository = new SaleRepository();
    }

    @Test
    public void testgetSalesArray() {
        ArrayList<Sale> salesArrayList = saleRepository.getSalesArray();

        // Verifica se a lista não é nula ou vazia
        assertNotNull(salesArrayList);

    }
}
