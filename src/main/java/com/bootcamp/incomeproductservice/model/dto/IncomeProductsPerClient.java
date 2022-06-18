package com.bootcamp.incomeproductservice.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IncomeProductsPerClient {
  private ClientDto client;
  private List<CreditDto> credits;
}
