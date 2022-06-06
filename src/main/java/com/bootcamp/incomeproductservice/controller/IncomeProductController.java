package com.bootcamp.incomeproductservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incomeProduct")
public class IncomeProductController {

    @GetMapping("/")
    public String getSaludo(){

        return "Saludo";
    }
}
