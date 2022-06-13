package com.bootcamp.incomeproductservice.service;

import com.bootcamp.incomeproductservice.model.Credit;
import com.bootcamp.incomeproductservice.model.CreditCard;
import com.bootcamp.incomeproductservice.model.dto.CreditDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public interface CreditCardService {
    Mono<CreditCard> create(CreditCard card);

    Mono<Void> createList(List<CreditCard> cards);

    Mono<CreditCard> findById(String id);

    Flux<CreditCard> findAll();

    Mono<CreditCard> update(CreditCard card);

    Mono<CreditCard> change(CreditCard card);

    Mono<Void> remove(String id);

    Mono<Void> remove(CreditCard card);

    Mono<Void> inactive(String id);

    Flux<Object> findByBusinessClientId(String id);

    Flux<CreditCard> findByPersonClientId(String id);
}
