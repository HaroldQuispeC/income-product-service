package com.bootcamp.incomeproductservice.controller;

import com.bootcamp.incomeproductservice.model.Credit;
import com.bootcamp.incomeproductservice.service.CreditService;
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
@RequestMapping("/api/incomes")
public class IncomeProductController {

  @Autowired
  private CreditService creditService;

  @GetMapping("{id}")
  public Mono<Credit> findbyId(@PathVariable("id") String id) {
    return creditService.findById(id);
  }

  @GetMapping
  public Flux<Credit> findAll() {
    return creditService.findAll();
  }

  @GetMapping("/search")
  public Flux<Credit> fetchCreditsByClientName(@RequestParam(value = "name",
                                                required = true) String name) {
    return creditService.fetchCreditsByClientName(name);
  }

  @GetMapping("/business-client/{id}")
  public Flux<Credit> findByBusinessClientId(@PathVariable("id") String id) {
    return creditService.findByBusinessClientId(id);
  }

  @GetMapping("/person-client/{id}")
  public Mono<Credit> findByPersonClientId(@PathVariable("id") String id) {
    return creditService.findByPersonClientId(id);
  }

  @PostMapping
  public Mono<Credit> create(@RequestBody Credit credit) {
    return creditService.create(credit);
  }

  @PostMapping(value = "/list")
  public Mono<Void> createList(@RequestBody List<Credit> credits) {
    return creditService.createList(credits);
  }

  @PutMapping
  public Mono<Credit> update(@RequestBody Credit credit) {
    return creditService.update(credit);
  }

  @PatchMapping
  public Mono<Credit> change(@RequestBody Credit credit) {
    return creditService.change(credit);
  }

  @DeleteMapping("{id}")
  public Mono<Void> deleteById(@PathVariable("id") String id) {
    return creditService.remove(id);
  }

  @DeleteMapping
  public Mono<Void> delete(@RequestBody Credit credit) {
    return creditService.remove(credit);
  }

  @PostMapping("/{id}/inactive")
  public Mono<Void> inactive(@PathVariable("id") String id) {
    return creditService.inactive(id);
  }

}
