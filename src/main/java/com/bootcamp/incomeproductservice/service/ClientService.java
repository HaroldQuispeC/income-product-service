package com.bootcamp.incomeproductservice.service;

import com.bootcamp.incomeproductservice.model.Business;
import com.bootcamp.incomeproductservice.model.Client;
import feign.Headers;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "client",
        url = "http://localhost:8080/api/client")
public interface ClientService {
  @GetMapping(value = "/")
  @Headers("Content-Type: application/json")
  List<Client> getClients();

  @GetMapping(value = "/findByDocument/{dni}")
  @Headers("Content-Type: application/json")
  Client findByDocument(@PathVariable("dni") String dni);

  @GetMapping(value = "/findByRuc/{ruc}")
  @Headers("Content-Type: application/json")
  Business findByRuc(@PathVariable("ruc") String ruc);
}
