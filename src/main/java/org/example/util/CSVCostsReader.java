package org.example.util;

import org.example.model.Cost;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVCostsReader {


    /**
     *
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList<Cost> readCostsFile(String filePath) throws FileNotFoundException {
        ArrayList<Cost> maintenanceArray = new ArrayList<>();

        File maintenanceFile = new File(filePath);
        Scanner scanner = new Scanner(maintenanceFile);

        String line = scanner.nextLine();

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            String[] split = line.split(";");

            int attractionId = Integer.parseInt(split[0]);
            double costPerTicket = Double.parseDouble(split[1]);
            double fixedMonthlyCost = Double.parseDouble(split[2]);

            Cost maintenance = new Cost(attractionId, costPerTicket, fixedMonthlyCost);
            maintenanceArray.add(maintenance);
        }

        return maintenanceArray;
    }

}