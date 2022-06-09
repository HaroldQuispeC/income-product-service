package com.bootcamp.incomeproductservice.util;

import com.bootcamp.incomeproductservice.model.Credit;
import com.bootcamp.incomeproductservice.model.dto.CreditDto;

public interface DtoConverter {
    Credit convert(CreditDto creditDto);

    CreditDto convert(Credit credit);

}
