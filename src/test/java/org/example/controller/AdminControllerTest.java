package org.example.controller;

import org.example.model.Attraction;
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
     * Testar se o count de User/Logins, corresponde ao que está no Cesaeland_logins.csv,
     * resposta esperada 6 registos.
     * @throws FileNotFoundException
     */
    @Test
    public void testUserLoginsCount() throws FileNotFoundException {
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


    /**
     * Testar a atração mais popular,
     * corresponde à atração com mais vendas,
     * resposta esperada "Escorregas da IA", com um total de 246 bilhetes vendidos.
     * @throws FileNotFoundException
     */
    @Test
    public void testMostPopularAttraction() throws FileNotFoundException {
        // Valor esperado para o teste
        String expected = "Escorregas da IA";

        // Inicializar o controlador
        AdminController adminController = new AdminController();

        // Instanciar o método para validar o resultado
        Attraction result = adminController.mostPopularAttraction();
        assertEquals(expected, result.getAttractionName());
    }


    /**
     * Testar a atração mais popular entre adultos,
     * corresponde à atração com mais vendas do tipoCliente adulto,
     * resposta esperada "Montanha Russa da Programacao", com um total de 214 bilhetes vendidos.
     * @throws FileNotFoundException
     */
    @Test
    public void testAdultMostPopularAttraction() throws FileNotFoundException {
        // Valor esperado para o teste
        String expected = "Montanha Russa da Programacao";

        // Inicializar o controlador
        AdminController adminController = new AdminController();

        // Instanciar o método para validar o resultado
        Attraction result = adminController.mostPopularAttractionByClientType("adulto");
        assertEquals(expected, result.getAttractionName());
    }

    /**
     * Testar a atração mais popular entre crianças,
     * corresponde à atração com mais vendas do tipoCliente crianca,
     * resposta esperada "Escorregas da IA", com um total de 246 bilhetes vendidos.
     * @throws FileNotFoundException
     */
    @Test
    public void testChildrenMostPopularAttraction() throws FileNotFoundException {
        // Valor esperado para o teste
        String expected = "Escorregas da IA";

        // Inicializar o controlador
        AdminController adminController = new AdminController();

        // Instanciar o método para validar o resultado
        Attraction result = adminController.mostPopularAttractionByClientType("crianca");
        assertEquals(expected, result.getAttractionName());
    }


    /**
     * Testar a atração mais lucrativa,
     * corresponde à atração que gerou mais lucro (calcula o lucro para cada atração considerando o total de receita menos os custos),
     * resposta esperada "Montanha Russa da Programacao", com um lucro total de 1894.00.
     * @throws FileNotFoundException
     */
    @Test
    public void testMostProfitableAttraction() throws FileNotFoundException {
        // Valor esperado para o teste
        String expected = "Montanha Russa da Programacao";

        // Inicializar o controlador
        AdminController adminController = new AdminController();

        // Instanciar o método para validar o resultado
        Attraction result = adminController.mostProfitableAttraction();
        assertEquals(expected, result.getAttractionName());
    }

    /**
     * Testar a atração menos lucrativa,
     * corresponde à atração que gerou menos lucro (calcula o lucro para cada atração considerando o total de receita menos os custos),
     * resposta esperada "Rio Lento Quality Assurance", com um lucro total de -276.00.
     * @throws FileNotFoundException
     */
    @Test
    public void testLessProfitableAttraction() throws FileNotFoundException {
        // Valor esperado para o teste
        String expected = "Rio Lento Quality Assurance";

        // Inicializar o controlador
        AdminController adminController = new AdminController();

        // Instanciar o método para validar o resultado
        Attraction result = adminController.lessProfitableAttraction();
        assertEquals(expected, result.getAttractionName());
    }

    /**
     * Testar a atração com melhor preço/tempo,
     * corresponde à atração com o melhor custo-benefício em termos de preço por segundo de diversão,
     * resposta esperada "Rio Lento Quality Assurance", com preço para adultos de 3.00 e preço para crianças de 1.50. Duração: 360 segundos.
     * @throws FileNotFoundException
     */
    @Test
    public void testBestPricePerSecondAttraction() throws FileNotFoundException {
        // Valor esperado para o teste
        String expected = "Rio Lento Quality Assurance";

        // Inicializar o controlador
        AdminController adminController = new AdminController();

        // Instanciar o método para validar o resultado
        Attraction result = adminController.bestPricePerSecond();
        assertEquals(expected, result.getAttractionName());
    }

}