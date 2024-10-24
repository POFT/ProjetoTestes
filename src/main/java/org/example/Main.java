package org.example;

import org.example.model.User;
import org.example.repository.AttractionRepository;
import org.example.repository.SaleRepository;
import org.example.repository.UserRepository;
import org.example.view.AdminView;
import org.example.view.ClientView;
import org.example.view.MaintenanceView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /**
     * Method that prints/displays the CESAELand home/login menu
     * */
    public static void main(String[] args) throws FileNotFoundException {
        UserRepository userRepository = new UserRepository();
        AttractionRepository attractionRepository = new AttractionRepository();
        SaleRepository saleRepository = new SaleRepository();

        ArrayList<User> users = userRepository.getUsersArray();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n✨ --- Bem-Vindo ao CESAELand! --- ✨");
            System.out.println("\n1. \uD83E\uDDD1\uD83C\uDFFB Acessar como Cliente");
            System.out.println("2. \uD83D\uDC49 Entrar como \uD83D\uDC68\uD83C\uDFFB\u200D\uD83D\uDCBC Admin ou \uD83E\uDDD1\uD83C\uDFFC\u200D\uD83D\uDD27 Engenheiro");
            System.out.println("3. \uD83D\uDEAA Sair");
            System.out.print("\n\uD83C\uDFAF Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    ClientView clientView = new ClientView(attractionRepository, saleRepository);
                    clientView.showClientMenu();
                    break;
                case 2:
                    boolean loggedIn = false;
                    while (!loggedIn) {
                        System.out.print("\n\uD83D\uDC69\uD83C\uDFFB\u200D\uD83D\uDCBB Digite seu nome de usuário: ");
                        String username = scanner.nextLine();

                        System.out.print("\n\uD83D\uDD10 Digite sua senha: ");
                        String password = scanner.nextLine();

                        for (User user : users) {
                            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                                loggedIn = true;
                                System.out.println("\n✅ Login bem-sucedido! Você é um(a) " + user.getLoginType());

                                if (user.getLoginType().equals("ADMIN")) {
                                    AdminView adminView = new AdminView();
                                    adminView.showAdminMenu();
                                } else if (user.getLoginType().equals("ENG")) {
                                    MaintenanceView maintenanceView = new MaintenanceView();
                                    maintenanceView.showMaintenanceMenu();
                                }
                                break;
                            }
                        }

                        if (!loggedIn) {
                            System.out.println("\n🚫 Nome de usuário ou senha incorretos. Tente novamente.");
                        }
                    }
                    break;
                case 3:
                    exit = true;
                    System.out.println("\n✨ Obrigado por visitar o CESAELand! Até logo! ✨");
                    break;
                default:
                    System.out.println("\n🚫 Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

}
