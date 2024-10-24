package org.example.view;

import org.example.controller.ClientController;
import org.example.model.Attraction;
import org.example.repository.AttractionRepository;
import org.example.repository.SaleRepository;

import java.util.List;
import java.util.Scanner;

public class ClientView {

    private ClientController clientController;

    public ClientView(AttractionRepository attractionRepository, SaleRepository saleRepository) {
        this.clientController = new ClientController(attractionRepository, saleRepository);
    }

    /**
     * Method that prints/displays the client profile main menu
     * */
    public void showClientMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n✨ --- Bem-Vindo ao Mundo Magico CESAELand, o que deseja fazer? --- \uD83C\uDFA2");
            System.out.println("\n1. \uD83E\uDD29 Ver Atrações Disponíveis");
            System.out.println("2. ⭐ Ver Atrações Favoritas");
            System.out.println("3. \uD83D\uDEAA Sair");
            System.out.print("\n\uD83C\uDFAF Escolha uma opção: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    viewAvailableAttractions();
                    break;
                case 2:
                    viewFavoriteAttractions();
                    break;
                case 3:
                    exit = true;
                    System.out.println("✨ O CESAELand espera te ver novamente em breve! \uD83D\uDC4B");
                    break;
                default:
                    System.out.println("\uD83D\uDEA8 Opção inválida. Tente novamente. \uD83D\uDEA8");
                    break;
            }
        }
    }

    /**
     * Method that prints/displays available attractions
     */
    private void viewAvailableAttractions() {
        List<Attraction> attractions = clientController.attractions();

        System.out.printf("%n\uD83C\uDFB6 Atrações Disponíveis:\n");
        System.out.printf("%-40s\t%-20s\t%-20s\t%-10s%n", "Atração", "Preço Adulto", "Preço Criança", "Duração");
        System.out.println("------------------------------------------------------------------------------------------------------------");

        for (Attraction attraction : attractions) {
            String duration = String.format("%d:%02d",
                    attraction.getDurationSeconds() / 60,
                    attraction.getDurationSeconds() % 60);
            System.out.printf("%-40s\t$%-19.2f\t$%-19.2f\t%-10s%n",
                    attraction.getAttractionName(),
                    attraction.getPriceAdult(),
                    attraction.getPriceChild(),
                    duration);
        }


    }


    /**
     * Method that prints/displays favorite attractions
     */
    private void viewFavoriteAttractions() {
        String favoriteAdult = clientController.favoriteAttractionForAdults();
        String favoriteChild = clientController.favoriteAttractionsForChildren();

        System.out.println("\n\uD83D\uDE4B\u200D♂\uFE0F\uD83C\uDF1F A atração favorita dos adultos é: " + favoriteAdult );
        System.out.println("\n\uD83D\uDC66\uFE0F\uD83C\uDF1F A atração favorita das crianças é: " + favoriteChild );
    }

}
