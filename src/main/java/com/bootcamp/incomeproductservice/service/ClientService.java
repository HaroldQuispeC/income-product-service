package com.bootcamp.incomeproductservice.service;

import com.bootcamp.incomeproductservice.exceptions.FallbackException;
import com.bootcamp.incomeproductservice.model.Client;
import feign.Headers;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "client-service",  url = "http://localhost:8080/api/client",
            fallback = ClientService.ClientServiceFallback.class)
public interface ClientService {
  @GetMapping(value = "/")
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "client-service-cb")
  List<Client> getClients();

  @GetMapping(value = "/findByDocument/{dni}")
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "client-service-cb")
  Client findByDocument(@PathVariable("dni") String dni);

  @GetMapping(value = "/findByRuc/{ruc}")
  @Headers("Content-Type: application/json")
  @CircuitBreaker(name = "client-service-cb")
  Client findByRuc(@PathVariable("ruc") String ruc);

  @Component
  class ClientServiceFallback implements ClientService {

    private final String defaultMessage = "Client Service is unavailable at this moment";

    /**
     * getClients fallback method.
     *
     * @return List Client
     */
    @Override
    public List<Client> getClients() {
      throw new FallbackException(String.format("getClients: %s", defaultMessage));
    }

    /**
     * findByDocument fallback method.
     *
     * @param dni String
     * @return String
     */
    @Override
    public Client findByDocument(String dni) {
      throw new FallbackException(String.format("findByDocument: %s", defaultMessage));
    }

    /**
     * findByRuc fallback method.
     *
     * @param ruc String
     * @return Client
     */
    @Override
    public Client findByRuc(String ruc) {
      throw new FallbackException(String.format("findByRuc: %s", defaultMessage));
    }
  }
}

