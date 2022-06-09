package com.bootcamp.incomeproductservice.model.dto;

import com.bootcamp.incomeproductservice.model.IncomeAccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditDto {
    @Id
    private String creditID;
    private String clientID;
    private IncomeAccountType incomeAccountType;
    private String creditSN;
    private double creditLimit;
    private double debt;
    private double balance;
    private  int billingCycle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private boolean active;
}
