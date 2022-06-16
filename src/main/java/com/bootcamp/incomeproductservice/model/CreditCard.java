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
@Document(collection = "CreditCard")
//@Entity
public class CreditCard {
  @Id
  private String creditCardID;
  private String creditID;
  private String financialCompany;
  private String creditCardSN;
  private LocalDateTime startDate;
  private LocalDateTime expirationDate;
  private String status;
  private boolean active;
}
