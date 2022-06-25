package com.bootcamp.incomeproductservice.model;

import com.bootcamp.incomeproductservice.model.base.CreditBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "Credit")
public class Credit extends CreditBase {
  @Id
  private String creditID;
  private  CreditCard creditCard;
}
