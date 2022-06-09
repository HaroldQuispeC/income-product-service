package com.bootcamp.incomeproductservice.util.impl;

import com.bootcamp.incomeproductservice.model.Credit;
import com.bootcamp.incomeproductservice.model.dto.CreditDto;
import com.bootcamp.incomeproductservice.util.DtoConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructConverter extends DtoConverter {
    MapStructConverter MAPPER = Mappers.getMapper(MapStructConverter.class);


    @Override
    Credit convert(CreditDto creditDto);

    @Override
    CreditDto convert(Credit credit);


}

