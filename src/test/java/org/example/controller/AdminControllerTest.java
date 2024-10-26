package org.example.controller;

import org.example.model.Attraction;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {

    private AdminController adminController;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() throws FileNotFoundException {

        userRepository = new UserRepository();
        adminController = new AdminController();

    }

    /**
     * Testar se o count de User/Logins, corresponde ao que está no Cesaeland_logins.csv,
     * resposta esperada 6 registos.
     */
    @Test
    public void testUserLoginsCount() {
        // Valor esperado para o teste
        int expected = 6;
        // Obter o resultado chamando o método

        int result = userRepository.getUsersArray().size();
        // Verificar se o resultado corresponde ao valor esperado
        assertEquals(expected, result);
    }

    /**
     * Testar se o total de vendas, corresponde ao que está nos ficheiros CSV,
     * resposta esperada 8144.
     */
    @Test
    public void testTotalSales() {
        // Valor esperado para o teste
        double expected = 8144.0;
        // Obter o resultado chamando o método
        double result = adminController.totalSales();

        // Validar o resultado
        assertEquals(expected, result, 0.01);
    }

    /**
     * Testar se o total de lucro, corresponde ao que está nos ficheiros CSV,
     * resposta esperada -3716.90.
     */
    @Test
    public void testTotalProfit() {
        // Valor esperado para o teste
        double expected = -3716.90;
        // Obter o resultado chamando o método
        double result = adminController.totalProfit();

        // Validar o resultado
        assertEquals(expected, result, 0.01);
    }

    /**
     * Testar a atração mais popular,
     * corresponde à atração com mais vendas,
     * resposta esperada "Escorregas da IA", com um total de 246 bilhetes vendidos.
     */
    @Test
    public void testMostPopularAttraction() {
        // Valor esperado para o teste
        String expected = "Escorregas da IA";
        // Instanciar o método para validar o resultado
        Attraction result = adminController.mostPopularAttraction();
        assertEquals(expected, result.getAttractionName());
    }

    /**
     * Testar a atração mais popular entre adultos,
     * corresponde à atração com mais vendas do tipoCliente adulto,
     * resposta esperada "Montanha Russa da Programacao", com um total de 214 bilhetes vendidos.
     */
    @Test
    public void testAdultMostPopularAttraction() {
        // Valor esperado para o teste
        String expected = "Montanha Russa da Programacao";
        // Instanciar o método para validar o resultado
        Attraction result = adminController.mostPopularAttractionByClientType("adulto");
        assertEquals(expected, result.getAttractionName());
    }

    /**
     * Testar a atração mais popular entre crianças,
     * corresponde à atração com mais vendas do tipoCliente crianca,
     * resposta esperada "Escorregas da IA", com um total de 246 bilhetes vendidos.
     */
    @Test
    public void testChildrenMostPopularAttraction() {
        // Valor esperado para o teste
        String expected = "Escorregas da IA";
        // Instanciar o método para validar o resultado
        Attraction result = adminController.mostPopularAttractionByClientType("crianca");
        assertEquals(expected, result.getAttractionName());
    }


    /**
     * Testar a atração mais lucrativa,
     * corresponde à atração que gerou mais lucro (calcula o lucro para cada atração considerando o total de receita menos os custos),
     * resposta esperada "Montanha Russa da Programacao", com um lucro total de 1894.00.
     */
    @Test
    public void testMostProfitableAttraction() {
        // Valor esperado para o teste
        String expected = "Montanha Russa da Programacao";
        // Instanciar o método para validar o resultado
        Attraction result = adminController.mostProfitableAttraction();
        assertEquals(expected, result.getAttractionName());
    }

    /**
     * Testar a atração menos lucrativa,
     * corresponde à atração que gerou menos lucro (calcula o lucro para cada atração considerando o total de receita menos os custos),
     * resposta esperada "Rio Lento Quality Assurance", com um lucro total de -276.00.
     */
    @Test
    public void testLessProfitableAttraction() {
        // Valor esperado para o teste
        String expected = "Rio Lento Quality Assurance";
        // Instanciar o método para validar o resultado
        Attraction result = adminController.lessProfitableAttraction();
        assertEquals(expected, result.getAttractionName());
    }

    /**
     * Testar a atração com melhor preço/tempo,
     * corresponde à atração com o melhor custo-benefício em termos de preço por segundo de diversão,
     * resposta esperada "Rio Lento Quality Assurance", com preço para adultos de 3.00 e preço para crianças de 1.50. Duração: 360 segundos.
     */
    @Test
    public void testBestPricePerSecondAttraction() {
        // Valor esperado para o teste
        String expected = "Rio Lento Quality Assurance";
        // Instanciar o método para validar o resultado
        Attraction result = adminController.bestPricePerSecond();
        assertEquals(expected, result.getAttractionName());
    }

}