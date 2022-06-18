package com.bootcamp.incomeproductservice.service.impl;

import com.bootcamp.incomeproductservice.exceptions.ModelException;
import com.bootcamp.incomeproductservice.model.Client;
import com.bootcamp.incomeproductservice.model.Constant;
import com.bootcamp.incomeproductservice.model.CreditCard;
import com.bootcamp.incomeproductservice.repository.CreditCardRepository;
import com.bootcamp.incomeproductservice.repository.CreditRepository;
import com.bootcamp.incomeproductservice.service.ClientService;
import com.bootcamp.incomeproductservice.service.CreditCardService;
import com.bootcamp.incomeproductservice.util.Util;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardServiceImpl implements CreditCardService {

  private static final Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);
  @Autowired
  ClientService feignClientClient;

  @Autowired
  CreditCardRepository creditCardRepository;

  @Autowired
  CreditRepository creditRepository;

  @Override
  public Mono<CreditCard> create(CreditCard card) {

    String newSn = "";

    if (card == null) {
      throw new ModelException("Credit card object null or invalid");
    }

    if (card.getCredit() == null) {
      throw new ModelException("Credit null or invalid");
    }

    if (card.getFinancialCompany().isEmpty()) {
      throw new ModelException("FinancialCompany null or invalid");
    }

    if (card.getFinancialCompany().equalsIgnoreCase(Constant.FinancialCompany.MASTERCARD.type)) {
      newSn += Constant.CreditCardSnPrefix.MASTERCARD.type;
    }

    if (card.getFinancialCompany().equalsIgnoreCase(Constant.FinancialCompany.VISA.type)) {
      newSn += Constant.CreditCardSnPrefix.VISA.type;
    }

    // Verificar si existe credito relacionado a un cliente
    Mono<Boolean> cardsXCredit = creditCardRepository
            .findAll().any(c -> c.getCredit().getCreditID()
                    .equals(card.getCredit().getCreditID())
                    && c.isActive());
    /*
    if (cardsXCredit != null) {
      throw new ModelException("Existing cards for this credit line");
    }*/

    Util.Random random = new Util.Random();
    newSn = String.format("%s-%s", newSn, random.getNumericString(4, 3, "-"));
    card.setCreditCardSN(newSn);

    card.setStatus("ACTIVE");
    card.setActive(true);

    logger.info("Create entity credit card");
    return creditCardRepository.save(card);
  }

  @Override
  public Mono<Void> createList(List<CreditCard> cards) {

    if (cards == null || cards.isEmpty()) {
      logger.info("Empty list of cards");
      return Mono.just(null);
    }

    logger.info("Create list of credit card entity");
    creditCardRepository.saveAll(cards).subscribe();
    return Mono.just("Adding credit card list").then();
  }

  @Override
  public Mono<CreditCard> findById(String id) {
    logger.info("Find credit entity");
    return creditCardRepository.findById(id);
  }

  @Override
  public Flux<CreditCard> findAll() {
    logger.info("Find all credit card entities");
    return creditCardRepository.findAll().filter(c -> c.isActive());
  }

  /**
   * Find credit cards associated to Person Clients by id.
   *
   * @param id String
   * @return Single Credit card object
   */
  @Override
  public Flux<CreditCard> findByPersonClientId(String id) {
    List<Client> clients = feignClientClient.getClients();
    if (clients.isEmpty()) {
      throw new ModelException("No clients found");
    }

    id = id.isEmpty() ? "" : id.trim();
    final String _clientId = id;

    if (_clientId.equals("")) {
      throw new ModelException("Invalid Id");
    }

    boolean exists = clients.stream().anyMatch(x -> x.getClientType()
            .getClientTypeId().trim()
            .equals(Constant.ClientType.NATURAL_PERSON.type)
            && x.getIdClient().equals(_clientId));

    if (!exists) {
      throw new ModelException("No personal clients found");
    }

    logger.info("Credit cards associated to Person Clients by id");

    return creditRepository.findAll()
            .filter(x -> x.getClientID().equals(_clientId))
            .flatMap(y -> creditCardRepository
                    .findAll()
                    .filter(z -> z.getCredit().getCreditID()
                            .equals(y.getCreditID())));
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

  /**
   * Find business clients by clientId.
   *
   * @param clientID String
   * @return Multiple objects
   */
  @Override
  public Flux<Object> findByBusinessClientId(String clientID) {
    logger.info("Find credit cards associated to Business Client by id");

    List<Client> clients = feignClientClient.getClients();
    if (clients.isEmpty()) {
      throw new ModelException("No clients found");
    }

    clientID = clientID.isEmpty() ? "" : clientID.trim();
    final String _id = clientID;

    if (_id.equals("")) {
      throw new ModelException("Invalid Id");
    }

    boolean exists = clients.stream().anyMatch(x -> x.getClientType().getClientTypeId().trim()
            .equals(Constant.IncomeAccountTypeId.BUSINESS_CREDIT_ID.type)
            && x.getIdClient().equals(_id));

    if (!exists) {
      throw new ModelException("No business clients found");
    }

    return creditRepository.findAll()
            .filter(x -> x.getClientID().trim().equals(_id))
            .flatMap(y -> creditCardRepository
                    .findAll()
                    .filter(z -> z.getCredit().getCreditID().trim()
                            .equals(y.getCreditID().trim())));
  }

  /**
   * Inactive method.
   *
   * @param id String
   * @return Mono Void
   */
  @Override
  public Mono<Void> inactive(String id) {
    logger.info("Set credit card to inactive state");

    return creditCardRepository.findById(id)
            .flatMap(card -> {
              card.setActive(false);
              card.setStatus("inactive");
              return creditCardRepository.save(card).then();
            });
  }
}
