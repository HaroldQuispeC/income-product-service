package com.bootcamp.incomeproductservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "IncomeAccountType")
public class IncomeAccountType {
  private String incomeAccountTypeID;
  private String incomeAccountDescription;
  private int maximumProductsAllowed;
}
