package com.bootcamp.incomeproductservice.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditCardDto {
  private String creditCardID;
  private String creditID;
  private String financialCompany;
  private String creditCardSN;
  private LocalDateTime expirationDate;
  private String status;
  private boolean active;
}
