package org.example.util;

import org.example.model.Cost;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVCostsReaderTest {

    @Test
    public void testReadCostsFile_FileNok() {
        String invalidPath = "invalid/file/path.csv";

        assertThrows(FileNotFoundException.class, () -> {
            CSVAttractionsReader.readAttractionsFile(invalidPath);
        });
    }

    @Test
    public void testReadCostsFile_FileOk() throws FileNotFoundException {
        String path = "src/main/resources/Cesaeland_custos.csv";
        ArrayList<Cost> costs = CSVCostsReader.readCostsFile(path);

        int expectedCosts = 10;
        int resultCosts = costs.size();
        assertEquals(expectedCosts,resultCosts);

        Cost firstline = costs.get(0);
        Cost secondline = costs.get(1);
        Cost thirdline = costs.get(2);
        Cost fourthline = costs.get(3);
        Cost fifthline = costs.get(4);
        Cost sixthline = costs.get(5);
        Cost seventhline = costs.get(6);
        Cost eighthline = costs.get(7);
        Cost ninthline = costs.get(8);
        Cost tenthline = costs.get(9);

        assertEquals(1, firstline.getIdAtraction());
        assertEquals(2.5, firstline.getCostMaintenanceTicket());
        assertEquals(700, firstline.getCostFixMonth());

        assertEquals(2, secondline.getIdAtraction());
        assertEquals(0.2, secondline.getCostMaintenanceTicket());
        assertEquals(150, secondline.getCostFixMonth());

        assertEquals(3, thirdline.getIdAtraction());
        assertEquals(0.3, thirdline.getCostMaintenanceTicket());
        assertEquals(150, thirdline.getCostFixMonth());

        assertEquals(4, fourthline.getIdAtraction());
        assertEquals(0.5, fourthline.getCostMaintenanceTicket());
        assertEquals(500, fourthline.getCostFixMonth());

        assertEquals(5, fifthline.getIdAtraction());
        assertEquals(0.5, fifthline.getCostMaintenanceTicket());
        assertEquals(200, fifthline.getCostFixMonth());

        assertEquals(6, sixthline.getIdAtraction());
        assertEquals(0.1, sixthline.getCostMaintenanceTicket());
        assertEquals(500, sixthline.getCostFixMonth());

        assertEquals(7, seventhline.getIdAtraction());
        assertEquals(2, seventhline.getCostMaintenanceTicket());
        assertEquals(550, seventhline.getCostFixMonth());

        assertEquals(8, eighthline.getIdAtraction());
        assertEquals(0.1, eighthline.getCostMaintenanceTicket());
        assertEquals(50, eighthline.getCostFixMonth());

        assertEquals(9, ninthline.getIdAtraction());
        assertEquals(3, ninthline.getCostMaintenanceTicket());
        assertEquals(640, ninthline.getCostFixMonth());

        assertEquals(10, tenthline.getIdAtraction());
        assertEquals(0.1, tenthline.getCostMaintenanceTicket());
        assertEquals(100, tenthline.getCostFixMonth());
    }
}
