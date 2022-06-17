package com.bootcamp.incomeproductservice.service;

import com.bootcamp.incomeproductservice.model.Business;
import com.bootcamp.incomeproductservice.model.Client;
import feign.Headers;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "client",
        url = "http://localhost:8080/api/client")
public interface ClientService {
  @RequestMapping(method = RequestMethod.GET, value = "/")
  @Headers("Content-Type: application/json")
  List<Client> getClients();

  @RequestMapping(method = RequestMethod.GET, value = "/findByDocument/{dni}")
  @Headers("Content-Type: application/json")
  Client findByDocument(@PathVariable("dni") String dni);

  @RequestMapping(method = RequestMethod.GET, value = "/findByRuc/{ruc}")
  @Headers("Content-Type: application/json")
  Business findByRuc(@PathVariable("ruc") String ruc);
}
