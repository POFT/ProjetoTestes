package org.example.util;

import org.example.model.Attraction;
import org.example.model.Sale;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.YearMonth;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVSalesReaderTest {

    @Test
    public void testReadSalesFile_FileNok() {
        String invalidPath = "invalid/file/path.csv";

        assertThrows(FileNotFoundException.class, () -> {
            CSVSalesReader.readCsvFileToSalesArray(invalidPath);
        });
    }

    @Test
    public void testReadSalesFile_FileOk() throws FileNotFoundException {
        String path = "src/test/resources/Cesaeland_vendas.csv";
        ArrayList<Sale> sales = CSVSalesReader.readCsvFileToSalesArray(path);

        int expectedSales = 6;
        int resultSales = sales.size();
        assertEquals(expectedSales,resultSales);

        Sale firstline = sales.get(0);
        Sale secondline = sales.get(1);
        Sale thirdline = sales.get(2);
        Sale fourthline = sales.get(3);
        Sale fifthline = sales.get(4);
        Sale sixthline = sales.get(5);

        assertEquals(1, firstline.getAttraction());
        assertEquals(YearMonth.of(2024, 6), firstline.getDate());
        assertEquals("adulto", firstline.getClientType());

        assertEquals(2, secondline.getAttraction());
        assertEquals(YearMonth.of(2024, 6), secondline.getDate());
        assertEquals("adulto", secondline.getClientType());

        assertEquals(3, thirdline.getAttraction());
        assertEquals(YearMonth.of(2024, 6), thirdline.getDate());
        assertEquals("crianca", thirdline.getClientType());

        assertEquals(1, fourthline.getAttraction());
        assertEquals(YearMonth.of(2024, 8), fourthline.getDate());
        assertEquals("adulto", fourthline.getClientType());

        assertEquals(2, fifthline.getAttraction());
        assertEquals(YearMonth.of(2024, 8), fifthline.getDate());
        assertEquals("crianca", fifthline.getClientType());

        assertEquals(3, sixthline.getAttraction());
        assertEquals(YearMonth.of(2024, 8), sixthline.getDate());
        assertEquals("adulto", sixthline.getClientType());

    }
}
