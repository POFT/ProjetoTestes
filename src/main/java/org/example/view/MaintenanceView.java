package org.example.view;

import org.example.controller.MaintenanceController;
import org.example.model.Attraction;
import org.example.model.Sale;
import org.example.repository.SaleRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MaintenanceView {

    private MaintenanceController maintenanceController;

    public MaintenanceView() {
        this.maintenanceController = new MaintenanceController();
    }

    /**
     * Method that prints/displays the engineer profile main menu
     * */
    public void showMaintenanceMenu() throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n✨ --- Bem-Vindo ao Mundo Magico CESAELand, o que deseja fazer? --- \uD83C\uDFA2");
            System.out.println("\n1. \uD83D\uDD27 Ver Próximas Revisões");
            System.out.println("2. \uD83D\uDEE0\uFE0F Ver Histórico de Revisões");
            System.out.println("3. \uD83D\uDEAA Sair");
            System.out.print("\n\uD83C\uDFAF Escolha uma opção: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    viewUpcomingRevisions();
                    break;
                case 2:
                    viewRevisionHistory();
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
     * Method that prints/displays the next maintenance revisions
     */
    private void viewUpcomingRevisions() throws FileNotFoundException {
        List<Map.Entry<Attraction, Integer>> upcomingRevisions = maintenanceController.consultNextRevisions();

        System.out.println("\n\uD83E\uDDD1\uD83C\uDFFC\u200D\uD83D\uDD27 Próximas Revisões:");
        System.out.printf("%-10s %-30s %-20s%n", "ID", "Nome da Atração", "Bilhetes que Faltam");
        System.out.println("------------------------------------------------------------------");

        for (Map.Entry<Attraction, Integer> entry : upcomingRevisions) {
            Attraction attraction = entry.getKey();
            int ticketsNeeded = entry.getValue();

            System.out.printf("%-10d %-30s %-20d%n", attraction.getId(), attraction.getAttractionName(), ticketsNeeded);
        }
    }


    /**
     * Method that prints/displays the last three maintenance revisions.
     */
    private void viewRevisionHistory() throws FileNotFoundException {
        List<Attraction> revisionHistory = maintenanceController.consultRevisionHistory();

        if (revisionHistory.isEmpty()) {
            System.out.println("No maintenance record found.");
            return;
        }

        System.out.println("\n\uD83E\uDDD1\uD83C\uDFFC\u200D\uD83D\uDD27 Histórico de Revisões:");
        System.out.printf("%-10s %-30s %-20s%n", "ID", "Nome da Atração", "Bilhetes Vendidos Após Revisão");
        System.out.println("------------------------------------------------------------");

        int start = Math.max(0, revisionHistory.size() - 3);

        for (int i = start; i < revisionHistory.size(); i++) {
            Attraction attraction = revisionHistory.get(i);

            int totalTicketsSold = 0;
            ArrayList<Sale> saleRepository = SaleRepository.getInstance().getSalesArray();

            for (Sale sale : saleRepository) {
                if (sale.getAttraction() == attraction.getId()) {
                    totalTicketsSold++;
                }
            }

            int ticketsSoldSinceLastRevision = totalTicketsSold % 50;
            System.out.printf("%-10d %-30s %-20d%n", attraction.getId(), attraction.getAttractionName(), ticketsSoldSinceLastRevision);
        }
    }
}
