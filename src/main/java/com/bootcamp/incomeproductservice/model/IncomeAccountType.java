package com.bootcamp.incomeproductservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "IncomeAccountType")
public class IncomeAccountType {
  private String incomeAccountTypeID;
  private String incomeAccountDescription;
  private Integer maximumProductsAllowed;
}