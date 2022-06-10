package com.bootcamp.incomeproductservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaturalPerson {

    private String idNaturalPerson;
    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String gender;
}
