package org.example.controller;

import org.example.repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {

    private AdminController adminController;
    private SaleRepository saleRepository;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        AdminController adminController = new AdminController();

    }



}