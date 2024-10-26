package org.example.controller;

import org.example.model.Attraction;
import org.example.model.Sale;
import org.example.repository.AttractionRepository;
import org.example.repository.SaleRepository;
import org.example.util.CSVAttractionsReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
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
     * @throws FileNotFoundException
     */
    @Test
    public void testAttractionsCount() throws FileNotFoundException {
        // Valor esperado para o teste
        int expected = 10;
        // Obter o resultado chamando o método
        int result = clientController.attractions().size();
        // Verificar se o resultado corresponde ao valor esperado
        assertEquals(expected, result);
    }


    /**
     * Testar se o count de Vendas, corresponde ao que está no Cesaeland_vendas.csv,
     * resposta esperada 1299 vendas.
     * @throws FileNotFoundException
     */
    @Test
    public void testSalesCount() throws FileNotFoundException {
        // Valor esperado para o teste
        int expected = 1299;
        // Obter o resultado chamando o método
        int result = clientController.getSaleRepository().getSalesArray().size();
        // Verificar se o resultado corresponde ao valor esperado
        assertEquals(expected, result);
    }

    /**
     * Testar se o count de Vendas por adulto, corresponde ao que está no Cesaeland_vendas.csv,
     * resposta esperada 667 vendas.
     * @throws FileNotFoundException
     */
    @Test
    public void testSalesCountAdult() throws FileNotFoundException {
        // Valor esperado para o teste
        int expected = 667;
        // Instanciar o método para validar o resultado
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
     * @throws FileNotFoundException
     */
    @Test
    public void testSalesCountChildren() throws FileNotFoundException {
        // Valor esperado para o teste
        int expected = 632;
        // Instanciar o método para validar o resultado
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
     * @throws FileNotFoundException
     */
    @Test
    public void testAdultFavoriteAttraction() throws FileNotFoundException {
        // Valor esperado para o teste
        String expected = "Montanha Russa da Programacao";
        // Instanciar o método para validar o resultado
        String result = clientController.favoriteAttractionForAdults();
        assertEquals(expected, result);
    }

    /**
     * Testar a atração favorita das crianças,
     * corresponde à atração com mais vendas do tipoCliente crianca,
     * resposta esperada "Escorregas da IA".
     * @throws FileNotFoundException
     */
    @Test
    public void testChildrenFavoriteAttraction() throws FileNotFoundException {
        // Valor esperado para o teste
        String expected= "Escorregas da IA";
        // Instanciar o método para validar o resultado
        String result = clientController.favoriteAttractionsForChildren();
        assertEquals(expected, result);
    }
    
}