package org.example.util;

import org.example.model.Attraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVAttractionsReader {


    public static ArrayList<Attraction> readAttractionsFile(String filePath) throws FileNotFoundException {
        // Store all the Attraction objects in the ArrayList
        ArrayList<Attraction> attractionsArray = new ArrayList<>();

        // Open the file from the given file path
        File attractionsFile = new File(filePath);
        Scanner scanner = new Scanner(attractionsFile);

        // Skip the header liner in the CSV File
        String line = scanner.nextLine();

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            // Split the line by ";"
            String[] split = line.split(";");

            // Parse the values from the split array
            int id = Integer.parseInt(split[0]);
            String attactionName = split[1];
            double priceAdult = Double.parseDouble(split[2]);
            double priceChild = Double.parseDouble(split[3]);
            int durationSeconds = Integer.parseInt(split[4]);

            // Create a new Attraction object with the parsed values and add it to the ArrayList
            Attraction attraction = new Attraction(id, attactionName, priceAdult, priceChild, durationSeconds);
            attractionsArray.add(attraction);
        }

        return attractionsArray;
    }


}
