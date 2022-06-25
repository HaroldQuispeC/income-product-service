package com.bootcamp.incomeproductservice.model.dto;

import com.bootcamp.incomeproductservice.model.base.ClientBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ClientDto extends ClientBase {
  private String idClient;
}