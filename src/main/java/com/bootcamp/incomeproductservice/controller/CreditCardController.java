package com.bootcamp.incomeproductservice.controller;

import com.bootcamp.incomeproductservice.model.CreditCard;
import com.bootcamp.incomeproductservice.service.CreditCardService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/incomes/credit-cards")
public class CreditCardController {
  @Autowired
  private CreditCardService creditCardService;

  @GetMapping("{id}")
  public Mono<CreditCard> findById(@PathVariable("id") String id) {
    return creditCardService.findById(id);
  }

  @GetMapping
  public Flux<CreditCard> findAll() {
    return creditCardService.findAll();
  }

  @GetMapping("/business-client/{id}")
  public Flux<CreditCard> findByBusinessClientId(@PathVariable("id") String id) {
    return creditCardService.findByBusinessClientId(id);
  }

  @GetMapping("/person-client/{id}")
  public Flux<CreditCard> findByPersonClientId(@PathVariable("id") String id) {
    return creditCardService.findByPersonClientId(id);
  }

  @GetMapping("/business-client/search")
  public Flux<CreditCard> findByBusinessClient(@RequestParam(value = "ruc",
         required = true) String ruc) {
    return creditCardService.findByBusinessClient(ruc);
  }

  @GetMapping("/person-client/search")
  public Flux<CreditCard> findByPersonClient(@RequestParam(value = "dni",
          required = true) String dni) {
    return creditCardService.findByPersonClient(dni);
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

  /**
   * delete method.
   * @param card CreditCard
   * @return Mono Void
   */
  @DeleteMapping
  public Mono<Void> delete(@RequestBody CreditCard card) {
    return creditCardService.remove(card);
  }

  @PostMapping("/{id}/inactive")
  public Mono<Void> inactive(@PathVariable("id") String id) {
    return creditCardService.inactive(id);
  }

}
