package com.bootcamp.incomeproductservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IncomeAccountTypeDto {

  private long incomeAccountTypeID;
  private String incomeAccountDescription;
  private int maximumProductsAllowed;
}
