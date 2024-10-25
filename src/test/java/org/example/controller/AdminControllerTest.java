package org.example.controller;

import org.example.repository.AttractionRepository;
import org.example.repository.CostRepository;
import org.example.repository.SaleRepository;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {

    private AdminController adminController;

    private SaleRepository saleRepository;
    private CostRepository costRepository;
    private AttractionRepository attractionRepository;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() throws FileNotFoundException {

        saleRepository = new SaleRepository();
        costRepository = new CostRepository();
        attractionRepository = new AttractionRepository();
        userRepository = new UserRepository();
        adminController = new AdminController();

    }

    /**
     * Testar se o total de vendas, corresponde ao que está nos ficheiros CSV,
     * resposta esperada 8144.
     * @throws FileNotFoundException
     */
    @Test
    public void testTotalSales() throws FileNotFoundException {
        // Valor esperado para o teste
        double expected = 8144.0;

        // Inicializar o controlador
        AdminController adminController = new AdminController();

        // Obter o resultado chamando o método
        double result = adminController.totalSales();

        // Validar o resultado
        assertEquals(expected, result, 0.01);
    }

    /**
     * Testar se o total de lucro, corresponde ao que está nos ficheiros CSV,
     * resposta esperada -3716.90.
     * @throws FileNotFoundException
     */
    @Test
    public void testTotalProfit() throws FileNotFoundException {
        // Valor esperado para o teste
        double expected = -3716.90;

        // Inicializar o controlador
        AdminController adminController = new AdminController();

        // Obter o resultado chamando o método
        double result = adminController.totalProfit();

        // Validar o resultado
        assertEquals(expected, result, 0.01);
    }







}