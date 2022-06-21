package com.bootcamp.incomeproductservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Business {

  private String idBusiness;
  private String businessName;
  private String ruc;
}
