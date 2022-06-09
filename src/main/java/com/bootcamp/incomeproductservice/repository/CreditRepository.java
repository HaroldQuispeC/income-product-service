package com.bootcamp.incomeproductservice.repository;

import com.bootcamp.incomeproductservice.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
        * [Description]. <br/>
        * <b>Class</b>: {@link CreditRepository}<br/>
        * <b>Copyright</b>: &Copy; 2022 NTT DATA Per&uacute;. <br/>
        * <b>Company</b>: NTT DATA Per&uacute;. <br/>
        *
        * @author NTT DATA Per&uacute;.<br/>
        * <u>Developed by</u>: <br/>
        * <ul>
        * <li>{USERNAME}. (acronym) From (NTT DATA)</li>
        * </ul>
        * <u>Changes</u>:<br/>
        * <ul>
        * <li>Junio 06, 2022 (acronym) Creation class.</li>
        * </ul>
        * @version 1.0
        */

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit,String> {

}

