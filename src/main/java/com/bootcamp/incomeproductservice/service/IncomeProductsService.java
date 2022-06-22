package com.bootcamp.incomeproductservice.service;

import com.bootcamp.incomeproductservice.model.dto.IncomeProductsPerClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public interface IncomeProductsService {

  Flux<IncomeProductsPerClient> fetchIncomeProductsPerClient(String clientID);
}