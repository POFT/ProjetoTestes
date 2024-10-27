package org.example.util;

import org.example.model.Attraction;
import org.example.util.CSVAttractionsReader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVAttractionsReaderTest {

    @Test
    public void testReadAttractionsFile_FileNok() {
        String invalidPath = "invalid/file/path.csv";

        assertThrows(FileNotFoundException.class, () -> {
            CSVAttractionsReader.readAttractionsFile(invalidPath);
        });
    }

    /**
     * Testar se o count de Atrações, corresponde ao que está no Cesaeland_atracoes.csv,
     * resposta esperada 10 atrações.
     * @throws FileNotFoundException
     */
    @Test
    public void testReadAttractionsFile_FileOk() throws FileNotFoundException {

        String path = "src/main/resources/Cesaeland_atracoes.csv";
        ArrayList<Attraction> attractions = CSVAttractionsReader.readAttractionsFile(path);

        int expectedAttractions = 10;
        int resultAttractions = attractions.size();
        assertEquals(expectedAttractions, resultAttractions);

        Attraction firstline = attractions.get(0);
        Attraction secondline = attractions.get(1);
        Attraction thirdline = attractions.get(2);
        Attraction fourthline = attractions.get(3);
        Attraction fifthline = attractions.get(4);
        Attraction sixthline = attractions.get(5);
        Attraction seventhline = attractions.get(6);
        Attraction eighthline = attractions.get(7);
        Attraction ninthline = attractions.get(8);
        Attraction tenthline = attractions.get(9);


        assertEquals(1, firstline.getId());
        assertEquals("Montanha Russa da Programacao", firstline.getAttractionName());
        assertEquals(15, firstline.getPriceAdult());
        assertEquals(12, firstline.getPriceChild());
        assertEquals(180, firstline.getDurationSeconds());

        assertEquals(2, secondline.getId());
        assertEquals("Casa Assombrada de Projeto Final", secondline.getAttractionName());
        assertEquals(3.5, secondline.getPriceAdult());
        assertEquals(2.5, secondline.getPriceChild());
        assertEquals(240, secondline.getDurationSeconds());

        assertEquals(3, thirdline.getId());
        assertEquals("Trampolins Bases de Dados", thirdline.getAttractionName());
        assertEquals(10, thirdline.getPriceAdult());
        assertEquals(5, thirdline.getPriceChild());
        assertEquals(600, thirdline.getDurationSeconds());

        assertEquals(4, fourthline.getId());
        assertEquals("Rafting Laravel", fourthline.getAttractionName());
        assertEquals(5, fourthline.getPriceAdult());
        assertEquals(4, fourthline.getPriceChild());
        assertEquals(90, fourthline.getDurationSeconds());

        assertEquals(5, fifthline.getId());
        assertEquals("Carrossel Web", fifthline.getAttractionName());
        assertEquals(4.5, fifthline.getPriceAdult());
        assertEquals(3, fifthline.getPriceChild());
        assertEquals(120, fifthline.getDurationSeconds());

        assertEquals(6, sixthline.getId());
        assertEquals("Rio Lento Quality Assurance", sixthline.getAttractionName());
        assertEquals(3, sixthline.getPriceAdult());
        assertEquals(1.5, sixthline.getPriceChild());
        assertEquals(360, sixthline.getDurationSeconds());

        assertEquals(7, seventhline.getId());
        assertEquals("Elevador de Servicos", seventhline.getAttractionName());
        assertEquals(10, seventhline.getPriceAdult());
        assertEquals(8, seventhline.getPriceChild());
        assertEquals(120, seventhline.getDurationSeconds());

        assertEquals(8, eighthline.getId());
        assertEquals("Escorregas da IA", eighthline.getAttractionName());
        assertEquals(2, eighthline.getPriceAdult());
        assertEquals(0.5, eighthline.getPriceChild());
        assertEquals(30, eighthline.getDurationSeconds());

        assertEquals(9, ninthline.getId());
        assertEquals("Torre da Nuvem", ninthline.getAttractionName());
        assertEquals(12, ninthline.getPriceAdult());
        assertEquals(10, ninthline.getPriceChild());
        assertEquals(180, ninthline.getDurationSeconds());

        assertEquals(10, tenthline.getId());
        assertEquals("Labirinto do Trabalho em Equipa", tenthline.getAttractionName());
        assertEquals(7.5, tenthline.getPriceAdult());
        assertEquals(5, tenthline.getPriceChild());
        assertEquals(520, tenthline.getDurationSeconds());
    }
}
