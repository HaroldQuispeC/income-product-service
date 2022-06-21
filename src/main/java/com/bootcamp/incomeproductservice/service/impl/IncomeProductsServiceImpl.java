package com.bootcamp.incomeproductservice.service.impl;

import com.bootcamp.incomeproductservice.exceptions.ModelException;
import com.bootcamp.incomeproductservice.model.Client;
import com.bootcamp.incomeproductservice.model.Constant;
import com.bootcamp.incomeproductservice.model.Credit;
import com.bootcamp.incomeproductservice.model.CreditCard;
import com.bootcamp.incomeproductservice.model.dto.CreditCardDto;
import com.bootcamp.incomeproductservice.model.dto.CreditDto;
import com.bootcamp.incomeproductservice.model.dto.IncomeProductsPerClient;
import com.bootcamp.incomeproductservice.repository.CreditCardRepository;
import com.bootcamp.incomeproductservice.service.ClientService;
import com.bootcamp.incomeproductservice.service.CreditCardService;
import com.bootcamp.incomeproductservice.service.CreditService;
import com.bootcamp.incomeproductservice.service.IncomeProductsService;
import com.bootcamp.incomeproductservice.util.impl.MapStructConverter;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IncomeProductsServiceImpl implements IncomeProductsService {

  private static final Logger logger = LoggerFactory.getLogger(IncomeProductsServiceImpl.class);

  @Autowired
  ClientService feignClientClient;

  @Autowired
  CreditCardService creditCardService;

  @Autowired
  CreditService creditService;

  /**
   * Method fetchIncomeProductsPerClient.
   *
   * @param clientID String
   * @return Rows of IncomeProductsPerClient (Credit)
   */
  public Flux<IncomeProductsPerClient> fetchIncomeProductsPerClient(String clientID) {

    logger.info("fetchIncomeProductsPerClient method.");
    IncomeProductsPerClient incomes = null;

    Optional<Client> optionalClient = feignClientClient.getClients()
            .stream()
            .filter(client -> client.getIdClient()
                    .equals(clientID)).findFirst();

    if (!optionalClient.isPresent()) {
      throw new ModelException("Client ID null or invalid");
    }

    Client oldClient = optionalClient.get();
    incomes = new IncomeProductsPerClient();
    incomes.setClient(MapStructConverter.MAPPER.convert(oldClient));

    Flux<IncomeProductsPerClient> finalFlux = Flux.just(incomes);
    Flux<Credit> monoCredit = null;
    Flux<CreditCard> fluxCreditCards = null;

    if (oldClient.getClientType()  //.getClientTypeId()
            .equals(Constant.ClientType.NATURAL_PERSON.type)) {
      monoCredit = creditService.findByPersonClientId(clientID).flux();
      fluxCreditCards = creditCardService.findByBusinessClientId(clientID);
      /*
      finalFlux.subscribe(x -> {
       x.setCredits( monoCredit.publish() );
      });
      ConnectableFlux<CreditCard> creditCardConnectableFlux = creditCards.publish(); */
    }

    if (oldClient.getClientType()  //.getClientTypeId()
            .equals(Constant.ClientType.BUSINESS.type)) {
      monoCredit = creditService.findByBusinessClientId(clientID);
      fluxCreditCards = creditCardService.findByBusinessClientId(clientID);
    }


    finalFlux.subscribe();

    return finalFlux;
  }
}
