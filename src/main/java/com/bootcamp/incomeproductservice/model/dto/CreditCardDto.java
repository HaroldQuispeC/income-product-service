package com.bootcamp.incomeproductservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditCardDto {
    private String creditCardID;
    private String creditID;
    private String financialCompany;
    private String creditCardSN;
    private LocalDateTime startDate;
    private LocalDateTime expirationDate;
    private String status;
    private boolean active;
}