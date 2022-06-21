package com.bootcamp.incomeproductservice.service;

import com.bootcamp.incomeproductservice.model.Credit;
import java.util.List;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface CreditService {

  Mono<Credit> create(Credit credit);

  Mono<Void> createList(List<Credit> credits);

  Mono<Credit> findById(String creditID);

  Flux<Credit> findAll();

  Mono<Credit> update(Credit credit);

  Mono<Credit> update(String id, Credit credit);

  Mono<Credit> change(Credit credit);

  Mono<Void> remove(String creditID);

  Mono<Void> remove(Credit credit);

  Mono<Void> inactive(String id);

  Flux<Credit> fetchCreditsByClientName(String name);

  Flux<Credit> findByBusinessClientId(String clientID);

  Mono<Credit> findByPersonClientId(String clientID);

}

