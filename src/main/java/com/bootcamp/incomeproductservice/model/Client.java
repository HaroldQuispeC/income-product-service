package com.bootcamp.incomeproductservice.model;

import java.util.List;
import lombok.Data;

@Data
public class Client {
  private String idClient;
  private String joiningDate;
  private String country;
  private String address;
  private String clientType;
  private String status;
  private List<String> phones;
  private List<String> emails;
  private NaturalPerson naturalPerson;
  private Business business;
}
