package com.bootcamp.incomeproductservice.service.impl;


import com.bootcamp.incomeproductservice.exceptions.ExceptionResponse;
import com.bootcamp.incomeproductservice.exceptions.ModelException;
import com.bootcamp.incomeproductservice.model.Constant;
import com.bootcamp.incomeproductservice.model.Credit;
import com.bootcamp.incomeproductservice.model.IncomeAccountType;
import com.bootcamp.incomeproductservice.model.dto.CreditDto;
import com.bootcamp.incomeproductservice.repository.CreditRepository;
import com.bootcamp.incomeproductservice.repository.IncomeAccountTypeRepository;
import com.bootcamp.incomeproductservice.service.CreditService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {

    private static final Logger logger = LoggerFactory.getLogger(CreditServiceImpl.class);

    @Autowired
    CreditRepository creditRepository;
    @Autowired
    IncomeAccountTypeRepository  incomeAccountTypeRepository;

    @Override
    public Mono<Credit> create(Credit credit) {

        try
        {
            if(credit == null)
            {
                throw new ModelException("Credit object null or invalid");
            }

            if(credit.getClientID().isEmpty())
            {
                throw new ModelException("Id Client null or invalid");
            }

            if(credit.getIncomeAccountType() == null)
            {
                throw new ModelException("IncomeAccountType associated to client is null or invalid");
            }

            String incomeAccountTypeID = credit.getIncomeAccountType().getIncomeAccountTypeID().trim();
            if(incomeAccountTypeID.isEmpty())
            {
                throw new ModelException("IncomeAccountType ID associated to client is null or invalid");
            }

            List<String> types = new ArrayList<>();
            types.add(Constant.IncomeAccountTypeId.PERSONAL_CREDIT_ID.type);
            types.add(Constant.IncomeAccountTypeId.BUSINESS_CREDIT_ID.type);

            if(types.contains(incomeAccountTypeID))
            {
                throw new ModelException("IncomeAccountType ID associated to client is not valid");
            }

            Mono<IncomeAccountType> maxIncomes = incomeAccountTypeRepository.findById(incomeAccountTypeID).single();

            Mono<Long> countCreditsByClient = creditRepository.findAll().
                                            filter(c-> c.isActive() && c.getClientID().equalsIgnoreCase(credit.getClientID()) &&
                                                   c.getIncomeAccountType().getIncomeAccountTypeID().equalsIgnoreCase(incomeAccountTypeID)
                                            ).count();

            /* validacion de numero de productos activs por tipo de cliente
            long max;
            Function<IncomeAccountType, String> mapper = new Function<IncomeAccountType, String>() {
                @Override
                public String apply(IncomeAccountType incomeAccountType) {

                }
            };
            maxIncomes.map(mapper);
            //countCreditsByClient.subscribe( x->x.longValue() <= maxIncomes.subscribe() );
            */

        }
        catch (RuntimeException ex)
        {
            throw new RuntimeException(ex);
        }

        logger.info("Create entity credit");
        return creditRepository.save(credit);
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
        return creditRepository.findById( creditID );
        //return creditRepository.findAll().filter(c->c.getCreditID().equalsIgnoreCase(creditID)).next();
    }

    public  Flux<Credit> findByBusinessClientId(String id){
        logger.info("Find credits by Business Client ");
        return creditRepository.findAll().
                filter(c-> c.isActive() && c.getClientID().equalsIgnoreCase(id) &&
                       c.getIncomeAccountType().getIncomeAccountTypeID().equals("2") );
    }

    public  Mono<Credit> findByPersonClientId(String id){
        logger.info("Find credits by Person Client ");
        return creditRepository.findAll().filter(c-> c.isActive() && c.getClientID().equalsIgnoreCase(id) &&
                        c.getIncomeAccountType().getIncomeAccountTypeID().equals("1")
                        ).take(1).single();
    }

    /*
    @Override
    public Mono<CreditDto> findDtoById(String creditID) {
        return creditRepository.findById( creditID ).map(c -> {
            CreditDto creditDto;
            creditDto = MapStructConverter.MAPPER.convert(c);
            return Mono.justOrEmpty(creditDto);
        });
    }  */


    @Override
    public Flux<Credit> findAll() {
        logger.info("Find all credit entities");
        return creditRepository.findAll().filter(c-> c.isActive());
    }

    @Override
    public Mono<Credit> update(Credit credit) {
        logger.info("Updating credit entity");
        return creditRepository.save(credit);
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
    public Mono<Void> inactive(String id)
    {
        return creditRepository.findById(id).flatMap(c -> {
                    c.setActive(false);
                    c.setStatus("inactive");
                    return creditRepository.save(c).then();
                });
    }

    @Override
    public Flux<Credit> fetchCreditsByClientName(String name) {
        logger.info("Fetch credits entity by client");
        return null;
    }
}
