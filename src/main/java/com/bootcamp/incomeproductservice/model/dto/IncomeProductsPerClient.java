package com.bootcamp.incomeproductservice.model.dto;

import java.util.ArrayList;
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

  /** addCredit, custom property for IncomeProductsPerClient.
   * IncomeProductsPerClient,
   * @param credit CreditDto
   */
  public void addCredit(CreditDto credit) {
    if (credits == null) {
      this.credits = new ArrayList<>();
    }

    if (credit != null) {
      this.credits.add(credit);
    }
  }
}
