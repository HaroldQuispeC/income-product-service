package com.bootcamp.incomeproductservice.model.dto;

import com.bootcamp.incomeproductservice.model.base.ClientBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ClientDto extends ClientBase {
  private String idClient;
}