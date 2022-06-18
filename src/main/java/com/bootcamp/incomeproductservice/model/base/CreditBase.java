package com.bootcamp.incomeproductservice.model.base;

import com.bootcamp.incomeproductservice.model.IncomeAccountType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CreditBase {
  private String clientID;
  private IncomeAccountType incomeAccountType;
  private String creditSN;
  private double creditLimit;
  private double debt;
  private double balance;
  private int billingCycle;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String status;
  private boolean active;
}
