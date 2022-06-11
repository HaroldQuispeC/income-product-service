package com.bootcamp.incomeproductservice.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Client {
    private String idClient;
    private Date joiningDate;
    private String country;
    private String address;
    private String clientType;
    private String status;
    private List<String> phones;
    private List<String> emails;
    private NaturalPerson naturalPerson;
    private Business business;
}
