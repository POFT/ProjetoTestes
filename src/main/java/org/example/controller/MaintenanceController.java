package org.example.controller;

import model.Attraction;
import model.Sale;
import org.example.model.Attraction;
import repository.AttractionRepository;
import repository.CostRepository;
import repository.SaleRepository;

import java.io.FileNotFoundException;
import java.util.*;

import static java.util.Collections.sort;

public class MaintenanceController {


    /**
     * Find the attractions that are closest to require maintenance. It is based on the number of tickets sold.
     *
     * @return the attractions that are closest to need a maintenance
     * @throws FileNotFoundException any data cannot be found.
     */
    public List<Map.Entry<Attraction, Integer>> consultNextRevisions() throws FileNotFoundException {
        ArrayList<Attraction> attractions = new AttractionRepository().getAttractionArray();
        ArrayList<Sale> sales = SaleRepository.getInstance().getSalesArray();

        Map<Attraction, Integer> attractionsSold = new HashMap<>();

        for (Attraction attraction : attractions) {
            int totalTicketsSold = 0;

            for (Sale sale : sales) {
                if (sale.getAttraction() == attraction.getId()) {
                    totalTicketsSold++;
                }
            }

            int ticketsToNextRevision = 50 - (totalTicketsSold % 50);
            attractionsSold.put(attraction, ticketsToNextRevision);
        }

        List<Map.Entry<Attraction, Integer>> sortedAttractions = new ArrayList<>(attractionsSold.entrySet());
        sortedAttractions.sort(Comparator.comparingInt(Map.Entry::getValue));

        return sortedAttractions.subList(0, Math.min(3, sortedAttractions.size()));
    }

    /**
     *
     * @return An ArrayList of Attraction sorted by the number of tickets
     *         sold since the last revision in descending order.
     * @throws FileNotFoundException any data cannot be found.
     */
    public ArrayList<Attraction> consultRevisionHistory() throws FileNotFoundException {
        ArrayList<Attraction> attractionRepository = new AttractionRepository().getAttractionArray();
        ArrayList<Sale> saleRepository = SaleRepository.getInstance().getSalesArray();

        Map<Attraction, Integer> ticketsSinceLastRevisionMap = new HashMap<>();

        for (Attraction attraction : attractionRepository) {
            int totalTicketsSold = 0;

            for (Sale sale : saleRepository) {
                if (sale.getAttraction() == attraction.getId()) {
                    totalTicketsSold++;
                }
            }

            int ticketsSinceLastRevision = totalTicketsSold % 50;
            ticketsSinceLastRevisionMap.put(attraction, ticketsSinceLastRevision);
        }

        ArrayList<Attraction> revisionHistories = new ArrayList<>(attractionRepository);
        revisionHistories.sort((a1, a2) -> ticketsSinceLastRevisionMap.get(a2)
                .compareTo(ticketsSinceLastRevisionMap.get(a1)));

        return revisionHistories;
    }
}
