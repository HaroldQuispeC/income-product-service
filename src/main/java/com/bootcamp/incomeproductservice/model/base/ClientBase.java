package com.bootcamp.incomeproductservice.model.base;

import com.bootcamp.incomeproductservice.model.Business;
import com.bootcamp.incomeproductservice.model.NaturalPerson;
import java.util.List;
import lombok.Data;

@Data
public class ClientBase {
  private String joiningDate;
  private String country;
  private String clientProfile;
  private String address;
  private String clientType;
  private String status;
  private List<String> phones;
  private List<String> emails;
  private NaturalPerson naturalPerson;
  private Business business;
}
