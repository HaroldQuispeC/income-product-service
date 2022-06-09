package com.bootcamp.incomeproductservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class IncomeAccountTypeDto {

    private long incomeAccountTypeID;
    private String incomeAccountDescription;
    private int maximumProductsAllowed;
}
