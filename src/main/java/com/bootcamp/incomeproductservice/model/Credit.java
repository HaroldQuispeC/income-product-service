package com.bootcamp.incomeproductservice.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Credit")

public class Credit {
  @Id
  private String creditID;
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
