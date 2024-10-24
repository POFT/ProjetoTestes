package org.example.controller;

import org.example.model.Attraction;
import org.example.model.Sale;
import org.example.model.Attraction;
import org.example.repository.AttractionRepository;
import org.example.repository.SaleRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientController {

    private AttractionRepository attractionRepository;
    private SaleRepository saleRepository;

    public ClientController(AttractionRepository attractionRepository, SaleRepository saleRepository) {
        this.attractionRepository = attractionRepository;
        this.saleRepository = saleRepository;
    }

    /**
     * Print all the attractions
     *
     * @return an Attraction arraylist with the attractions
     */
    public List<Attraction> attractions() {
        return attractionRepository.getAttractionArray();
    }

    /**
     * Print adults favorite attractions
     * @return
     */
    public String favoriteAttractionForAdults() {
        ArrayList<Sale> sales = saleRepository.getSalesArray();
        ArrayList<Attraction> attractions = attractionRepository.getAttractionArray();
        Map<Integer, Integer> attractionCountMap = new HashMap<>();

        for (Sale sale : sales) {
            if (sale.getClientType().equalsIgnoreCase("adulto")) {
                int currentAttraction = sale.getAttraction();
                attractionCountMap.put(currentAttraction, attractionCountMap.getOrDefault(currentAttraction, 0) + 1);
            }
        }

        int maxSales = 0;
        int favoriteAttractionId = -1;

        for (Map.Entry<Integer, Integer> entry : attractionCountMap.entrySet()) {
            if (entry.getValue() > maxSales) {
                maxSales = entry.getValue();
                favoriteAttractionId = entry.getKey();
            }
        }

        if (favoriteAttractionId == -1) {
            return null;
        }

        for (Attraction attraction : attractions) {
            if (attraction.getId() == favoriteAttractionId) {
                return attraction.getAttractionName();
            }
        }

        return null;
    }

    /**
     * Print children favorite attractions
     * @return
     */
    public String favoriteAttractionsForChildren() {
        ArrayList<Sale> sales = saleRepository.getSalesArray();
        ArrayList<Attraction> attractions = attractionRepository.getAttractionArray();
        Map<Integer, Integer> attractionCountMap = new HashMap<>();

        for (Sale sale : sales) {
            if (sale.getClientType().equalsIgnoreCase("crianca")) {
                int currentAttraction = sale.getAttraction();
                attractionCountMap.put(currentAttraction, attractionCountMap.getOrDefault(currentAttraction, 0) + 1);
            }
        }

        int maxSales = 0;
        int favoriteAttractionId = -1;

        for (Map.Entry<Integer, Integer> entry : attractionCountMap.entrySet()) {
            if (entry.getValue() > maxSales) {
                maxSales = entry.getValue();
                favoriteAttractionId = entry.getKey();
            }
        }

        if (favoriteAttractionId == -1) {
            return null;
        }

        for (Attraction attraction : attractions) {
            if (attraction.getId() == favoriteAttractionId) {
                return attraction.getAttractionName();
            }
        }

        return null;
    }
}
