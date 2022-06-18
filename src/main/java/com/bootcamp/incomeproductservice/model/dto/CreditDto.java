package com.bootcamp.incomeproductservice.model.dto;

import com.bootcamp.incomeproductservice.model.CreditCard;
import com.bootcamp.incomeproductservice.model.base.CreditBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditDto extends CreditBase {
  private String clientID;
  private CreditCard creditCard;
}
