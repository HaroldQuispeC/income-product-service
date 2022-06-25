package com.bootcamp.incomeproductservice.model.dto;

import com.bootcamp.incomeproductservice.model.base.CreditBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CreditDto extends CreditBase {
  private String creditID;
  private CreditCardDto creditCard;
}
