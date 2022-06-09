package com.bootcamp.incomeproductservice.service.impl;

import com.bootcamp.incomeproductservice.model.Credit;
import com.bootcamp.incomeproductservice.model.CreditCard;
import com.bootcamp.incomeproductservice.repository.CreditCardRepository;
import com.bootcamp.incomeproductservice.repository.CreditRepository;
import com.bootcamp.incomeproductservice.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CreditRepository creditRepository;

    @Override
    public Mono<CreditCard> create(CreditCard card) {
        logger.info("Create entity credit card");
        return creditCardRepository.save(card);
    }

    @Override
    public Mono<Void> createList(List<CreditCard> cards) {
        logger.info("Create list of credit card entity");
        creditCardRepository.saveAll(cards).subscribe();
        return Mono.just("Adding credit card list").then();
    }

    @Override
    public Mono<CreditCard> findById(String id) {
        logger.info("Find credit entity");
        return creditCardRepository.findById( id );
    }

    @Override
    public Flux<CreditCard> findAll() {
        logger.info("Find all credit card entities");
        return creditCardRepository.findAll().filter(c-> c.isActive());
    }

    @Override
    public Mono<CreditCard> findByPersonClientId(String id) {
        return null;
    }

    @Override
    public Mono<CreditCard> update(CreditCard card) {
        logger.info("Updating credit card entity");
        return creditCardRepository.save(card);
    }

    @Override
    public Mono<CreditCard> change(CreditCard card) {
        logger.info("Saving credit card entity");
        return creditCardRepository.save(card);
    }

    @Override
    public Mono<Void> remove(String id) {
        logger.info("Removing credit card entity physically");
        creditCardRepository.deleteById(id).subscribe();
        return Mono.just("remove " + id).then();
    }

    @Override
    public Mono<Void> remove(CreditCard card) {
        logger.info("Removing credit card entity physically");
        String id = card.getCreditCardID();
        creditCardRepository.deleteById(id).subscribe();
        return Mono.just("remove " + id).then();
    }

    @Override
    public Flux<Object> findByBusinessClientId(String clientID) {
        logger.info("Find credit cards by Business Client ");
        Mono<List<Credit>> credits = creditRepository.findAll().
                                filter(c-> c.isActive() && c.getClientID() == clientID).collectList();

       Flux<Object> creditCardFlux = credits.flatMapMany(c-> creditRepository.findAll().
                                        filter(x-> c.contains( x.getCreditID()) ));

       return creditCardFlux;
    }

    @Override
    public Mono<Void> inactive(String id) {
       return creditCardRepository.findById(id).
               flatMap(card -> {
                   card.setActive(false);
                   card.setStatus("inactive");
                return creditCardRepository.save(card).then();
            });
    }

}
