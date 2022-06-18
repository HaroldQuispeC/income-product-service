package com.bootcamp.incomeproductservice.model;

import com.bootcamp.incomeproductservice.model.base.CreditBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "Credit")

public class Credit extends CreditBase {
  @Id
  private String creditID;
}
