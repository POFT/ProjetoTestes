package org.example.controller;

import org.example.model.Attraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceControllerTest {
    private MaintenanceController maintenanceController;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        maintenanceController = new MaintenanceController();
    }

    /**
     * Testar se devolve as próximas 3 revisões,
     *
     * @throws FileNotFoundException
     */
    @Test
    void consultNextRevisions() throws FileNotFoundException {
        List<Map.Entry<Attraction, Integer>> nextRevisions = maintenanceController.consultNextRevisions();

        assertEquals(3, nextRevisions.size());
    }


    /**
     * Testar se devolve as ultimas 3 revisões,
     *
     * @throws FileNotFoundException
     */
    @Test
    void consultRevisionHistory() throws FileNotFoundException {
        List<Attraction> revisionHistory = maintenanceController.consultRevisionHistory();

        assertEquals(3, revisionHistory.size());
    }
}