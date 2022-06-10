package com.bootcamp.incomeproductservice.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Client {
    private String idClient;
    private String joiningDate;
    private String country;
    private String address;
    private String clientType;
    private String status;
    private Business business;
    private NaturalPerson naturalPerson;
}
