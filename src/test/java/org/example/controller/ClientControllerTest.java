package org.example.controller;

import org.example.model.Attraction;
import org.example.model.Sale;
import org.example.repository.AttractionRepository;
import org.example.repository.SaleRepository;
import org.example.util.CSVAttractionsReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientControllerTest {

    private ClientController clientController;
    private AttractionRepository attractionRepository;
    private SaleRepository saleRepository;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        // Inicializar os repositórios antes de cada teste
        attractionRepository = new AttractionRepository();
        saleRepository = new SaleRepository();
        clientController = new ClientController(attractionRepository,saleRepository);
    }


    /**
     * Testar se o count de Atrações, corresponde ao que está no Cesaeland_atracoes.csv,
     * resposta esperada 10 atrações.
     */
    @Test
    public void testAttractionsCount() {

        // Valor esperado para o teste
        int expected = 10;
        int result = clientController.attractions().size();
        // Verificar se o resultado corresponde ao valor esperado
        assertEquals(expected, result);
    }


    /**
     * Testar se o count de Vendas, corresponde ao que está no Cesaeland_vendas.csv,
     * resposta esperada 1299 vendas.
     */
    @Test
    public void testSalesCount() {
        // Valor esperado para o teste
        int expected = 1299;
        int result = clientController.getSaleRepository().getSalesArray().size();
        // Verificar se o resultado corresponde ao valor esperado
        assertEquals(expected, result);
    }

    /**
     * Testar se o count de Vendas por adulto, corresponde ao que está no Cesaeland_vendas.csv,
     * resposta esperada 667 vendas.
     */
    @Test
    public void testSalesCountAdult() {
        // Valor esperado para o teste
        int expected = 667;
        int result = 0;
        for (Sale sale : saleRepository.getSalesArray()) {
            if (sale.getClientType().equalsIgnoreCase("adulto")) {
                result++;
            }
        }
        // Validar o resultado
        assertEquals(expected, result);
    }

    /**
     * Testar se o count de Vendas por criança, corresponde ao que está no Cesaeland_vendas.csv,
     * resposta esperada 632 vendas.
     */
    @Test
    public void testSalesCountChildren() {
        // Valor esperado para o teste
        int expected = 632;
        int result = 0;
        for (Sale sale : saleRepository.getSalesArray()) {
            if (sale.getClientType().equalsIgnoreCase("crianca")) {
                result++;
            }
        }
        // Validar o resultado
        assertEquals(expected, result);
    }


    /**
     * Testar a atração favorita dos adultos,
     * corresponde à atração com mais vendas do tipoCliente adulto,
     * resposta esperada "Montanha Russa da Programacao".
     */
    @Test
    public void testAdultFavoriteAttraction() {
        // Valor esperado para o teste
        String expected = "Montanha Russa da Programacao";
        String result = clientController.favoriteAttractionForAdults();
        // Validar o resultado
        assertEquals(expected, result);
    }

    /**
     * Testar a atração favorita das crianças,
     * corresponde à atração com mais vendas do tipoCliente crianca,
     * resposta esperada "Escorregas da IA".
     */
    @Test
    public void testChildrenFavoriteAttraction() {
        // Valor esperado para o teste
        String expected= "Escorregas da IA";
        String result = clientController.favoriteAttractionsForChildren();
        // Validar o resultado
        assertEquals(expected, result);
    }
    
}