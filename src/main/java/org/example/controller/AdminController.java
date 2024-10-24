package org.example.controller;

import model.Attraction;
import model.Cost;
import model.Sale;
import org.example.model.Attraction;
import repository.AttractionRepository;
import repository.CostRepository;
import repository.SaleRepository;
import repository.UserRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdminController {

    private SaleRepository saleRepository;
    private CostRepository costRepository;
    private AttractionRepository attractionRepository;
    private UserRepository userRepository;

    public AdminController() throws FileNotFoundException {
        this.saleRepository = SaleRepository.getInstance();
        this.costRepository = new CostRepository();
        this.attractionRepository = new AttractionRepository();
        this.userRepository = new UserRepository();
    }

    /**
     * Calculates the sum of all sales
     *
     * @return the total sales value
     */
    public double totalSales() {
        double total = 0;
        Map<YearMonth, Double> salesByMonth = mapTotalSalesValueByMonth();
        for (Map.Entry<YearMonth, Double> monthSale : salesByMonth.entrySet()) {
            total += monthSale.getValue();
        }
        return total;
    }


    /**
     * Calculates the balance of the period
     *
     * @return the profit or prejudice value of the period
     */
    public double totalProfit() {
        Map<YearMonth, Double> profitByMonth = mapProfitValueByMonth();
        double totalProfit = 0;

        for (Map.Entry<YearMonth, Double> monthProfit : profitByMonth.entrySet()) {
            totalProfit += monthProfit.getValue();
        }
        return totalProfit;
    }


    /**
     * Groups sum of sales by month
     *
     * @return Map with the total value of sales by month
     */
    public Map<YearMonth, Double> mapTotalSalesValueByMonth() {
        ArrayList<YearMonth> months = getMonthsArray();
        Map<YearMonth, Double> salesByMonth = new HashMap<>();

        for (YearMonth date : months) {
            salesByMonth.put(date, getTotalMonthSalesValue(date));
        }
        return salesByMonth;
    }

    /**
     * Groups the balance by month
     *
     * @return Map with the balance value by month
     */
    public Map<YearMonth, Double> mapProfitValueByMonth() {
        ArrayList<YearMonth> months = getMonthsArray();
        Map<YearMonth, Double> profitByMonth = new HashMap<>();
        Map<YearMonth, Double> variableCost = mapTotalVariableCostValueByMonth();
        Map<YearMonth, Double> totalSales = mapTotalSalesValueByMonth();
        double fixCost = totalFixCostByMonth();

        for (YearMonth month : months) {
            Double profit = totalSales.get(month) - variableCost.get(month) - fixCost;
            profitByMonth.put(month, profit);
        }

        return profitByMonth;
    }

    /**
     * Finds the attraction that sold the most tickets by type of customer
     *
     * @return the most popular attraction by type of costumer
     */
    public Attraction mostPopularAttractionByClientType(String clientType) {
        Map<Integer, Integer> countSalesByAttraction = new HashMap<>();
        ArrayList<Sale> salesToAdult = saleRepository.getSalesArray().stream()
                .filter(s -> s.getClientType().equals(clientType.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<Integer> attractions = distinctAttractions();

        for (Integer attraction : attractions) {
            int count = (int) salesToAdult.stream().filter(s -> s.getAttraction() == attraction).count();
            countSalesByAttraction.put(attraction, count);
        }

        return getMostPopularAttraction(countSalesByAttraction);
    }

    /**
     * Finds the attraction that sold the most tickets
     *
     * @return the Attraction that sold the most tickets in general
     */
    public Attraction mostPopularAttraction() {
        Map<Integer, Integer> salesByAttraction = countSalesByAttraction();
        return getMostPopularAttraction(salesByAttraction);
    }

    /**
     * Finds the most profitable Attraction according the data
     *
     * @return the most profitable Attraction
     */
    public Attraction mostProfitableAttraction() {
        Map<Integer, Double> profitByAttraction = mapProfitByAttraction();
        Double biggestProfit = 0.0;
        int attractionId = 0;

        for (Map.Entry<Integer, Double> profit : profitByAttraction.entrySet()) {
            if (profit.getValue() > biggestProfit) {
                biggestProfit = profit.getValue();
                attractionId = profit.getKey();
            }
        }

        int finalAttractionId = attractionId;
        return attractionRepository.getAttractionArray().stream()
                .filter(a -> a.getId() == finalAttractionId)
                .findFirst()
                .get();
    }

    /**
     * Finds the less profitable Attraction according the data
     *
     * @return the less profitable Attraction
     */
    public Attraction lessProfitableAttraction() {
        Map<Integer, Double> profitByAttraction = mapProfitByAttraction();
        Double smallestProfit = profitByAttraction.get(1);
        int attractionId = 0;

        for (Map.Entry<Integer, Double> profit : profitByAttraction.entrySet()) {
            if (profit.getValue() < smallestProfit) {
                smallestProfit = profit.getValue();
                attractionId = profit.getKey();
            }
        }

        int finalAttractionId = attractionId;
        return attractionRepository.getAttractionArray().stream()
                .filter(a -> a.getId() == finalAttractionId)
                .findFirst()
                .get();
    }


    // -------------------------- AUX -----------------------------

    /**
     * Finds the existing months in Sales data
     *
     * @return ArrayList with all months existing in the Sales file
     */
    private ArrayList<YearMonth> getMonthsArray() {
        return this.saleRepository.getSalesArray().stream()
                .map(s -> s.getDate())
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Groups the id of each attraction
     *
     * @return an ArrayList with all the different attractions id
     */
    private ArrayList<Integer> distinctAttractions() {
        return this.attractionRepository.getAttractionArray()
                .stream()
                .map(a -> a.getId()).distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Groups the total number of sales by Attraction
     *
     * @return Map with the total of sales by attraction
     */
    public Map<Integer, Integer> countSalesByAttraction() {

        ArrayList<Integer> attractions = distinctAttractions();
        Map<Integer, Integer> salesByAttraction = new HashMap<>();
        ArrayList<Sale> sales = this.saleRepository.getSalesArray();

        for (Integer attraction : attractions) {
            int count = (int) sales.stream().filter(s -> s.getAttraction() == attraction).count();
            salesByAttraction.put(attraction, count);
        }

        return salesByAttraction;
    }

    /**
     * Sums the values of all sales to the informed month
     *
     * @param date the month/year to have the sales value calculated
     * @return the total value of sales on the informed month
     */
    private Double getTotalMonthSalesValue(YearMonth date) {
        Double totalMonthSales = 0.0;

        Map<Integer, Double> pricesToAdult = getAttractionsPriceToAdult();
        Map<Integer, Double> pricesToChild = getAttractionsPriceToChild();

        ArrayList<Sale> monthSales = this.saleRepository.getSalesArray()
                .stream().filter(s -> s.getDate().equals(date))
                .collect(Collectors.toCollection(ArrayList::new));

        for (Sale s : monthSales) {
            if (s.getClientType().equals("adulto")) {
                totalMonthSales += pricesToAdult.get(s.getAttraction());
            } else {
                totalMonthSales += pricesToChild.get(s.getAttraction());
            }
        }
        return totalMonthSales;
    }

    /**
     * Groups the information about adult prices for each Attraction
     *
     * @return Map with the attractions and its price to adults
     */
    private Map<Integer, Double> getAttractionsPriceToAdult() {

        Map<Integer, Double> pricesByAdult = new HashMap<>();

        ArrayList<Attraction> attractionsArray = this.attractionRepository.getAttractionArray();
        for (Attraction a : attractionsArray) {
            pricesByAdult.put(a.getId(), a.getPriceAdult());
        }
        return pricesByAdult;
    }

    /**
     * Groups the information about child prices for each Attraction
     *
     * @return Map with the attractions and its price to child
     */
    private Map<Integer, Double> getAttractionsPriceToChild() {
        Map<Integer, Double> pricesToChild = new HashMap<>();

        ArrayList<Attraction> attractionsArray = this.attractionRepository.getAttractionArray();
        for (Attraction a : attractionsArray) {
            pricesToChild.put(a.getId(), a.getPriceChild());
        }
        return pricesToChild;
    }

    /**
     * Calculates the maintenance cost by month
     *
     * @return Map with the values of the maintenance cost for month
     */
    private Map<YearMonth, Double> mapTotalVariableCostValueByMonth() {
        Double maintenanceCost;

        ArrayList<YearMonth> months = getMonthsArray();
        ArrayList<Sale> sales = this.saleRepository.getSalesArray();
        Map<Integer, Double> maintenancePriceByTicket = mapAttractionMaintenancePriceByTicket();
        Map<YearMonth, Double> maintenanceCostOfMonth = new HashMap<>();

        for (YearMonth month : months) {
            ArrayList<Sale> salesOfMonth = sales.stream().filter(s -> s.getDate().equals(month)).collect(Collectors.toCollection(ArrayList::new));
            maintenanceCost = 0.0;

            for (Sale s : salesOfMonth) {
                int attraction = s.getAttraction();
                maintenanceCost += maintenancePriceByTicket.get(attraction);
            }
            maintenanceCostOfMonth.put(month, maintenanceCost);

        }
        return maintenanceCostOfMonth;
    }

    /**
     * Calculate the fixed cost by month
     *
     * @return the sum of monthly cost of all attractions
     */
    private double totalFixCostByMonth() {
        double totalFixCostByMonth = 0;
        ArrayList<Cost> costs = this.costRepository.getCostArrayList();
        for (Cost c : costs) {
            totalFixCostByMonth += c.getCostFixMonth();
        }
        return totalFixCostByMonth;
    }

    /**
     * Groups the maintenance cost by attraction sold tickets
     *
     * @return Map with the price of maintenance for each ticket of each attraction
     */
    private Map<Integer, Double> mapAttractionMaintenancePriceByTicket() {
        Map<Integer, Double> attractionMaintenCostByTicket = new HashMap<>();
        ArrayList<Cost> cost = this.costRepository.getCostArrayList();

        for (Cost c : cost) {
            attractionMaintenCostByTicket.put(c.getIdAtraction(), c.getCostMaintenanceTicket());
        }
        return attractionMaintenCostByTicket;
    }

    /**
     * Find the Attraction with the biggest number of tickets sold
     *
     * @param soldTicketByAttraction Map with the total tickets sold by attraction
     * @return the attraction with the biggest number of sales
     */
    private Attraction getMostPopularAttraction(Map<Integer, Integer> soldTicketByAttraction) {
        int biggestValue = 0;
        int mostPopular = 0;
        for (Map.Entry<Integer, Integer> c : soldTicketByAttraction.entrySet()) {
            if (c.getValue() > biggestValue) {
                biggestValue = c.getValue();
                mostPopular = c.getKey();
            }
        }

        int finalMostPopular = mostPopular;
        Attraction mostPopularAttraction = this.attractionRepository
                .getAttractionArray()
                .stream()
                .filter(a -> a.getId() == finalMostPopular).findFirst().get();

        return mostPopularAttraction;
    }

    /**
     * Groups balance sales by Attraction
     *
     * @return Map with the sales balance by attraction
     */
    public Map<Integer, Double> mapProfitByAttraction() {
        Map<Integer, Double> profitByAttraction = new HashMap<>();

        ArrayList<Cost> costs = this.costRepository.getCostArrayList();
        ArrayList<Integer> attractionArrayList = distinctAttractions();
        Map<Integer, Double> maintenancePriceByTicketByAttraction = mapAttractionMaintenancePriceByTicket();
        Map<Integer, Integer> countSalesByAttraction = countSalesByAttraction();
        Map<Integer, Double> salesValueByAttraction = mapTotalSalesValueByAttraction();


        for (Integer attractionId : attractionArrayList) {
            Double fixedCost = costs.stream()
                    .filter(c -> c.getIdAtraction().equals(attractionId))
                    .findFirst()
                    .get()
                    .getCostFixMonth();

            Double variableCost =
                    maintenancePriceByTicketByAttraction.get(attractionId) * countSalesByAttraction.get(attractionId);

            Double profit = salesValueByAttraction.get(attractionId) - (variableCost + fixedCost);
            profitByAttraction.put(attractionId, profit);
        }

        return profitByAttraction;
    }

    /**
     * Groups sum of sales value by attraction
     *
     * @return Map with the total value of sales by attraction
     */
    private Map<Integer, Double> mapTotalSalesValueByAttraction() {
        ArrayList<Integer> attractions = distinctAttractions();
        ArrayList<Sale> sales = this.saleRepository.getSalesArray();
        Map<Integer, Double> pricesToAdult = getAttractionsPriceToAdult();
        Map<Integer, Double> pricesToChild = getAttractionsPriceToChild();

        Map<Integer, Double> salesValueByAttraction = new HashMap<>();
        Double salesValue;

        for (Integer attractionId : attractions) {
            salesValue = 0.0;
            Double adultPrice = pricesToAdult.get(attractionId);
            Double childPrice = pricesToChild.get(attractionId);

            ArrayList<Sale> salesByAttraction = sales.stream()
                    .filter(s -> s.getAttraction() == attractionId)
                    .collect(Collectors.toCollection(ArrayList::new));

            for (Sale s : salesByAttraction) {
                if (s.getClientType().equals("adulto")) {
                    salesValue += adultPrice;
                } else {
                    salesValue += childPrice;
                }
            }
            salesValueByAttraction.put(attractionId, salesValue);
        }
        return salesValueByAttraction;
    }

    /**
     * Method to find the attraction with the best price per second
     * @return the attraction with the best price per second
     */
    public Attraction bestPricePerSecond() {
        ArrayList<Attraction> attractions = attractionRepository.getAttractionArray();
        Attraction bestAttraction = null;
        double bestPricePerSecond = 1000000;

        for (Attraction attraction : attractions) {

            double priceAdultPerSecond = attraction.getPriceAdult() / attraction.getDurationSeconds();
            double priceChildPerSecond = attraction.getPriceChild() / attraction.getDurationSeconds();

            if (priceAdultPerSecond < bestPricePerSecond) {
                bestPricePerSecond = priceAdultPerSecond;
                bestAttraction = attraction;
            }
            if (priceChildPerSecond < bestPricePerSecond) {
                bestPricePerSecond = priceChildPerSecond;
                bestAttraction = attraction;
            }
        }
        return bestAttraction;
    }

    /**
     * Method to add a new access
     *
     * @return A message indicating the result of the operation
     */
    public String addNewAccess() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escolha o tipo de login (ADMIN/ENGENHEIRO): ");
        String loginType = scanner.nextLine().toUpperCase();

        if (!loginType.equals("ADMIN") && !loginType.equals("ENGENHEIRO")) {
            return "Tipo de login inválido. Por favor digite ADMIN ou ENGENHEIRO.";
        }

        System.out.print("Digite o username: ");
        String username = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();

        try {
            userRepository.addUser(loginType, username, password);
            return "Novo acesso criado com sucesso.";
        } catch (IOException e) {
            return "Erro ao criar novo acesso: " + e.getMessage();
        }
    }

}