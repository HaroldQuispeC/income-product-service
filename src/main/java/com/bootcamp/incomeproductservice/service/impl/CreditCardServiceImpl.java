package com.bootcamp.incomeproductservice.service.impl;

import com.bootcamp.incomeproductservice.exceptions.ModelException;
import com.bootcamp.incomeproductservice.model.Client;
import com.bootcamp.incomeproductservice.model.Constant;
import com.bootcamp.incomeproductservice.model.CreditCard;
import com.bootcamp.incomeproductservice.repository.CreditCardRepository;
import com.bootcamp.incomeproductservice.repository.CreditRepository;
import com.bootcamp.incomeproductservice.service.ClientService;
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
    ClientService feignClientClient ;

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

    /**
     * Find credit cards associated to Person Clients by id
     * @param id
     * @return Single Credit card object
     */
    @Override
    public Flux<CreditCard> findByPersonClientId(String id) {
        List<Client> clients = feignClientClient.getClients();
        if(clients.isEmpty()){
            throw new ModelException("No clients found");
        }

        id = id.isEmpty() ? "" : id.trim();
        final String _id = id;

        if(_id == "")
            throw new ModelException("Invalid Id");

        boolean exists = clients.stream().anyMatch(x -> x.getClientType().trim().equals("1")
                         && x.getIdClient().equals(_id));

        if(!exists){
            throw new ModelException("No personal clients found");
        }

        logger.info("Credit cards associated to Person Clients by id");

        return creditRepository.findAll().
                filter(x->x.getClientID().equals(_id)).
                flatMap(y-> creditCardRepository.
                          findAll().
                          filter(z -> z.getCreditID().equals(y.getCreditID())));


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
     * Find business clients by clientId
     * @param clientID
     * @return Multiple objects
     */
    @Override
    public Flux<Object> findByBusinessClientId(String clientID) {
        List<Client> clients = feignClientClient.getClients();
        if(clients.isEmpty()){
            throw new ModelException("No clients found");
        }

        clientID = clientID.isEmpty() ? "" : clientID.trim();
        final String _id = clientID;

        if(_id == "")
            throw new ModelException("Invalid Id");

        boolean exists = clients.stream().anyMatch(x -> x.getClientType().trim().
                equals(Constant.IncomeAccountTypeId.BUSINESS_CREDIT_ID.type)
                && x.getIdClient().equals(_id));

        if(!exists){
            throw new ModelException("No business clients found");
        }

        logger.info("Find credit cards associated to Business Client by id");

        return creditRepository.findAll().
                filter(x->x.getClientID().trim().equals(_id)).
                flatMap(y-> creditCardRepository.
                        findAll().
                        filter(z -> z.getCreditID().trim().equals(y.getCreditID().trim())));

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
