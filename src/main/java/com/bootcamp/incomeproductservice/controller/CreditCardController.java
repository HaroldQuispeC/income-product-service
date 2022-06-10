package com.bootcamp.incomeproductservice.controller;

import com.bootcamp.incomeproductservice.model.CreditCard;
import com.bootcamp.incomeproductservice.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;


    @GetMapping("{id}")
    public Mono<CreditCard> findbyId(@PathVariable("id") String id) {
        return creditCardService.findById(id);
    }

    @GetMapping
    public Flux<CreditCard> findAll() {
        return creditCardService.findAll();
    }

    @GetMapping("/business-client/{id}")
    public Flux<Object> findByBusinessClientId(@PathVariable("id") String id) {
        return creditCardService.findByBusinessClientId(id);
    }

    @GetMapping("/person-client/{id}")
    public Flux<CreditCard> findByPersonClientId(@PathVariable("id") String id) {
        return creditCardService.findByPersonClientId(id);
    }

    @PostMapping
    public Mono<CreditCard> create(@RequestBody CreditCard card) {
        return creditCardService.create(card);
    }

    @PostMapping(value = "/list")
    public Mono<Void> createList(@RequestBody List<CreditCard> cards) {
        return creditCardService.createList(cards);
    }

    @PutMapping
    public Mono<CreditCard> update(@RequestBody CreditCard card) {
        return creditCardService.update(card);
    }

    @PatchMapping
    public Mono<CreditCard> change(@RequestBody CreditCard card) {
        return creditCardService.change(card);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteById(@PathVariable("id") String id) {
        return creditCardService.remove(id);
    }

    @DeleteMapping
    public Mono<Void> delete(@RequestBody CreditCard credit) {
        return creditCardService.remove(credit);
    }

    @PostMapping("/{id}/inactive")
    public Mono<Void> inactive(@PathVariable("id") String id) {
        return creditCardService.inactive(id);
    }
}
