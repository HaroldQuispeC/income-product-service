package com.bootcamp.incomeproductservice.service.impl;

import com.bootcamp.incomeproductservice.exceptions.ModelException;
import com.bootcamp.incomeproductservice.model.Client;
import com.bootcamp.incomeproductservice.model.Constant;
import com.bootcamp.incomeproductservice.model.Credit;
import com.bootcamp.incomeproductservice.repository.CreditRepository;
import com.bootcamp.incomeproductservice.repository.IncomeAccountTypeRepository;
import com.bootcamp.incomeproductservice.service.ClientService;
import com.bootcamp.incomeproductservice.service.CreditService;
import com.bootcamp.incomeproductservice.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {

  private static final Logger logger = LoggerFactory.getLogger(CreditServiceImpl.class);

  @Autowired
  ClientService feignClientClient;
  @Autowired
  CreditRepository creditRepository;
  @Autowired
  IncomeAccountTypeRepository incomeAccountTypeRepository;

  @Override
  public Mono<Credit> create(Credit credit) {

    if (credit == null) {
      throw new ModelException("Credit object null or invalid");
    }

    if (credit.getClientID().isEmpty()) {
      throw new ModelException("Client ID null or invalid");
    }

    if (credit.getIncomeAccountType() == null) {
      throw new ModelException("IncomeAccountType associated to client is null or invalid");
    }

    String incomeAccountTypeID = credit.getIncomeAccountType().getIncomeAccountTypeID().trim();
    if (incomeAccountTypeID.isEmpty()) {
      throw new ModelException("IncomeAccountType ID associated to client is null or invalid");
    }

    List<String> types = new ArrayList<>();
    types.add(Constant.IncomeAccountTypeId.PERSONAL_CREDIT_ID.type);
    types.add(Constant.IncomeAccountTypeId.BUSINESS_CREDIT_ID.type);

    if (!types.contains(incomeAccountTypeID)) {
      throw new ModelException("IncomeAccountType ID associated to client is not valid");
    }

    String newSn = "";

    if (credit.getIncomeAccountType() == null) {
      throw new ModelException("IncomeAccountType associated is null or invalid");
    }

    if (credit.getBalance() > credit.getCreditLimit()) {
      throw new ModelException("Balance Account can not overcome limit of credit.");
    }

    if (credit.getIncomeAccountType()
            .getIncomeAccountTypeID()
            .equalsIgnoreCase(Constant.IncomeAccountTypeId.PERSONAL_CREDIT_ID.type)) {
      newSn += Constant.CreditSnPrefix.PERSONAL_CREDIT.type;
    }

    if (credit.getIncomeAccountType()
            .getIncomeAccountTypeID()
            .equalsIgnoreCase(Constant.IncomeAccountTypeId.BUSINESS_CREDIT_ID.type)) {
      newSn += Constant.CreditSnPrefix.BUSINESS_CREDIT.type;
    }

    Util.Random random = new Util.Random();
    newSn = String.format("%s-%s-%s",
            newSn,
            random.getNumericString(4),
            random.getNumericString(10));
    credit.setCreditSN(newSn);
    credit.setStatus("ACTIVE");
    credit.setActive(true);

    // validacion de numero de productos activos por tipo de cliente
    Integer maxIncomes = Objects.requireNonNull(incomeAccountTypeRepository
            .findById(incomeAccountTypeID)
            .single()
            .block()).getMaximumProductsAllowed();

    return creditRepository.findAll()
            .filter(c -> c.isActive() && c.getClientID()
                    .equalsIgnoreCase(credit.getClientID())
                    && c.getIncomeAccountType()
                    .getIncomeAccountTypeID()
                    .equalsIgnoreCase(incomeAccountTypeID)
            ).count().flatMap(products -> {
              if (maxIncomes != null && products >= maxIncomes) {
                throw new ModelException("Max. number of accounts "
                        + " associated to client " + credit.getClientID() + " has been reached.");
              }

              logger.info("Create entity credit");
              return creditRepository.save(credit);
            });
  }

  @Override
  public Mono<Void> createList(List<Credit> credits) {
    logger.info("Create list of credit entity");
    creditRepository.saveAll(credits).subscribe();
    return Mono.just("Adding credit list").then();
  }

  @Override
  public Mono<Credit> findById(String creditID) {
    logger.info("Find credit entity");
    return creditRepository.findById(creditID);
  }

  /**
   * findByBusinessClientId method.
   *
   * @param id String
   * @return Flux Credit
   */
  public Flux<Credit> findByBusinessClientId(String id) {
    logger.info("Find credit (income-product) by Business Client");
    return creditRepository.findAll()
            .filter(c -> c.isActive() && c.getClientID().equalsIgnoreCase(id)
                    && c.getIncomeAccountType().getIncomeAccountTypeID()
                    .equals(Constant.IncomeAccountTypeId.BUSINESS_CREDIT_ID.type));
  }

  /**
   * findByPersonClientId method.
   *
   * @param id String
   * @return Mono Credit
   */
  public Mono<Credit> findByPersonClientId(String id) {
    logger.info("Find credit (income-product) by Person Client");
    return creditRepository.findAll().filter(c -> c.isActive() && c.getClientID()
            .equalsIgnoreCase(id)
            && c.getIncomeAccountType()
            .getIncomeAccountTypeID()
            .equals(Constant.IncomeAccountTypeId.PERSONAL_CREDIT_ID.type)
    ).singleOrEmpty();
  }

  /**
   * findAll method.
   * @return
   */
  @Override
  public Flux<Credit> findAll() {
    logger.info("Find all credit entities");
    return creditRepository
            .findAll()
            .filter(c -> c.isActive());
  }

  /**
   * update method.
   * @param credit Credit
   * @return
   */
  @Override
  public Mono<Credit> update(Credit credit) {
    logger.info("Updating credit entity");
    return creditRepository.save(credit);
  }

  /**
   * Updating credit entity with id.
   *
   * @param id     String
   * @param credit Credit
   * @return
   */
  public Mono<Credit> update(String id, Credit credit) {
    logger.info("Updating credit entity with id");
    return creditRepository.findById(id)
            .map(c -> {
              c.setStatus(credit.getStatus().trim());
              c.setActive(credit.isActive());

              if (credit.getStartDate() != null) {
                c.setStartDate(credit.getStartDate());
              }
              if (credit.getEndDate() != null) {
                c.setEndDate(credit.getEndDate());
              }
              c.setBalance(credit.getBalance());
              c.setBillingCycle(credit.getBillingCycle());
              c.setCreditLimit(credit.getCreditLimit());
              c.setDebt(credit.getDebt());
              return creditRepository.save(c);
            }).then(Mono.empty());
  }

  @Override
  public Mono<Credit> change(Credit credit) {
    logger.info("Saving credit entity");
    return creditRepository.save(credit);
  }

  @Override
  public Mono<Void> remove(String creditID) {
    logger.info("Removing credit entity physically");
    creditRepository.deleteById(creditID).subscribe();
    return Mono.just("remove " + creditID).then();
  }

  @Override
  public Mono<Void> remove(Credit credit) {
    logger.info("Removing credit entity physically");
    String id = credit.getCreditID();
    creditRepository.delete(credit).subscribe();
    return Mono.just("remove " + id).then();
  }

  @Override
  public Mono<Void> inactive(String id) {
    return creditRepository.findById(id).flatMap(c -> {
      c.setActive(false);
      c.setStatus("inactive");
      return creditRepository.save(c).then();
    });
  }

  /**
   * fetchCreditsByClientName method.
   *
   * @param name String
   * @return
   */
  @Override
  public Flux<Credit> fetchCreditsByClientName(String name) {
    logger.info("Fetch credits entity by client");

    List<Client> clients = feignClientClient.getClients();
    if (clients.isEmpty()) {
      throw new ModelException("No clients found");
    }

    Optional<Client> optBusiness = clients.stream().filter(client ->
                    client.getClientType()
                            .equals(Constant.IncomeAccountTypeId.BUSINESS_CREDIT_ID.type)
                            && client.getBusiness()
                            .getBusinessName()
                            .toLowerCase()
                            .contains(name.toLowerCase()))
            .findFirst();

    Optional<Client> optPersonal = clients.stream().filter(client ->
            client.getClientType()
                    .equals(Constant.IncomeAccountTypeId.PERSONAL_CREDIT_ID.type)
                    && client.getNaturalPerson().getName()
                    .toLowerCase().concat(" " + client.getNaturalPerson().getLastName())
                    .toLowerCase().contains(name.toLowerCase())).findFirst();

    if (!optBusiness.isPresent() && !optPersonal.isPresent()) {
      throw new ModelException("No clients found");
    }

    if (optBusiness.isPresent()) {
      return findByBusinessClientId(optBusiness.get().getIdClient());
    }

    return findByPersonClientId(optPersonal.get().getIdClient()).flux();
  }
}
