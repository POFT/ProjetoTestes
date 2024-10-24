package org.example.util;

import org.example.model.Sale;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVSalesReader {

    /**
     * Read a CSV file with the sales register and convert into an ArrayList of Sale object
     * @param filePath The path of the CVS file
     * @return An ArrayList of Sale object
     * @throws FileNotFoundException if the file cannot be found with the passed path
     */
    public static ArrayList<Sale> readCsvFileToSalesArray(String filePath) throws FileNotFoundException {

        ArrayList<Sale> sales = new ArrayList<>();
        File salesFile = new File(filePath);
        Scanner scanner = new Scanner(salesFile);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            String saleOccurence = scanner.nextLine();
            String[] saleOccurenceData = saleOccurence.split(";");

            int attraction = Integer.parseInt(saleOccurenceData[0]);
            YearMonth date = YearMonth.parse(saleOccurenceData[1], formatter);
            String clientType = saleOccurenceData[2];

            sales.add(new Sale(attraction, date, clientType));
        }
        return sales;
    }

}
