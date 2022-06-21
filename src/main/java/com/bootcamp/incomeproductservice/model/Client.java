package com.bootcamp.incomeproductservice.model;

import com.bootcamp.incomeproductservice.model.base.ClientBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Client extends ClientBase {
  private String idClient;
}
