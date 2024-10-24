package org.example.view;

import org.example.controller.AdminController;
import org.example.model.Attraction;

import java.io.FileNotFoundException;
import java.time.YearMonth;
import java.util.Map;
import java.util.Scanner;

public class AdminView {

    private AdminController adminController;

    public AdminView() throws FileNotFoundException {
        this.adminController = new AdminController();
    }

    /**
     * Method that prints/displays the admin profile main menu
     * */
    public void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n? --- Bem-Vindo ao Mundo Magico CESAELand, o que deseja fazer? --- \uD83C\uDFA2");
            System.out.println("\n1. \uD83D\uDCCA Ver o total de todas as vendas");
            System.out.println("2. \uD83D\uDCB5 Ver o total de lucro");
            System.out.println("3. \uD83D\uDCC6 Ver o total de vendas e lucro por m�s");
            System.out.println("4. \uD83D\uDC68 Ver a atra��o mais popular entre os adultos");
            System.out.println("5. \uD83D\uDC67 Ver a atra��o mais popular entre crian�as");
            System.out.println("6. \uD83C\uDFC6 Ver a atra��o mais popular");
            System.out.println("7. \uD83E\uDD11 Ver a atra��o mais lucrativa");
            System.out.println("8. \uD83D\uDCB8 Ver a atra��o menos lucrativa");
            System.out.println("9. ? Ver a atra��o com melhor pre�o/tempo");
            System.out.println("10. \uD83E\uDEAA Adicionar novo login");
            System.out.println("11. \uD83D\uDEAA Sair");
            System.out.print("\n\uD83C\uDFAF Escolha uma op��o: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    viewTotalSales();
                    break;
                case 2:
                    viewTotalProfit();
                    break;
                case 3:
                    viewSalesAndProfitByMonth();
                    break;
                case 4:
                    viewMostPopularAttractionAdults();
                    break;
                case 5:
                    viewMostPopularAttractionChildren();
                    break;
                case 6:
                    viewMostPopularAttraction();
                    break;
                case 7:
                    viewMostProfitableAttraction();
                    break;
                case 8:
                    viewLeastProfitableAttraction();
                    break;
                case 9:
                    viewBestPricePerTimeAttraction();
                    break;
                case 10:
                    addNewLogin();
                    break;
                case 11:
                    exit = true;
                    System.out.println("\n? O CESAELand espera te ver novamente em breve! \uD83D\uDC4B");
                    break;
                default:
                    System.out.println("\uD83D\uDEA8 Op��o inv�lida. Tente novamente. \uD83D\uDEA8");
                    break;
            }
        }
    }

    /**
     * Method that prints/displays total sales
     */
    private void viewTotalSales() {
        System.out.printf("\n\uD83D\uDCC8 A venda total do CESAELand s�o de: $%.2f%n",
                adminController.totalSales());
    }

    /**
     * Method that prints/displays total profits
     */
    private void viewTotalProfit() {
        System.out.printf("\n\uD83D\uDCB5 O lucro total do CESAELand foi de: $%.2f%n",
                adminController.totalProfit());
    }

    /**
     * Method that prints/shows sales and profit per month
     */
    private void viewSalesAndProfitByMonth() {
        Map<YearMonth, Double> salesByMonth = adminController.mapTotalSalesValueByMonth();
        Map<YearMonth, Double> profitByMonth = adminController.mapProfitValueByMonth();

        System.out.printf("%n\uD83D\uDCC8 \uD83D\uDCB5  As vendas e lucros do CESAELand por m�s foram de:%n");
        System.out.printf("%-10s\t%-20s\t%-10s%n", "M�s", "Vendas", "Lucro");
        System.out.println("--------------------------------------------");

        for (YearMonth month : salesByMonth.keySet()) {
            Double sales = salesByMonth.get(month);
            Double profit = profitByMonth.get(month);

            if (sales == null) sales = 0.0;
            if (profit == null) profit = 0.0;

            System.out.printf("%-20s\t$%.2f\t$%.2f%n", month.toString(), sales, profit);
        }
    }


    /**
     * Method that prints/displays the most popular attraction among adults
     */
    private void viewMostPopularAttractionAdults() {
        Attraction mostPopularAttraction = adminController.mostPopularAttractionByClientType("adulto");

        System.out.printf("\n? A atra��o mais popular do CESAELand entre os adultos �: %s, com um total de %d bilhetes vendidos.",
                mostPopularAttraction.getAttractionName(), adminController.countSalesByAttraction().get(mostPopularAttraction.getId()));
    }


    /**
     * Method that prints/displays the most popular attraction among children
     */
    private void viewMostPopularAttractionChildren() {
        Attraction mostPopularAttraction = adminController.mostPopularAttractionByClientType("crian�a");

        System.out.printf("\n? A atra��o mais popular do CESAELand entre as crian�as �: %s, com um total de %d bilhetes vendidos.",
                mostPopularAttraction.getAttractionName(), adminController.countSalesByAttraction().get(mostPopularAttraction.getId()));

    }


    /**
     * Method that prints/displays the most popular attraction
     */
    private void viewMostPopularAttraction() {
        Attraction mostPopularAttraction = adminController.mostPopularAttraction();

        System.out.printf("\n? A atra��o mais popular do CESAELand �: %s, com um total de %d bilhetes vendidos.",
                mostPopularAttraction.getAttractionName(), adminController.countSalesByAttraction().get(mostPopularAttraction.getId()));

    }


    /**
     * Method that prints/shows the most profitable attraction
     */
    private void viewMostProfitableAttraction() {
        Attraction mostProfitableAttraction = adminController.mostProfitableAttraction();

        System.out.printf("\n\uD83D\uDCB5 A atra��o mais lucrativa do CESAELand �: %s, com um lucro total de %.2f.",
                mostProfitableAttraction.getAttractionName(), adminController.mapProfitByAttraction().get(mostProfitableAttraction.getId()));

    }


    /**
     * Method that prints/shows the least profitable attraction
     */
    private void viewLeastProfitableAttraction() {
        Attraction lessProfitableAttraction = adminController.lessProfitableAttraction();

        System.out.printf("\n\uD83D\uDCB8 A atra��o menos lucrativa do CESAELand �: %s, com um lucro total de %.2f.",
                lessProfitableAttraction.getAttractionName(), adminController.mapProfitByAttraction().get(lessProfitableAttraction.getId()));

    }


    /**
     * Method that prints/shows the best attraction by price/time
     */
    private void viewBestPricePerTimeAttraction() {
        Attraction bestPriceAttraction = adminController.bestPricePerSecond();

        System.out.printf("\n? A atra��o do CESAELand com melhor pre�o/tempo �: %s, com pre�o para adultos de %.2f e pre�o para crian�as de %.2f. Dura��o: %d segundos.",
                bestPriceAttraction.getAttractionName(),
                bestPriceAttraction.getPriceAdult(),
                bestPriceAttraction.getPriceChild(),
                bestPriceAttraction.getDurationSeconds());
    }


    /**
     * Method that prints/shows the function to add new user register
     */
    private void addNewLogin() {

        String message = adminController.addNewAccess();

        System.out.println("\n?\uD83D\uDE4B\uD83C\uDFFB\u200D?\uFE0F Vamos adicionar um novo usu�rio ao mundo m�gico CESAELand:");
        System.out.println(message);
    }


}
